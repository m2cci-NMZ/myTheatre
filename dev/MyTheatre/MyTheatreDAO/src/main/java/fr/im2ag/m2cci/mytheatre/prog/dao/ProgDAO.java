/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.im2ag.m2cci.mytheatre.prog.model.Humoristique;
import fr.im2ag.m2cci.mytheatre.prog.model.Opera;
import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import fr.im2ag.m2cci.mytheatre.prog.model.Spectacle;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private static final SimpleDateFormat horaireFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat horaireFinFormatter = new SimpleDateFormat("yyyy-MM-dd 23:59");   // La date de fin doit être inclue en entier

    /**
     * Permet de faire une requete sur les `Representation`s selon des filtres
     * passés en paramètres
     *
     * @param ds
     * @param horaireDebut
     * @param horaireFin
     * @param cibleSpe
     * @param typesSpe
     * @return Une liste de Representations
     * @throws SQLException
     * @throws ParseException
     */
    public static List<Representation> representationsFiltrees(DataSource ds, Date horaireDebut,
            Date horaireFin, String cibleSpe, List<String> typesSpe) throws SQLException, ParseException {

        List<Representation> representations = new ArrayList<>();

        if (!typesSpe.isEmpty()) {
            // Si la liste des types de Spectacles et vide, la requete ne retournera rien, on ne la fait donc pas

            String queryRep = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, horaireRep \n"
                    + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe \n"
                    + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe \n"
                    + "JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe \n"
                    + "WHERE horaireRep>=? AND horaireRep<=?";

            try (Connection conn = ds.getConnection()) {
                // Construction de la requête en fonction des filtres
                // Cible
                if (!cibleSpe.equals("null")) {
                    queryRep += " AND cibleSpe=?";
                }
                // Types de spectacle
                queryRep += " AND (typeSpe=?";
                for (int i = 1; i < typesSpe.size(); i++) {
                    queryRep += " OR typeSpe=?";
                }
                queryRep += ")\n";

                // Ajout d'un ordre
                queryRep += " ORDER BY horaireRep; \n";

                PreparedStatement stmt = conn.prepareStatement(queryRep);

                // Preparation de la requete
                stmt.setString(1, horaireFormatter.format(horaireDebut));
                stmt.setString(2, horaireFinFormatter.format(horaireFin));
                int iQuery = 3;     // Permet de garder l'indice d'ajout du PreparedStatement
                if (!cibleSpe.equals("null")) {
                    stmt.setString(3, cibleSpe);
                    iQuery++;
                }
                for (int i = 0; i < typesSpe.size(); i++) {
                    stmt.setString(iQuery + i, typesSpe.get(i));
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

                        // Création des objets
                        Date horaire = horaireFormatter.parse(horaireRep);
                        Spectacle s;
                        switch (type) {
                            case "opera":
                                int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                                boolean aUnOrchestre = (aUnOrchestreOpe == 1);
                                s = new Opera(numero, nom, prixDeBase, type, cible, aUnOrchestre);
                                break;
                            case "humoristique":
                                int estUnOneWomanManShowHum = rs.getInt("estUnOneWomanManShowHum");
                                boolean estUnOneWomanManShow = (estUnOneWomanManShowHum == 1);
                                s = new Humoristique(numero, nom, prixDeBase, type, cible, estUnOneWomanManShow);
                                break;
                            default:
                                s = new Spectacle(numero, nom, prixDeBase, type, cible);
                        }
                        Representation rep = new Representation(horaire, s);
                        representations.add(rep);
                    }
                }
            }
        }

        return representations;
    }

    /**
     * Permet de récupérer la liste de tous les Spectacle dans la base
     * 
     * @param ds : Datasource
     * @return List de Spectacle
     * @throws SQLException 
     */
    public static List<Spectacle> toutSpectacles(DataSource ds) throws SQLException {
        String querySpe = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe \n"
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe \n"
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe \n"
                + "ORDER BY nomSpe; \n";

        List<Spectacle> spectacles = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();

            try (ResultSet rs = stmt.executeQuery(querySpe)) {
                while (rs.next()) {
                    // Récupération des attributs
                    int numero = rs.getInt("numeroSpe");
                    String nom = rs.getString("nomSpe");
                    double prixDeBase = rs.getDouble("prixDeBaseSpe");
                    String cible = rs.getString("cibleSpe");
                    String type = rs.getString("typeSpe");

                    // Création des objets
                    Spectacle spec;
                    switch (type) {
                        case "opera":
                            int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                            boolean aUnOrchestre = (aUnOrchestreOpe == 1);
                            spec = new Opera(numero, nom, prixDeBase, type, cible, aUnOrchestre);
                            break;
                        case "humoristique":
                            int estUnOneWomanManShowHum = rs.getInt("estUnOneWomanManShowHum");
                            boolean estUnOneWomanManShow = (estUnOneWomanManShowHum == 1);
                            spec = new Humoristique(numero, nom, prixDeBase, type, cible, estUnOneWomanManShow);
                            break;
                        default:
                            spec = new Spectacle(numero, nom, prixDeBase, type, cible);
                    }
                    spectacles.add(spec);
                }
            }
        }
        return spectacles;
    }

    /**
     * Retourne la liste de toutes les Representations entre les deux Dates
     * passées en paramètre Appelle representationsFiltrees en mettant dans
     * cibleSpe et typeSpe les valeurs adaptées permettant de récupérer toutes
     * les Spectacles possibles
     *
     * @param ds : DataSource
     * @param horaireDebut : Date
     * @param horaireFin : Date
     * @return : Liste de Representation
     * @throws SQLException
     * @throws ParseException
     */
    public static List<Representation> toutesRepresentationsDatees(DataSource ds, Date horaireDebut,
            Date horaireFin) throws SQLException, ParseException {
        String cibleSpe = "null";
        List<String> typesSpe = new ArrayList<>();
        typesSpe.add("opera");
        typesSpe.add("humoristique");
        typesSpe.add("drame");
        typesSpe.add("musical");
        typesSpe.add("cirque");

        return representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
    }

    /**
     * Retourne un Spectacle correspondant au numeroSpe en paramètre Si la BD ne
     * contient pas ce numeroSpe, alors on retourne null
     *
     * @param ds : DataSource
     * @param numeroSpe : numero du Spectacle
     * @return Spectacle : null ou correspondant au Spectacle
     * @throws SQLException
     */
    public static Spectacle spectacleDeNumero(DataSource ds, int numeroSpe) throws SQLException {
        Spectacle s = null;

        String querySpe = "SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe \n"
                + "FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe \n"
                + "LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe \n"
                + "WHERE S.numeroSpe = ? \n"
                + "ORDER BY nomSpe; \n";

        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(querySpe);
            stmt.setInt(1, numeroSpe);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Récupération des attributs
                    int numero = rs.getInt("numeroSpe");
                    String nom = rs.getString("nomSpe");
                    double prixDeBase = rs.getDouble("prixDeBaseSpe");
                    String cible = rs.getString("cibleSpe");
                    String type = rs.getString("typeSpe");

                    // Création des objets
                    switch (type) {
                        case "opera":
                            int aUnOrchestreOpe = rs.getInt("aUnOrchestreOpe");
                            boolean aUnOrchestre = (aUnOrchestreOpe == 1);
                            s = new Opera(numero, nom, prixDeBase, type, cible, aUnOrchestre);
                            break;
                        case "humoristique":
                            int estUnOneWomanManShowHum = rs.getInt("estUnOneWomanManShowHum");
                            boolean estUnOneWomanManShow = (estUnOneWomanManShowHum == 1);
                            s = new Humoristique(numero, nom, prixDeBase, type, cible, estUnOneWomanManShow);
                            break;
                        default:
                            s = new Spectacle(numero, nom, prixDeBase, type, cible);
                    }
                }
            }
        }

        return s;
    }

    /**
     * Permet d'insérer un spectacle dans la base de données
     *
     * @param ds : Datasource pour la BD
     * @param numero : int pour le numero du Spectacle
     * @param nom : String pour le nom du Spectacle
     * @param prixDeBase : double pour le prix du base du Spectacle
     * @param cible : String pour le public cible du Spectacle
     * @param type : String pour le type de Spectacle
     * @param aOrchestreOuEstOneWomanManShow : boolean pour les Spectacle de
     * type 'opera' ou 'humoristique', permet de signaler qu'il y a un orchestre
     * ou respectivement que c'est un OneWomanManShow
     * @throws SQLException
     */
    public static void insertSpectacle(DataSource ds, int numero, String nom, double prixDeBase, String cible, String type,
            boolean aOrchestreOuEstOneWomanManShow) throws SQLException {
        // todo : Traiter le cas des opera et des humoristique
        String queryInsert = "INSERT INTO LesSpectacles VALUES (?, ?, ?, ?, ?); ";

        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(queryInsert);
            stmt.setInt(1, numero);
            stmt.setString(2, nom);
            stmt.setDouble(3, prixDeBase);
            stmt.setString(4, cible);
            stmt.setString(5, type);

            if (type.equals("opera") || type.equals("humoristique")) {

                try {
                    conn.setAutoCommit(false);  // On fait les deux INSERT de manière atomique
                    stmt.executeUpdate();

                    // Insertion dans les tables LesOperas ou LesHumoristes
                    if (type.equals("opera")) {
                        queryInsert = "INSERT INTO LesOperas VALUES (?, ?); ";
                    } else {
                        queryInsert = "INSERT INTO LesHumoristiques VALUES (?, ?); ";
                    }
                    // Préparation de la requete
                    stmt = conn.prepareStatement(queryInsert);
                    stmt.setInt(1, numero);
                    if (aOrchestreOuEstOneWomanManShow) {
                        stmt.setInt(2, 1);
                    } else {
                        stmt.setInt(2, 0);
                    }

                    stmt.executeUpdate();
                    conn.commit();      // On commit toutes les modifications
                    conn.setAutoCommit(true);  // On remet en mode auto-commit
                } catch (SQLException e){
                    conn.rollback();    // Annule les opérations de la transaction
                    throw e;
                }
            } else {
                stmt.executeUpdate();   // Si il n'y a pas de double INSERT 
            }
        }
    }

    /**
     * Permet d'insérer une representation dans la base de données
     * 
     * @param ds : Datasource pour la BD
     * @param numeroSpe : int pour le numero du Spectacle
     * @param horaireRep : Date pour l'horaire (précise à la minute) du la
     * Representation
     * @throws SQLException
     */
    public static void insertRepresentation(DataSource ds, int numeroSpe, Date horaireRep) throws SQLException {
        String queryInsert = "INSERT INTO LesRepresentations VALUES (?, ?);";

        try (Connection conn = ds.getConnection()) {
            // Active les foreign key
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");

            // Fait l'insertion des données
            PreparedStatement stmt = conn.prepareStatement(queryInsert);
            stmt.setString(1, horaireFormatter.format(horaireRep));
            stmt.setInt(2, numeroSpe);

            stmt.executeUpdate();
        }
    }
    
    /**
     * Supprime tous les Spectacles dont les numéros sont données en paramètre
     * 
     * @param ds : Datasource
     * @param numerosDeSpe : List d'Integer correspondant au numéro de Spectacle à supprimer
     * @throws SQLException 
     */
    public static void deleteSpectacles(DataSource ds, List<Integer> numerosDeSpe) throws SQLException {
        // todo : Traiter le cas des opera et des humoristique
        String queryInsert = "DELETE FROM LesSpectacles WHERE numeroSpe = ?; ";

        try (Connection conn = ds.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(queryInsert);
            
            for (Integer numeroSpe : numerosDeSpe){
                stmt.setInt(1, numeroSpe);
                stmt.addBatch();
            }
            
            int [] updateCounts = stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        }
    }
}
