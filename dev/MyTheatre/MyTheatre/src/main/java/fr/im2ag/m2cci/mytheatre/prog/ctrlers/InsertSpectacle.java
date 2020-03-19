/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.ctrlers;

import fr.im2ag.m2cci.mytheatre.prog.dao.ProgDAO;
import fr.im2ag.m2cci.mytheatre.prog.model.Spectacle;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "insertSpectacle", urlPatterns = {"/insertSpectacle"})
public class InsertSpectacle extends HttpServlet {

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

        int numero = Integer.parseInt(request.getParameter("numeroSpe"));
        String nom = request.getParameter("nomSpe");
        double prixDeBase = Double.parseDouble(request.getParameter("prixSpe"));
        String cible = request.getParameter("cibleSpe");
        String type = request.getParameter("typeSpe");

        try {
            // Requete à la BD pour l'insertion
            ProgDAO.ajoutSpectacle(dataSource, numero, nom, prixDeBase, cible, type, false, false);

            request.getRequestDispatcher("progCtrlerAjoutProg").forward(request, response);
        } catch (SQLException ex1) {
            try {
                List<Spectacle> spectacles = ProgDAO.toutSpectacles(dataSource);
                
                // On vérifie si le problème vient de la clé primaire
                boolean clePrimaireViolee = false;
                for (Spectacle s : spectacles) {
                    if (s.getNumero() == numero) {
                        clePrimaireViolee = true;
                    }
                }
                
                if(clePrimaireViolee){
                    // On signale au controleur qu'il y a un pb lié à la clé primaire
                    request.setAttribute("erreurSQL", "PK_SPE");
                    
                    request.getRequestDispatcher("progCtrlerAjoutProg").forward(request, response);
                } else {
                    // Si ça vient pas de la clé primaire, on renvoie l'exception
                    throw new ServletException("Problème à l'insertion du Spectacle: " + ex1.getMessage(), ex1);
                }
                
            } catch (SQLException ex2) {
                throw new ServletException("Problème avec la BD : " + ex2.getMessage(), ex2);
            }
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
