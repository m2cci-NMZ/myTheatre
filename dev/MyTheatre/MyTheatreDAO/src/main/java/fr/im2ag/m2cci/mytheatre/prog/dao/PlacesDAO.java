/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.im2ag.m2cci.mytheatre.prog.model.Place;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import javax.json.Json;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author nico
 */
public class PlacesDAO {

    private static final SimpleDateFormat horaireFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * Requête pour trouver les places déjà vendues pour une représentation
     * donnée
     */
    private static final String PLACES_VENDUES
            = "SELECT numPla, numRan FROM LesTickets WHERE horaireRep = ?";

    /**
     * Requête pour insérer les données dans la table LesTickets
     */
    private static final String CREER_TICKET
            = "INSERT INTO LesTickets (numTic, dateTic, horaireRep, prixTic, numPla, numRan, numDA ) VALUES (? ,?, ?, ?, ?, ?, ?); ";
    /**
     * Requête pour insérer les données dans la table LesTicketsAchetes
     */
    private static final String ACHETER_TICKET
            = "INSERT INTO LesTicketsAchetes (numTic, loginUti ) VALUES (?, ?); ";

    /**
     * Requête pour insérer les données dans la table LesTicketsAchetes
     */
    private static final String RESERVER_TICKET
            = "INSERT INTO LesTicketsReserves (numTic, loginUti ) VALUES (?, ?); ";

    /**
     * recherche, pour un spectacle donné, la liste des places qui ont déja été
     * vendues et retourne une liste d'objets Place.
     *
     * @param ds la source de données pour les connexions JDBC
     * @param spectacleId l'identifiant du spectacle
     * @return la liste des places déjà vendue pour le spectacle spectacleId
     * @throws SQLException si problème avec JDBC
     */
    public static List<Place> placesVendues(DataSource ds, Date horaireRepresentation) throws SQLException {
        try (Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(PLACES_VENDUES)) {
            pstmt.setString(1, horaireFormatter.format(horaireRepresentation));
            ResultSet rs = pstmt.executeQuery();
            List<Place> places = new ArrayList<>();
            while (rs.next()) {
                places.add(new Place(rs.getInt("numPla"),
                        rs.getInt("numRan"))
                );
            }
            return places;
        }
    }

    /**
     * recherche pour un spectacle donné la liste des places qui ont déja été
     * vendues et la retourne sous la forme d'une chaîne JSON.
     *
     * Par exemple si les places vendues sont les places de numéros 1, 2, 43 et
     * 154 le code JSON retournée au client sera :
     * <pre>
     * {"placesVendues":[
     *    {"placeId":1,"rang":1,"colonne":3},
     *    {"placeId":2,"rang":1,"colonne":4},
     *    {"placeId":43,"rang":2,"colonne":19},
     *    {"placeId":71,"rang":3,"colonne":19},{"placeId":100,"rang":4,"colonne":20},
     *    {"placeId":154,"rang":7,"colonne":15},
     *   ]
     * }
     * </pre> * @param ds la source de données pour les connexions JDBC
     *
     * @param spectacleId l'identifiant du spectacle
     * @return la chaîne JSON
     * @throws SQLException si problème avec JDBC
     */
    public static String placesVenduesAsJSON(DataSource ds, Date horaireRepresentation) throws SQLException {
        try (Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(PLACES_VENDUES)) {
            pstmt.setString(1, horaireFormatter.format(horaireRepresentation));
            ResultSet rs = pstmt.executeQuery();
            StringWriter sw = new StringWriter();
            try (JsonGenerator gen = Json.createGenerator(sw)) {
                gen.writeStartObject()
                        .writeStartArray("placesVendues");
                while (rs.next()) {
                    gen.writeStartObject()
                            .write("placeId", rs.getInt("numPla"))
                            .write("rang", rs.getInt("numRan"))
                            .writeEnd();
                }
                gen.writeEnd()
                        .writeEnd();
            }
            return sw.toString();
        }
    }

    /**
     * achat de places.Permet d'enregistrer dans la base de données les places
     * achetées.
     *
     * @param ds la source de données pour les connexions JDBC
     * @param horaireRepresentation l'horaire de la representation
     * @param placesIds les identifiants du numéro de place acheté
     * @param rangsIds les identifiants du numéro de rang acheté
     * @param prixTic le prix du ticket
     * @throws SQLException SQLException si problème avec JDBC
     * @throws fr.im2ag.m2cci.mytheatre.prog.dao.AchatConcurrentException si une
     * place a déjà été achetée
     */
    public static void acheterPlaces(DataSource ds, Date horaireRepresentation, int[] placesIds, int[] rangsIds, double prixTic) throws SQLException, AchatConcurrentException {
        int numTicket = numeroDernierTicket(ds);
        Date dateCourante = new Date();
        String dateTick = horaireFormatter.format(dateCourante);
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(CREER_TICKET); PreparedStatement pstmt2 = conn.prepareStatement(ACHETER_TICKET)) {
                conn.setAutoCommit(false);  // début d'une transaction
                for (int i = 0; i < rangsIds.length; i++) {
                    pstmt.setInt(1, numTicket + 1 + i);
                    pstmt.setString(2, dateTick);
                    pstmt.setString(3, horaireFormatter.format(horaireRepresentation));
                    pstmt.setDouble(4, prixTic);
                    pstmt.setInt(5, placesIds[i]);
                    pstmt.setInt(6, rangsIds[i]);
                    pstmt.setInt(7, 1);
                    pstmt2.setInt(1, numTicket + 1 + i);
                    pstmt2.setInt(2, 1);
                    pstmt2.addBatch();
                    pstmt.addBatch();  // ajoute la requête d'insertion au batch
                }

                pstmt.executeBatch();  // exécute les requêtes d'insertion
                pstmt2.executeBatch();
                conn.commit();   // valide la transaction
            } catch (SQLException ex) {
                ex.printStackTrace();
                if (conn.getAutoCommit() == false) {
                    conn.rollback();   // annule la transaction 
                    // vérifie si l'erreur est liée à la contrainte PK_PlacesVendues
                    // dans ce cas une exception AchatConcurrentException est relancée
                    switch (conn.getMetaData().getDatabaseProductName()) {
                        case "SQLite":
                            if (ex.getMessage().contains("PRIMARY KEY constraint failed")) {
                                throw new AchatConcurrentException("places déjà achetées ", ex);
                            }
                            break;
                        default:  // testé pour Oracle et PostgreSQL
                            ex = ex.getNextException();  // on prend la cause
                            if (ex instanceof SQLIntegrityConstraintViolationException
                                    || ex.getMessage().contains("pk_placesvendues")) {
                                // certains drivers ne supportent pas encore le type SQLIntegrityConstraintViolationException
                                throw new AchatConcurrentException("places déjà achetées ", ex);
                            }

                    }
                }
                // l'exception ne concerne pas la contrainte PK_PlacesVendues  
                // elle est relancée telle quelle
                throw ex;

            } finally {
                if (conn != null) {
                    conn.setAutoCommit(true); // remet la connexion en mode autocommit
                }
            }
        }
    }
    /**
     * reservation de places.Permet d'enregistrer dans la base de données les places
     * achetées.
     *
     * @param ds la source de données pour les connexions JDBC
     * @param horaireRepresentation l'horaire de la representation
     * @param placesIds les identifiants du numéro de place acheté
     * @param rangsIds les identifiants du numéro de rang acheté
     * @param prixTic le prix du ticket
     * @throws SQLException SQLException si problème avec JDBC
     * @throws fr.im2ag.m2cci.mytheatre.prog.dao.AchatConcurrentException si une
     * place a déjà été achetée
     */
    public static void reserverPlaces(DataSource ds, Date horaireRepresentation, int[] placesIds, int[] rangsIds, double prixTic) throws SQLException, AchatConcurrentException {
        int numTicket = numeroDernierTicket(ds);
        Date dateCourante = new Date();
        String dateTick = horaireFormatter.format(dateCourante);
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(CREER_TICKET); PreparedStatement pstmt2 = conn.prepareStatement(RESERVER_TICKET)) {
                conn.setAutoCommit(false);  // début d'une transaction
                for (int i = 0; i < rangsIds.length; i++) {
                    pstmt.setInt(1, numTicket + 1 + i);
                    pstmt.setString(2, dateTick);
                    pstmt.setString(3, horaireFormatter.format(horaireRepresentation));
                    pstmt.setDouble(4, prixTic);
                    pstmt.setInt(5, placesIds[i]);
                    pstmt.setInt(6, rangsIds[i]);
                    pstmt.setInt(7, 1);
                    pstmt2.setInt(1, numTicket + 1 + i);
                    pstmt2.setInt(2, 1);
                    pstmt2.addBatch();
                    pstmt.addBatch();  // ajoute la requête d'insertion au batch
                }

                pstmt.executeBatch();  // exécute les requêtes d'insertion
                pstmt2.executeBatch();
                conn.commit();   // valide la transaction
            } catch (SQLException ex) {
                ex.printStackTrace();
                if (conn.getAutoCommit() == false) {
                    conn.rollback();   // annule la transaction 
                    // vérifie si l'erreur est liée à la contrainte PK_PlacesVendues
                    // dans ce cas une exception AchatConcurrentException est relancée
                    switch (conn.getMetaData().getDatabaseProductName()) {
                        case "SQLite":
                            if (ex.getMessage().contains("PRIMARY KEY constraint failed")) {
                                throw new AchatConcurrentException("places déjà achetées ", ex);
                            }
                            break;
                        default:  // testé pour Oracle et PostgreSQL
                            ex = ex.getNextException();  // on prend la cause
                            if (ex instanceof SQLIntegrityConstraintViolationException
                                    || ex.getMessage().contains("pk_placesvendues")) {
                                // certains drivers ne supportent pas encore le type SQLIntegrityConstraintViolationException
                                throw new AchatConcurrentException("places déjà achetées ", ex);
                            }

                    }
                }
                // l'exception ne concerne pas la contrainte PK_PlacesVendues  
                // elle est relancée telle quelle
                throw ex;

            } finally {
                if (conn != null) {
                    conn.setAutoCommit(true); // remet la connexion en mode autocommit
                }
            }
        }
    }

    /**
     * Methode permettant d'avoir acces au dernier numero de ticket disponible
     *
     * @param ds
     * @return
     * @throws SQLException
     */
    public static int numeroDernierTicket(DataSource ds) throws SQLException {
        String queryRep = "SELECT numTic FROM LesTickets";
        List<Integer> numTickets = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(queryRep);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    numTickets.add(rs.getInt("numTic"));
                }
            }
        }
        Collections.sort(numTickets);
        if (numTickets.size() == 0) {
            return 0;
        } else {
            return numTickets.get(numTickets.size() - 1);
        }
    }
}
