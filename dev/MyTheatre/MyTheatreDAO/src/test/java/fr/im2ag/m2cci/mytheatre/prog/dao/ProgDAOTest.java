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
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author marti236
 */
public class ProgDAOTest {

   

    
    static DataSource ds = new MockDataSource();
   
    public ProgDAOTest() {
    }

    private void readAndExecuteSqlNegative(String path) throws FileNotFoundException, SQLException {
        File input = new File(path);
        Scanner sc = new Scanner(input);
        sc.useDelimiter("^\n$|;(( *\n)|( *--.*\n))|--.*\n");
        try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
            //conn.setAutoCommit(false);
            while (sc.hasNext()) {
                String sqlQuery = sc.next().trim();

                if (!(sqlQuery.isEmpty() || sqlQuery.startsWith("\n"))) {
                    //stmt.addBatch(sqlQuery);
//                    System.out.println("##### ");
//                    System.out.println(sqlQuery);
                    stmt.executeUpdate(sqlQuery);
                }
            }
            // stmt.executeBatch();
            // conn.commit();
        }
    }

    @BeforeAll
    public static void setUpClass() throws FileNotFoundException, SQLException {
        File input = new File("bd/dataTest.sql");
        Scanner sc = new Scanner(input);
        sc.useDelimiter("^\n$|;(( *\n)|( *--.*\n))|--.*\n");
        try (Connection conn = ds.getConnection()) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(false);
                while (sc.hasNext()) {
                    String sqlQuery = sc.next().trim();

                    if (!(sqlQuery.isEmpty() || sqlQuery.startsWith("\n"))) {
                        stmt.addBatch(sqlQuery);
                    }
                }
                stmt.executeBatch();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @AfterAll
    public static void tearDownClass() throws FileNotFoundException, SQLException {
        File input = new File("bd/dataTest.sql");
        Scanner sc = new Scanner(input);
        sc.useDelimiter("^\n$|;(( *\n)|( *--.*\n))|--.*\n");
        try (Connection conn = ds.getConnection()) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(false);
                while (sc.hasNext()) {
                    String sqlQuery = sc.next().trim();

                    if (!(sqlQuery.isEmpty() || sqlQuery.startsWith("\n"))) {
                        stmt.addBatch(sqlQuery);
                    }
                }
                stmt.executeBatch();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testRepresentationsUneRequetePremierResultat() throws Exception {
        System.out.println("representationsFiltreesPremierResultat");
        Date horaireDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date horaireFin = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-30");
        Date dateRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-03-13 18:00");
        String cibleSpe = "null";
        List<String> typesSpe = new ArrayList<>();
        typesSpe.add("drame");
        typesSpe.add("musical");
        typesSpe.add("cirque");
        typesSpe.add("opera");
        typesSpe.add("humoristique");
        List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
        Spectacle spec = new Spectacle(46, "Les Animaux", 8.0, "cirque", "unCinqAns");
        Representation rep = new Representation(dateRep, spec);
        assertEquals(rep, result.get(0));
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testRepresentationsUneRequeteDernierResultat() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");
        Date horaireDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date horaireFin = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-30");
        Date dateRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-03-15 20:00");
        String cibleSpe = "null";
        List<String> typesSpe = new ArrayList<>();
        typesSpe.add("drame");
        typesSpe.add("musical");
        typesSpe.add("cirque");
        typesSpe.add("opera");
        typesSpe.add("humoristique");
        List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
        Spectacle spec = new Humoristique(20, "L'avare", 10.0, "humoristique", "toutPublic", false);
        Representation rep = new Representation(dateRep, spec);
        assertEquals(rep, result.get(5));
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testViolationPkLesSpectacles() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn2.sql");
            Assertions.fail("Insertion en violation de la pk_lesspectacles réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testViolationCkLesSpectaclesNumeroSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn3.sql");
            Assertions.fail("Insertion d'un numero de spectacle negatif réussi");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testViolationCkLesSpectaclesPrixDeBaseSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn4.sql");
            Assertions.fail("Insertion d'un prix de base negatif réussi");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testViolationCKLesSpectaclesCibleSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn5.sql");
            Assertions.fail("Insertion d'un prix d'un public cible non autorisé réussi");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testViolationCKLesSpectaclesTypeSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn6.sql");
            Assertions.fail("Insertion d'un prix d'un type de spectacle non autorisé réussi");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testFKLesHumoristiquesNumSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn1.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testFKLesOperasNumSpe() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn7.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testCKLesHumoristiquesEstUnOneWomanShow() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn8.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }

    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testCKLesOperasAUnOrchestre() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn9.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }
    }
    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testPKLesRepresentations() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn10.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testFKLesRepresentations() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");

        try {
            this.readAndExecuteSqlNegative("bd/jddn11.sql");
            Assertions.fail("Insertion negative réussie");

        } catch (SQLException ex) {
            assertEquals(19, ex.getErrorCode());
        }
    }

    /**
     * Tests pour insertSpectacle pour un drame
     * @throws Exception 
     */
    @Test
    public void testInsertSpectale() throws Exception {
        System.out.println("insertSpectacle");
        int numero = 1000;
        String nom = "Un Spectacle";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "drame";
        Spectacle s1 = new Spectacle(numero, nom, prixDeBase, type, cible);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, false);
        Spectacle s2 = ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(s1, s2);
    }
    
    /**
     * Tests pour insertSpectacle pour un drame avec le boolean a vrai (alors qu'on s'en fou)
     * On regarde le comportement si le booléen est à vrai
     * On check si ça devient un Opera ou un Humoristique
     * @throws Exception 
     */
    @Test
    public void testInsertSpectaleBoolVrai() throws Exception {
        System.out.println("insertSpectacle");
        int numero = 999;
        String nom = "Un Spectacle";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "drame";
        Spectacle s1 = new Spectacle(numero, nom, prixDeBase, type, cible);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, true);
        
        // Test du downcast vers un Opera
        try {
            Opera o = (Opera) ProgDAO.spectacleDeNumero(ds, numero);
            Assertions.fail("Ce n'est pas un Opera");
        } catch (ClassCastException e){
            // Normal
        }
        
        // Test du downcast vers un Humoristique
        try {
            Humoristique h = (Humoristique) ProgDAO.spectacleDeNumero(ds, numero);
            Assertions.fail("Ce n'est pas un Humoristique");
        } catch (ClassCastException e){
            // Normal
        }
        
        Spectacle s2 = ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(s1, s2);
    }
    
    /**
     * Tests pour insertSpectacle pour un Opera
     * @throws Exception
     */
    @Test
    public void testInsertOpera() throws Exception {
        System.out.println("insertSpectacle pour Opera sans Orchestre");
        int numero = 1001;
        String nom = "Un Opera";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "opera";
        boolean aUnOrchestre = false;
        Opera o1 = new Opera(numero, nom, prixDeBase, type, cible, aUnOrchestre);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, aUnOrchestre);
        Opera o2 = (Opera) ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(o1, o2);
    }
    
    /**
     * Tests pour insertSpectacle pour un Opera avec orchestre
     * @throws Exception 
     */
    @Test
    public void testInsertOperaOrchestre() throws Exception {
        System.out.println("insertSpectacle pour Opera avec Orchestre");
        int numero = 1002;
        String nom = "Un Opera Avec Orchestre";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "opera";
        boolean aUnOrchestre = true;
        Opera o1 = new Opera(numero, nom, prixDeBase, type, cible, aUnOrchestre);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, aUnOrchestre);
        Opera o2 = (Opera) ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(o1, o2);
    }
    
    /**
     * Tests pour insertSpectacle pour un Humoristique
     * @throws Exception 
     */
    @Test
    public void testInsertHumoristique() throws Exception {
        System.out.println("insertSpectacle pour Humoristique pas OneWomanShow");
        int numero = 1003;
        String nom = "Humour";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "humoristique";
        boolean aUnOrchestre = false;
        Humoristique h1 = new Humoristique(numero, nom, prixDeBase, type, cible, aUnOrchestre);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, aUnOrchestre);
        Humoristique h2 = (Humoristique) ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(h1, h2);
    }
    
    /**
     * Tests pour insertSpectacle pour un Humoristique OneWomanShow
     * @throws Exception 
     */
    @Test
    public void testInsertHumoristiqueStandUp() throws Exception {
        System.out.println("insertSpectacle pour Humoristique OneWomanShow");
        int numero = 1004;
        String nom = "Humour Stand-Up";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "humoristique";
        boolean aUnOrchestre = true;
        Humoristique h1 = new Humoristique(numero, nom, prixDeBase, type, cible, aUnOrchestre);
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, aUnOrchestre);
        Humoristique h2 = (Humoristique) ProgDAO.spectacleDeNumero(ds, numero);
        assertEquals(h1, h2);
    }

    @Test
    public void testInsertRepresentation() throws Exception {
        System.out.println("insertRepresentation");
        int numero = 17;
        String nom = "Andromaque";
        Double prixDeBase = 15.0;
        String cible = "adulte";
        String type = "drame";
        Date horaireRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-03-16 18:00");
        Spectacle spe = new Spectacle(numero, nom, prixDeBase, type, cible);
        Representation rep = new Representation(horaireRep, spe);
        ProgDAO.insertRepresentation(ds, numero, horaireRep);
        List<Representation> representations = ProgDAO.toutesRepresentationsDatees(ds, horaireRep, horaireRep);

        assertEquals(representations.get(0), rep);

    }


    @Test
    public void testNbTotalRep() throws Exception {
        System.out.println("representationsFiltrees");
        Date horaireDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date horaireFin = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-30");
        String cibleSpe = "null";
        List<String> typesSpe = new ArrayList<>();
        typesSpe.add("drame");
        typesSpe.add("musical");
        typesSpe.add("cirque");
        typesSpe.add("opera");
        typesSpe.add("humoristique");
        List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
        assertEquals(7, result.size());
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testToutSpectaclesDernierResultat() throws Exception {
        System.out.println("representationsFiltreesDernierResultat");
        int numero = 3000;
        String nom = "Zzzzzzzzzzzzz";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "humoristique";
        boolean estUnOneWomanShow = true;
        ProgDAO.insertSpectacle(ds, numero, nom, prixDeBase, cible, type, estUnOneWomanShow);
        List<Spectacle> result = ProgDAO.toutSpectacles(ds);
        Humoristique h1 = new Humoristique(numero, nom, prixDeBase, type, cible, estUnOneWomanShow);
        Humoristique h2 = (Humoristique) result.get(result.size()-1);
        assertEquals(h1, h2);
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testNbTotalToutSpectacles() throws Exception {
        System.out.println("toutSpectacles");
        List<Spectacle> resultInit = ProgDAO.toutSpectacles(ds);
        ProgDAO.insertSpectacle(ds, 2000, "Opera Orchestre", 10, "adulte", "opera", true);
        ProgDAO.insertSpectacle(ds, 2001, "Opera sans Orchestre", 10, "jeunePublic", "opera", false);
        ProgDAO.insertSpectacle(ds, 2002, "Humour Stand-Up", 10, "toutPublic", "humoristique", true);
        ProgDAO.insertSpectacle(ds, 2003, "Humour Enfant", 10, "unCinqAns", "humoristique", false);
        ProgDAO.insertSpectacle(ds, 2004, "Cirque", 10, "toutPublic", "cirque", false);
        ProgDAO.insertSpectacle(ds, 2005, "Drame Adulte", 10, "adulte", "drame", true);
        List<Spectacle> resultEnd = ProgDAO.toutSpectacles(ds);
        assertEquals(resultEnd.size(), resultInit.size()+6);
    }
    
    
    /**
     * Test de la méthode deleteSpectacles pour un seul Spectacle
     */
    @Test
    public void testDelete1Spectacle() throws Exception {
        System.out.println("deleteSpectacles pour un spectacle");
       
        ProgDAO.insertSpectacle(ds, 700, "Spectacle 1", 10, "adulte", "drame", false);
        List<Spectacle> spectaclesDebut = ProgDAO.toutSpectacles(ds);
        
        List<Integer> speASuppr = new ArrayList<>();
        speASuppr.add(700);
        ProgDAO.deleteSpectacles(ds, speASuppr);
        
        List<Spectacle> spectaclesFin = ProgDAO.toutSpectacles(ds);
        
        assertEquals(spectaclesFin.size(), spectaclesDebut.size()-1);
    }
    
    
//    /**
//     * Test de la méthode deleteSpectacles pour plusieurs Spectacle
//     */
//    @Test
//    public void testDeletePlusieursSpectacles() throws Exception {
//        System.out.println("deleteSpectacles pour plusieurs spectacles");
//       
//        ProgDAO.insertSpectacle(ds, 701, "Spectacle 5", 10, "toutPublic", "drame", false);
//        ProgDAO.insertSpectacle(ds, 702, "Spectacle 28", 10, "adulte", "opera", false);
//        ProgDAO.insertSpectacle(ds, 703, "Spectacle 12", 10, "unCinqAns", "musical", false);
//        List<Spectacle> spectaclesDebut = ProgDAO.toutSpectacles(ds);
//        
//        List<Integer> speASuppr = new ArrayList<>();
//        speASuppr.add(701);
//        speASuppr.add(702);
//        speASuppr.add(703);
//        ProgDAO.deleteSpectacles(ds, speASuppr);
//        
//        List<Spectacle> spectaclesFin = ProgDAO.toutSpectacles(ds);
//        
//        assertEquals(spectaclesFin.size(), spectaclesDebut.size()-3);
//    }
//    
//    
//    /**
//     * Test de la méthode deleteSpectacles pour un Spectacle ayant des Representations
//     */
//    @Test
//    public void testDeleteSpectacleAvecRepresentation() throws Exception {
//        System.out.println("deleteSpectacles pour un spectacle ayant des representations");
//        // Ajout du Spectacle
//        ProgDAO.insertSpectacle(ds, 704, "Spectacle 5", 10, "toutPublic", "drame", false);
//        // Ajout de la Representation
//        Date horaireRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-03-16 19:35");
//        ProgDAO.insertRepresentation(ds, 704, horaireRep);
//        // On récupère les Representations de ce jour
//        List<Representation> representationsDebut = ProgDAO.toutesRepresentationsDatees(ds, horaireRep, horaireRep);
//        // On supprime le Spectacle
//        List<Integer> speASuppr = new ArrayList<>();
//        speASuppr.add(704);
//        ProgDAO.deleteSpectacles(ds, speASuppr);
//        // On récupère les Representations de ce jour après la suppresion
//        List<Representation> representationsFin = ProgDAO.toutesRepresentationsDatees(ds, horaireRep, horaireRep);
//        // On compare si il y a bien une Representation en moins
//        assertEquals(representationsFin.size(), representationsDebut.size()-1);
//    }
}
