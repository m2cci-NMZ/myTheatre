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
import java.util.ArrayList;
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
 * @author iXeRay
 */
@WebServlet(name = "insertRepresentation", urlPatterns = {"/insertRepresentation"})
public class InsertRepresentation extends HttpServlet {

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

        int numeroSpe = Integer.parseInt(request.getParameter("numeroSpeRep"));
        String horaireRepDate = request.getParameter("horaireRepDate");
        String horaireRepHeure = request.getParameter("horaireRepHeure");

        try {
            Date horaireRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(horaireRepDate + " " + horaireRepHeure);

            // Requete à la BD pour l'insertion
            ProgDAO.insertRepresentation(dataSource, numeroSpe, horaireRep);

            request.getRequestDispatcher("progCtrlerAjoutProg").forward(request, response);
        } catch (SQLException sqlEx1) {
            try {
                Date horaireRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(horaireRepDate + " " + horaireRepHeure);
                Date jourRep = new SimpleDateFormat("yyyy-MM-dd").parse(horaireRepDate);
                
                // On récupère toutes les Representations de cette journée
                List<Representation> representations = ProgDAO.toutesRepresentationsDatees(dataSource, jourRep, jourRep);
            
                // On vérifie si le problème vient de la clé primaire
                boolean clePrimaireViolee = false;
                for (Representation r : representations) {
                    if (r.getHoraire().equals(horaireRep)) {
                        clePrimaireViolee = true;
                    }
                }
                
                // On vérifie si le numéro du Spectacle appartient bien à un Spectacle
                Spectacle s = ProgDAO.spectacleDeNumero(dataSource, numeroSpe);
                boolean spectacleManquant = s == null;  // Si la DAO ne retourne rien, il n'y a pas de Spectacle avec ce numéro
                
                if(clePrimaireViolee || spectacleManquant){
                    // On signale au controleur les différents problèmes
                    List<String> erreursSQL = new ArrayList<>();
                    if (clePrimaireViolee){
                        erreursSQL.add("PK_REP");   // Pb lié à la clé primaire
                    }
                    if (spectacleManquant){
                        erreursSQL.add("FK_REP_numeroSpe");   // Pb lié au numéro du spectacle pas dans la BD 
                    }
                    
                    request.setAttribute("erreursSQL", erreursSQL);
                    request.getRequestDispatcher("progCtrlerAjoutProg").forward(request, response);
                } else {
                    // Si ça ne vient ni de la clé primaire, ni de numeroSpe, on renvoie l'exception
                    throw new ServletException("Problème à l'insertion de la Representation: " + sqlEx1.getMessage(), sqlEx1);
                }
                
            } catch (SQLException sqlEx2) {
                throw new ServletException("Problème avec la BD : " + sqlEx2.getMessage(), sqlEx2);
            } catch (ParseException parseEx2) {
                // Si ça vient pas de la clé primaire, on renvoie l'exception
                throw new ServletException("Problème avec la convertion des dates : " + parseEx2.getMessage(), parseEx2);
            }
        
        } catch (ParseException parseEx1) {
            throw new ServletException("Problème avec la convertion des dates : " + parseEx1.getMessage(), parseEx1);
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
