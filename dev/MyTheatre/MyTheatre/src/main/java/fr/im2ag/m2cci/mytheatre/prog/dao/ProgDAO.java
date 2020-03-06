/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.ima2g.m2cci.mytheatre.prog.model.Representation;
import fr.ima2g.m2cci.mytheatre.prog.model.Spectacle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author marti236
 */
public class ProgDAO {
    public static List<Representation> representationsFiltrees(DataSource ds, Date debut, 
            Date fin, String cibleSpe, String typeSpe) throws SQLException, ParseException{
        
        List<Representation> representations = new ArrayList();
        
        try (Connection conn = ds.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, dateRep \n"
                + "FROM LesRepresentations R JOIN LesSpectacles S ON R.numeroSpe=S.numeroSpe \n"
                + "WHERE cibleSpe=? AND typeSpe=?");
            stmt.setString(1, "toutPublic");
            stmt.setString(2, "cirque");
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    // Récupération des attributs
                    int numero = rs.getInt("numeroSpe");
                    String nom = rs.getString("nomSpe");
                    double prixDeBase = rs.getDouble("prixDeBaseSpe");
                    String cible = rs.getString("cibleSpe");
                    String type = rs.getString("typeSpe");
                    String dateRep = rs.getString("dateRep");
                    
                    // Création des objets
                    Date date = new SimpleDateFormat("dd/MM/yyyy HHh").parse(dateRep);
                    Spectacle s;
                    switch(type){
                        case "opera":
                            int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                            boolean aUnOrchestre = aUnOrchestreOpe == 1;
                            s = new Opera(numero, nom, prixDeBase, cible, type, aUnOrchestre)
                            break;
                            //Opera s = new Opera(numero, nom, prixDeBase, cible, type);
                        case "humoristique":
                        
                        default:
                            s = new Spectacle(numero, nom, prixDeBase, cible, type);   
                    }
                    
                    
                    
                    representations.add(new Representation(date, s)) ;
                }
            } 
        }
        return representations;
    }
}
