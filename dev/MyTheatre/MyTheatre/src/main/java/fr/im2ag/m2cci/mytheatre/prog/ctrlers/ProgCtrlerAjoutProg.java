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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
        response.setContentType("text/html;charset=UTF-8");

        // Récupération des valeurs des dates du formulaire
        String debut = request.getParameter("dateDebut");
        String fin = request.getParameter("dateFin");
        if (debut == null) {         // Si il n'y a pas de date spécifiée, on la force
            debut = "2020-03-01";
        }
        if (fin == null) {
            fin = "2020-03-31";
        }

        try {
            // Récupération et set si besoin des dates
            Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(debut);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fin);
            if (dateDebut.after(dateFin)) {   // Si l'utilisateur inverse la date de début et de fin
                Date tmp = dateFin;
                dateFin = dateDebut;
                dateDebut = tmp;
            }

            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);

            
            // Requete à la BD
            List<Spectacle> listSpectacles = ProgDAO.toutSpectacles(dataSource);
            request.setAttribute("listeSpectacles", listSpectacles);

            List<Representation> listRepresentations = ProgDAO.representationsFiltrees(dataSource, dateDebut, dateFin, "null", new ArrayList<String>());
            request.setAttribute("listeRepresentations", listRepresentations);
            
            
            // On prépare les attributs de la requete Web
            // Nombre de semaines entre les deux dates (toutes semaine inclu)
            int nbSem = nbSemaineEntre(dateDebut, dateFin);
            request.setAttribute("nbSemaines", nbSem);
            
            
            // Dates de début et de fin de chaque semaines stockées dans des tableaux
            Date[] datesDebutsSemaines = new Date[nbSem];
            Date[] datesFinsSemaines = new Date[nbSem];
            
            // Remplissage de ces deux tableaux
            Date dateDebutSem = dateDebut;
            Date dateFinSem;
            Calendar c = Calendar.getInstance();
            
            // On retrouve la date du lundi correspondant à la première semaine
            c.setTime(dateDebutSem);
            int numeroJourDateDebSem = c.get(Calendar.DAY_OF_WEEK);        // Commence au Dimanche donc on décalle de 1 jour
            numeroJourDateDebSem -= 1;
            if (numeroJourDateDebSem == 0) {
                numeroJourDateDebSem = 7;
            }
            c.add(Calendar.DATE, -(numeroJourDateDebSem - 1));      // Ici on met dans le calendrier la date du lundi
            dateDebutSem = c.getTime();     // On convertit en date

            // On parcours jusqu'à la dernière semaine
            for (int i = 0; i < nbSem; i++) {       // Pour chaque semaine
                c.setTime(dateDebutSem);    // On convertit le lundi en calendrier

                c.add(Calendar.DATE, 6);    // On ajoute 6 pour passer au dimanche
                dateFinSem = c.getTime();   // On récupère la date
                
                // On remplit les tableaux
                datesDebutsSemaines[i] = dateDebutSem;      
                datesFinsSemaines[i] = dateFinSem;

                c.add(Calendar.DATE, 1);    // On passe au lundi suivant
                dateDebutSem = c.getTime();     // On prépare l'itération suivante
            }
            
            request.setAttribute("datesDebutsSemaines", datesDebutsSemaines);
            request.setAttribute("datesFinsSemaines", datesFinsSemaines);
            
            
            // Pour chaque semaine, on récupère toutes les représentations et on les trie par jour de la semaine
            ArrayList[] representationsParSemaine = new ArrayList[nbSem];
            
            // On crée le gros tableau qui contient toutes les semaines
            for (int iSem = 0; iSem < nbSem; iSem++){ 
                representationsParSemaine[iSem] = new ArrayList();;
                for (int iJour = 0; iJour < 7; iJour ++){
                    representationsParSemaine[iSem].add(new ArrayList<Representation>());
                }
            }
            
            /*// On remplit le tableau
            for (Representation r : listRepresentations) {
                Date horaire = r.getHoraire();
                for (int iSem = 0; iSem < nbSem; iSem++){
                    dateDebutSem = datesDebutsSemaines[iSem];
                    dateFinSem = datesDebutsSemaines[iSem];
                    if (horaire.after(dateDebutSem) && horaire.before(dateDebutSemSuivante)) 
                }
            }*/
            
            
            
            
            
            
            
            /*// On récupère toutes les représentations entre la dateDebutSem et la dateFinSem
                for (Representation r : listRepresentations) {
                    Date horaire = r.getHoraire();
                    if (horaire.after(dateDebutSem) && horaire.before(dateDebutSemSuivante)) {
                        String nom = r.getSpectacle().getNom();
                        String heure = heureFormatter.format(horaire) + "h";

                        // Calcul du numero du jour de la semaine
                        Calendar cJourSemaine = Calendar.getInstance();
                        cJourSemaine.setTime(horaire);
                        int numJourSem = cJourSemaine.get(Calendar.DAY_OF_WEEK);
                        numJourSem -= 1;
                        if (numJourSem == 0) {
                            numJourSem = 7;
                        }
                        
                    }
                }
            
            
            // On 
            ArrayList[] test = new ArrayList[7];
            for (int i = 0; i < 7 ; i++){
                test[i] = new ArrayList<Representation>();
            }*/
            
            
            
            
            

            request.getRequestDispatcher("/WEB-INF/ajoutProgrammation.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException("Problème avec la BD : " + ex.getMessage(), ex);
        } catch (ParseException ex) {
            throw new ServletException("Problème avec la convertion des dates : " + ex.getMessage(), ex);
        }
    }
    
    private int nbSemaineEntre(Date d1, Date d2){
        // Calcul le numéro du jour de semaine de d2
        Calendar c = Calendar.getInstance();
        c.setTime(d2);
        int dayOfWeekOfD2 = c.get(Calendar.DAY_OF_WEEK);        // Commence au Dimanche 
        dayOfWeekOfD2 -= 1;
        if (dayOfWeekOfD2 == 0) {
            dayOfWeekOfD2 = 7;
        }
        
        // Calcul du nombre de jour entre les deux dates
        LocalDate date1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long nbJour = ChronoUnit.DAYS.between(date1, date2) + 1;
        
        // Calcul du nombre de semaines
        int nbSem = 0;
        while(nbJour > 0){
            nbSem ++;
            nbJour -= dayOfWeekOfD2;
            dayOfWeekOfD2 = 7;
        }
      
        return nbSem;
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
