/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.ctrlers;

import fr.im2ag.m2cci.mytheatre.prog.dao.ProgDAO;
import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author marti236
 */
@WebServlet(name = "progCtrler", urlPatterns = {"/progCtrler"})
public class ProgCtrler extends HttpServlet {

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
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Récupération des valeurs des champs du formulaire
        String debut = request.getParameter("dateDebut");
        String fin = request.getParameter("dateFin");
        String cible = request.getParameter("cible");
        String[] paramTypes = request.getParameterValues("type");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCourante = new Date();
        // Traitement du cas où on charge la page pour la première fois
        boolean premierChargement = false;
        if(debut == null && fin == null && cible == null && paramTypes == null){
            premierChargement = true;
        }
        // Ajout de l'attribut à la requete
        request.setAttribute("premierChargement", premierChargement);
        // Dates par défaut
        if (debut == null) { 
            debut = dateFormatter.format(dateCourante);
        }
        if (fin == null) {
            Calendar c = Calendar.getInstance();
            c.setTime(dateCourante);
            c.add(Calendar.DATE, 1);
            fin = dateFormatter.format(c.getTime());
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
            
            if (!premierChargement) {  // Si ce n'est pas la page par défaut on doit faire des requetes
                // Gestion des types de spectacles
                List<String> types = new ArrayList<>();
                if (paramTypes != null) {
                    // On traite paramType différemment des autres paramètres car il peut être vide à la demande de l'utilisateur (si il décoche tout)
                    for (String paramType : paramTypes) {
                        types.add(paramType);
                    }
                }
                request.setAttribute("listTypes", types);

                // Requete à la BD
                List<Representation> listRepresentations = ProgDAO.representationsFiltrees(dataSource, dateDebut, dateFin, cible, types);
                request.setAttribute("progList", listRepresentations);
            }

            request.getRequestDispatcher("/WEB-INF/prog.jsp").forward(request, response);

        } catch (SQLException ex) {
            throw new ServletException("Problème avec la BD : " + ex.getMessage(), ex);
        } catch (ParseException ex) {
            throw new ServletException("Problème avec la convertion des dates : " + ex.getMessage(), ex);
        }
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
