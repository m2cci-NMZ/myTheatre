/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.ima2g.m2cci.mytheatre.prog.model.Humoristique;
import fr.ima2g.m2cci.mytheatre.prog.model.Opera;
import fr.ima2g.m2cci.mytheatre.prog.model.Representation;
import fr.ima2g.m2cci.mytheatre.prog.model.Spectacle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        
        /*String preparedQueryWithCibleAndType = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep "
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe "
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe "
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe "
                + "WHERE cibleSpe=? AND typeSpe=?;";*/
        

        
        /*Refaire la requete vis à vis de jdd1*/
        
        try (Connection conn = ds.getConnection()){
            /*PreparedStatement stmt = conn.prepareStatement(preparedQueryWithCibleAndType);
            stmt.setString(1, "toutPublic");
            stmt.setString(2, "cirque");*/
            
            Statement stmt = conn.createStatement();
            String myQuery = "SELECT numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe "
                    + "FROM LesSpectacles;";
            
            
            try (ResultSet rs = stmt.executeQuery(myQuery)) {
                
                while (rs.next()) {
                    // Récupération des attributs
                    int numero = rs.getInt("numeroSpe");
                    String nom = rs.getString("nomSpe");
                    double prixDeBase = rs.getDouble("prixDeBaseSpe");
                    String cible = rs.getString("cibleSpe");
                    String type = rs.getString("typeSpe");
                    String dateRep = rs.getString("dateRep");
                    System.out.println(numero);
                    
                    // Création des objets
                    //Date date = new SimpleDateFormat("dd/MM/yyyy HHh").parse(dateRep);
                    Spectacle s;
                    switch(type){
                        case "opera":
                            int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                            boolean aUnOrchestre = (aUnOrchestreOpe == 1);
                            s = new Opera(numero, nom, prixDeBase, cible, type, aUnOrchestre);
                            break;
                            //Opera s = new Opera(numero, nom, prixDeBase, cible, type);
                        case "humoristique":
                            int estUnOneWomanManShowHum = rs.getInt("aUnOrchestreOpe");
                            boolean estUnOneWomanManShow = (estUnOneWomanManShowHum == 1);
                            s = new Humoristique(numero, nom, prixDeBase, cible, type, estUnOneWomanManShow);
                            break;
                        default:
                            s = new Spectacle(numero, nom, prixDeBase, cible, type); 
                    }
                    //Representation rep = new Representation(date, s);
                   // representations.add(rep) ;
                   // System.out.println("Test");
                    System.out.println(s);
                }
                
                // Pas possible de faire une requete sur la Date -> On retire les dates invalides du resultat
                for (Representation rep : representations){
                    if (rep.getDate().before(debut) || rep.getDate().after(fin)){
                        representations.remove(rep);
                    }
                }
            } 
        }
        return representations;
    }
}
