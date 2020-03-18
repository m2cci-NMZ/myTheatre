/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.ctrlers;

import fr.im2ag.m2cci.mytheatre.prog.dao.ProgDAO;
import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import fr.im2ag.m2cci.mytheatre.prog.model.Spectacle;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author miquelr
 */
@WebServlet(name = "progCtrlerAjoutProg", urlPatterns = {"/progCtrlerAjoutProg"})
public class ProgCtrlerAjoutProg extends HttpServlet {

    @Resource(name = "jdbc/db")
    private DataSource dataSource;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des valeurs des dates du formulaire
        String debut = request.getParameter("dateDebut");
        String fin = request.getParameter("dateFin");
        
        if (debut == null) {         // Si il n'y a pas de dates spécifiées, on les force à des dates par défaut
            debut = "2020-03-01";
        }
        if (fin == null) {
            fin = "2020-03-31";
        }

        try {
            // Conversion en date
            Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(debut);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fin);
            // Si l'utilisateur inverse la date de début et de fin on le met dans le bon ordre
            if (dateDebut.after(dateFin)) {
                Date tmp = dateFin;
                dateFin = dateDebut;
                dateDebut = tmp;
            }
            // Ajout des attributs à la requete
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);


            //######################################################################
            //Traitement de la partie affichage de la programmation en semaines
            //######################################################################
            
            //##### Calcul des Dates du Lundi et du Dimanche de chaque semaines
            // Nombre de semaines entre les deux dates (toutes semaines inclus)
            int nbSem = nbSemaineEntre(dateDebut, dateFin);
            request.setAttribute("nbSemaines", nbSem);

            // Dates de début et de fin de chaque semaines stockées dans des tableaux
            Date[] datesLundi = new Date[nbSem];
            Date[] datesDimanche = new Date[nbSem];

            // Remplissage des deux tableaux
            Date dateLundi, dateDimanche;
            Calendar c = Calendar.getInstance();

            // On retrouve la date du lundi correspondant à la première semaine
            int numeroJourSemDateDeb = numeroJourSemaine(dateDebut);
            c.setTime(dateDebut);
            c.add(Calendar.DATE, -numeroJourSemDateDeb);      // Met dans le calendrier la date du lundi

            // On parcours jusqu'à la dernière semaine
            for (int i = 0; i < nbSem; i++) {       // Pour chaque semaine
                dateLundi = c.getTime();     // Récupère la date du lundi

                c.add(Calendar.DATE, 6);    // On ajoute 6 pour passer au dimanche
                dateDimanche = c.getTime();   // On récupère la date

                // On remplit les tableaux
                datesLundi[i] = dateLundi;
                datesDimanche[i] = dateDimanche;

                c.add(Calendar.DATE, 1);    // On passe au lundi suivant
            }

            request.setAttribute("datesLundi", datesLundi);
            request.setAttribute("datesDimanche", datesDimanche);

    
            //##### Séparation des Representations par semaines et par jours de la semaine
            // Requete a la BD
            List<Representation> listRepresentations = ProgDAO.toutesRepresentationsDatees(dataSource, dateDebut, dateFin);
            // Découpage en Liste
            List<List<List<Representation>>> repParSemaine = new ArrayList<>();
            for (int iSem = 0; iSem < nbSem; iSem++) {
                repParSemaine.add(new ArrayList<List<Representation>>());
            }
            // Remplissage des List
            int iSem = 0;
            int iRep = 0;
            while (iRep < listRepresentations.size()){
                Representation rep = listRepresentations.get(iRep);
                Date horaire = rep.getHoraire();
                if (horaire.compareTo(datesLundi[iSem])>=0 && horaire.compareTo(toFinDeJournee(datesDimanche[iSem]))<=0) {
                    ajouterAuRepParSemaine(rep, iSem, repParSemaine);
                    iRep++;
                } else if (!horaire.after(toFinDeJournee(datesDimanche[iSem]))) {
                    throw new RuntimeException ("Une des représentations de la requete n'est pas dans la plage horraire définie par l'utilisateur");
                } else {
                    iSem++;
                }
            }
            // Ajout de l'attribut
            request.setAttribute("repParSemaine", repParSemaine);
            
            
            // Requete à la BD pour tous les Spectacles
            List<Spectacle> listSpectacles = ProgDAO.toutSpectacles(dataSource);
            request.setAttribute("listeSpectacles", listSpectacles);
            
            
            request.getRequestDispatcher("/WEB-INF/ajoutProgrammation.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException("Problème avec la BD : " + ex.getMessage(), ex);
        } catch (ParseException ex) {
            throw new ServletException("Problème avec la convertion des dates : " + ex.getMessage(), ex);
        }
    }

    /**
     * Calcule le nombre de semaines de la période délimitée par deux dates
     *
     * @param d1 : Premier jour
     * @param d2 : Dernier jour
     * @return : int correspondant au nombre de semaine
     */
    protected static int nbSemaineEntre(Date d1, Date d2) {
        // Calcul du nombre de jour entre les deux dates
        LocalDate date1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long nbJour = ChronoUnit.DAYS.between(date1, date2) + 1;    // On ajoute un pour inclure d1 et d2

        // Calcul du nombre de semaines
        int nbSem = 0;
        int nbJourAEnlever = numeroJourSemaine(d2) + 1;     // On commence par enlever le nombre de jours dans la dernière semaine
        while (nbJour > 0) {
            nbSem++;
            nbJour -= nbJourAEnlever;
            nbJourAEnlever = 7;
        }

        return nbSem;
    }

    /**
     * Calcul le numéro du jour de la semaine correspondant à la date donnée en
     * paramètre : 0 pour lundi, ..., 6 pour dimanche
     *
     * @param d : Date
     * @return : int correspondant au numéro du jour
     */
    private static int numeroJourSemaine(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int numeroJour = c.get(Calendar.DAY_OF_WEEK);        // Retourne 1 pour Dimanche puis 2 pour Lundi, ...
        numeroJour -= 1;
        if (numeroJour == 0) {
            numeroJour = 7;
        }
        numeroJour -= 1;    // On fait commencer le lundi à 0

        return numeroJour;
    }

    /**
     * Retourne une nouvelle date de même jour que à celle passée en paramètre,
     * mais ayant pour heure 23h59 Permet de faire des comparaison entre jours
     *
     * @param d : Date
     * @return : Date fixée à 23h59
     */
    private Date toFinDeJournee(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR, +23);
        c.add(Calendar.MINUTE, +59);

        return c.getTime();
    }
    
    private void ajouterAuRepParSemaine(Representation rep, int numeroSem, List<List<List<Representation>>> repParSemaine){
        if (repParSemaine.get(numeroSem).isEmpty()){
            // Premier ajout pour cette semaine, on crée les 7 List<Representation> pour chaque jour (Lundi, ..., Dimanche)
            for (int iJour = 0; iJour < 7; iJour++){
                repParSemaine.get(numeroSem).add(new ArrayList<Representation>());
            }
        }
        
        int numeroJourSemaineRep = numeroJourSemaine(rep.getHoraire());     // On trouve l'indice du jour
        repParSemaine.get(numeroSem).get(numeroJourSemaineRep).add(rep);    // On ajoute dans le bon jour
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
