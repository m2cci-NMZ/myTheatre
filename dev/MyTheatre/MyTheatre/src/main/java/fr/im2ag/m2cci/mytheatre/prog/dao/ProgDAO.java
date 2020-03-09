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
    private static final SimpleDateFormat horaireFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    
    public static List<Representation> representationsFiltrees(DataSource ds, Date horaireDebut, 
            Date horaireFin, String cibleSpe, String typeSpe) throws SQLException, ParseException{
        
        String queryRep = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep \n"
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe \n"
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe \n"
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe \n"
                + "WHERE horaireRep>? AND horaireRep<?";
        
        String queryDeBase = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep "
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe "
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe "
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe "
                + "WHERE horaireRep>? AND horaireRep<?;";
        
        String queryCible = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep "
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe "
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe "
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe "
                + "WHERE horaireRep>? AND horaireRep<? AND cibleSpe=?;";
        
        String queryType = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep "
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe "
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe "
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe "
                + "WHERE horaireRep>? AND horaireRep<? AND typeSpe=?;";
        
        String queryCibleType = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep "
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe "
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe "
                + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe "
                + "WHERE horaireRep>? AND horaireRep<? AND cibleSpe=? AND typeSpe=?;";
        
        List<Representation> representations = new ArrayList();
        
        try (Connection conn = ds.getConnection()){
            // Construction de la requête en fonction des filtres
            if (!cibleSpe.equals("null")){
                queryRep += " AND cibleSpe=?";
            }
            if (!typeSpe.equals("null")){
                queryRep += " AND typeSpe=?";
            }
            queryRep += "; \n";
            System.out.println(queryRep);
            
            PreparedStatement stmt = conn.prepareStatement(queryRep);
            
            // Preparation de la requete
            stmt.setString(1, horaireFormatter.format(horaireDebut));
            stmt.setString(2, horaireFormatter.format(horaireFin));
            if (!cibleSpe.equals("null") && !typeSpe.equals("null")){
                stmt.setString(3, cibleSpe);
                stmt.setString(4, typeSpe);
            } else if (!cibleSpe.equals("null")) {
                stmt.setString(3, cibleSpe);
            } else if (!typeSpe.equals("null")) {
                stmt.setString(3, typeSpe);
            } 
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    // Récupération des attributs
                    int numero = rs.getInt("numeroSpe");
                    String nom = rs.getString("nomSpe");
                    double prixDeBase = rs.getDouble("prixDeBaseSpe");
                    String cible = rs.getString("cibleSpe");
                    String type = rs.getString("typeSpe");
                    String horaireRep = rs.getString("horaireRep");
                    System.out.println(horaireRep);
                    
                    // Création des objets
                    Date horaire = horaireFormatter.parse(horaireRep);
                    Spectacle s;
                    switch(type){
                        case "opera":
                            int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                            boolean aUnOrchestre = (aUnOrchestreOpe == 1);
                            s = new Opera(numero, nom, prixDeBase, cible, type, aUnOrchestre);
                            break;
                        case "humoristique":
                            int estUnOneWomanManShowHum = rs.getInt("estUnOneWomanManShowHum");
                            boolean estUnOneWomanManShow = (estUnOneWomanManShowHum == 1);
                            s = new Humoristique(numero, nom, prixDeBase, cible, type, estUnOneWomanManShow);
                            break;
                        default:
                            s = new Spectacle(numero, nom, prixDeBase, cible, type); 
                    }
                    Representation rep = new Representation(horaire, s);
                    representations.add(rep) ;
                }
                
                /*// Pas possible de faire une requete sur la Date -> On retire les dates invalides du resultat
                for (Representation rep : representations){
                    if (rep.getDate().before(debut) || rep.getDate().after(fin)){
                        representations.remove(rep);
                    }
                }*/
            } 
        }
        return representations;
    }
}
