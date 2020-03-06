/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.ctrlers;

import fr.im2ag.m2cci.mytheatre.prog.dao.ProgDAO;
import fr.ima2g.m2cci.mytheatre.prog.model.Representation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    @Resource(name = "jdbc/CAFE")
    private DataSource dataSource;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Date dateDebut;
            String debut;
            debut = request.getParameter("dateDebut");
            dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(debut);

            Date dateFin;
            String fin;
            fin = request.getParameter("dateFin");
            dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fin);
            

            String type;
            type = request.getParameter("type");
            
            String cible;
            cible=request.getParameter("cible");
            
            List<Representation>  listRepresentations = ProgDAO.representationsFiltrees(dataSource, dateDebut, dateFin, cible, type);
            
            request.setAttribute("progList", listRepresentations);
            
       
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
