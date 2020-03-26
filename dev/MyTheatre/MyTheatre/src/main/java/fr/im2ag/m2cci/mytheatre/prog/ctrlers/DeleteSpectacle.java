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
import java.util.ArrayList;
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
@WebServlet(name = "deleteSpectacle", urlPatterns = {"/deleteSpectacle"})
public class DeleteSpectacle extends HttpServlet {

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

        // Récupération des indices du tableau des Spectacle à supprimer
        String indicesSelectionnees = request.getParameter("lignesSpeSelected");
        String[] indicesString = indicesSelectionnees.split(" ");
        
        // Conversion en une liste d'Integer
        List<Integer> indicesASuppr = new ArrayList<>();
        for (String indice : indicesString){
            indicesASuppr.add(Integer.parseInt(indice));
        }
        
        try {
            // On récupère la liste des Spectacles
            List<Spectacle> spectacles = ProgDAO.toutSpectacles(dataSource);
            
            // On convertit la liste des indices à la liste des numeroSpe correspondante
            List<Integer> numeroSpeASuppr = new ArrayList<>();
            for (int indice : indicesASuppr){
                numeroSpeASuppr.add(spectacles.get(indice).getNumero());
            }
            
            // On appelle la DAo pour supprimer les Spectacles
            ProgDAO.deleteSpectacles(dataSource, numeroSpeASuppr);
            
        } catch (SQLException ex) {
            throw new ServletException("Problème à la suppression des Spectacles : " + ex.getMessage(), ex);  
        }
        
        request.getRequestDispatcher("progCtrlerAjoutProg").forward(request, response);
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
