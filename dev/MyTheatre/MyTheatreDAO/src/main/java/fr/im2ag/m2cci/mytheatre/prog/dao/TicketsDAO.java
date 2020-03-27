/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.im2ag.m2cci.mytheatre.prog.model.Ticket;
import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import fr.im2ag.m2cci.mytheatre.prog.model.Spectacle;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class TicketsDAO {

    private static final SimpleDateFormat horaireFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * Requête pour trouver les places déjà vendues ou reservées pour une
     * représentation donnée
     */
    private static final String PLACES_RESERVEES
            = "SELECT T.numeroPla, T.numeroRan, T.horaireRep"
            + " FROM LesTickets_base T "
            + "JOIN LesTicketsAchetes A ON (T.numeroTic=A.numeroTic) "
            + "JOIN LesRepresentations R ON (R.horaireRep = T.horaireRep) "
            + "JOIN LesSpectacles S ON (S.numeroSpe = R.numeroSpe) "
            + " WHERE loginUti = ?";
    /**
     * Requete pour supprimer une reservation
     */
    private static final String SUPPRIMER_RESERVATION
            = "DELETE FROM LesTicketsReserves WHERE numTic=?";

    /**
     * Requête pour trouver les places déjà vendues ou reservées pour une
     * représentation donnée
     */
    private static final String PLACES_VENDUES
            = "SELECT numeroPla, numeroRan FROM LesTickets_base WHERE horaireRep = ?";

    /**
     * Requête pour insérer les données dans la table LesTickets
     */
    private static final String CREER_TICKET
            = "INSERT INTO LesTickets_base ( horaireRep, numeroRan,  numeroPla,  numeroTic, dateEmissionTic, numeroDos ) VALUES (? ,?, ?, ?, ?, ?); ";
    /**
     * Requête pour insérer les données dans la table LesTicketsAchetes
     */
    private static final String ACHETER_TICKET
            = "INSERT INTO LesTicketsAchetes (numeroTic, loginUti ) VALUES (?, ?); ";

    /**
     * Requête pour insérer les données dans la table LesTicketsReserves
     */
    private static final String RESERVER_TICKET
            = "INSERT INTO LesTicketsReserves (numeroTic, loginUti ) VALUES (?, ?); ";

    /**
     * recherche, pour un client donné, la liste des places qui ont été achetées
     * par ce client et retourne une liste d'objets Place.
     *
     * @param ds la source de données pour les connexions JDBC
     * @param noClient
     * @return la liste des places déjà vendue pour le spectacle spectacleId
     * @throws SQLException si problème avec JDBC
     * @throws java.text.ParseException
     */
    public static List<Ticket> placesReserveesParClient(DataSource ds, String UserId) throws SQLException, ParseException {
        try (Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(PLACES_RESERVEES)) {
            pstmt.setString(1, UserId);
            ResultSet rs = pstmt.executeQuery();
            List<Ticket> places = new ArrayList<>();
            while (rs.next()) {
                Date dateRep = horaireFormatter.parse(rs.getString("horaireRep"));
                Spectacle spe = new Spectacle(rs.getInt("numeroSpe"), rs.getString("nomSpe"), rs.getDouble("prixDeBaseSpe"), rs.getString("cibleSpe"), rs.getString("typeSpe"));
                Representation rep = new Representation(dateRep, spe);
                places.add(new Ticket(rs.getString("userId"), rs.getInt("numTic"), rs.getInt("numPla"),
                        rs.getInt("numRan"), rep)
                );
            }
            return places;
        }
    }
/**
 * Achete des tickets reserves passés sous forme de liste: efface les entrees  
 * de la table LesTicketsReserves et ajoute une entrée dans la table LesTicketsAchetes
 * @param ds
 * @param places
 * @throws SQLException
 * @throws AchatConcurrentException 
 */
    public static void acheterPlacesReservees(DataSource ds, List<Ticket> places) throws SQLException, AchatConcurrentException {
        try (Connection conn = ds.getConnection()) {
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");
            try (PreparedStatement pstmt = conn.prepareStatement(SUPPRIMER_RESERVATION); PreparedStatement pstmt2 = conn.prepareStatement(ACHETER_TICKET)) {
                for (Ticket place : places) {
                    pstmt.setInt(1, place.getNumeroTicket());
                    pstmt2.setInt(1, place.getNumeroTicket());
                    pstmt2.setString(1, place.getUserId());
                    pstmt2.addBatch();
                    pstmt.addBatch();
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
                                throw new AchatConcurrentException("places déjà reservées ", ex);
                            }
                            break;
                        default:  // testé pour Oracle et PostgreSQL
                            ex = ex.getNextException();  // on prend la cause
                            if (ex instanceof SQLIntegrityConstraintViolationException
                                    || ex.getMessage().contains("pk_placesvendues")) {
                                // certains drivers ne supportent pas encore le type SQLIntegrityConstraintViolationException
                                throw new AchatConcurrentException("places déjà vendues ", ex);
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
     * recherche pour un spectacle donné la liste des places qui ont déja été
     * vendues et la retourne sous la forme d'une chaîne JSON.Par exemple si les
     * places vendues sont les places de numéros 1, 2, 43 et 154 le code JSON
     * retournée au client sera :
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
     * @param ds
     * @param horaireRepresentation
     * @return la chaîne JSON
     * @throws SQLException si problème avec JDBC
     */
    public static String placesVenduesAsJSON(DataSource ds, Date horaireRepresentation) throws SQLException {
        supprimerReservations(ds);//supprime les reservations qui ont plus de 24h
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
                            .write("placeId", rs.getInt("numeroPla"))
                            .write("rang", rs.getInt("numeroRan"))
                            .writeEnd();
                }
                gen.writeEnd()
                        .writeEnd();
            }
            return sw.toString();
        }
    }

    /**
     * Reservation de places, permet d'enregistrer dans la base de données les
     * places achetées et supprime les tickets qui on plus de 24h
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
    public static void reserverPlaces(DataSource ds, Date horaireRepresentation, int[] placesIds, int[] rangsIds) throws SQLException, AchatConcurrentException {

        Date dateCourante = new Date();
        String dateTick = horaireFormatter.format(dateCourante);//recupère la date courante pour avoir la pk des tickets reservés

        int numTicket = (int) dateCourante.getTime() / 1000;// le numéro du ticket est généré avec le nombre de secondes écoulées depuis le 01/01/1970

        //fait 2 insertions: une dans la table LesTickets et une dans LesTicketsReserves
        try (Connection conn = ds.getConnection()) {
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");
            try (PreparedStatement pstmt = conn.prepareStatement(CREER_TICKET); PreparedStatement pstmt2 = conn.prepareStatement(RESERVER_TICKET)) {
                conn.setAutoCommit(false);  // début d'une transaction
                for (int i = 0; i < rangsIds.length; i++) {
                    pstmt.setInt(4, numTicket + i);
                    pstmt.setString(5, dateTick);
                    pstmt.setString(1, horaireFormatter.format(horaireRepresentation));
                    pstmt.setInt(3, placesIds[i]);
                    pstmt.setInt(2, rangsIds[i]);
                    pstmt.setInt(6, 1);
                    pstmt2.setInt(1, numTicket + i);
                    pstmt2.setString(2, "nico58");
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
                                throw new AchatConcurrentException("places déjà reservées ", ex);
                            }
                            break;
                        default:  // testé pour Oracle et PostgreSQL
                            ex = ex.getNextException();  // on prend la cause
                            if (ex instanceof SQLIntegrityConstraintViolationException
                                    || ex.getMessage().contains("pk_placesvendues")) {
                                // certains drivers ne supportent pas encore le type SQLIntegrityConstraintViolationException
                                throw new AchatConcurrentException("places déjà reservées ", ex);
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
     * Supprime les tickets reservés qui ont plus de 24h
     *
     * @param ds
     * @throws SQLException
     */
    public static void supprimerReservations(DataSource ds) throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date dateLimite = cal.getTime();

        String queryRep = "DELETE FROM LesTickets_base WHERE dateEmissionTic <= ? AND numeroTic IN (SELECT numeroTic FROM LesTicketsReserves)";

        try (Connection conn = ds.getConnection()) {
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");
            PreparedStatement stmt = conn.prepareStatement(queryRep);
            stmt.setString(1, horaireFormatter.format(dateLimite));
            stmt.executeUpdate();
        }
    }

}
