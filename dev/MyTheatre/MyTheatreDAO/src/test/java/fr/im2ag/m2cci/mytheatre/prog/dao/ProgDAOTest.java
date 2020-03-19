/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.im2ag.m2cci.mytheatre.prog.model.Humoristique;
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

    static DataSource ds = new MockDataSource("org.sqlite.JDBC", "jdbc:sqlite:C:/MSys64/home/celia/m2cci-1920-pi-GP02/bd/sql/bd.sqlite3", null, null);

    public ProgDAOTest() {
    }

    private void readAndExecuteSqlNegative(String path) throws FileNotFoundException, SQLException {
        File input = new File(path);
        Scanner sc = new Scanner(input);
        sc.useDelimiter("^\n$|;(( *\n)|( *--.*\n))|--.*\n");
        try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement()) {
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
        assertEquals(6, result.size());
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

    @Test
    public void testAjoutSpectale() throws Exception {
        System.out.println("AjoutSpectacle");
        int numero = 1000;
        String nom = "A";
        Double prixDeBase = 10.0;
        String cible = "toutPublic";
        String type = "drame";
        Spectacle spe = new Spectacle(numero, nom, prixDeBase, type, cible);
        ProgDAO.ajoutSpectacle(ds, numero, nom, prixDeBase, cible, type, false, false);
        List<Spectacle> spectacles = ProgDAO.toutSpectacles(ds);
        int i = spectacles.indexOf(spe);
        assertEquals(spectacles.get(i), spe);

    }
    @Test
    public void testInsertRepresentation() throws Exception{
        System.out.println("insertRepresentation");
        int numeroSpe = 100 ; 
        Date horaireRep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-03-10 15:00");
        Representation rep = new Representation(horaireRep, spec);
        ProgDAO.insertRepresentation(ds, numeroSpe, horaireRep);
        List<Spectacle> spectacles = ProgDAO.toutSpectacles(ds);
        int i = spectacles.indexOf(numeroSpe);
        assertEquals(spectacles.get(i),numeroSpe);
        
       
    }
     @Test
    public void testInsertRepresentation() throws Exception {
        System.out.println("insertRepresentation");
        DataSource ds = null;
        int numeroSpe = 0;
        Date horaireRep = null;
        ProgDAO.insertRepresentation(ds, numeroSpe, horaireRep);
      
     
}
     List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
        Spectacle spec = new Spectacle(46, "Les Animaux", 8.0, "cirque", "unCinqAns");
        Representation rep = new Representation(dateRep, spec);
        assertEquals(rep, result.get(0));
}

     public void testDecrement() {
        System.out.println("decrement");
        Counter instance = new Counter();
        int expResult = 0;
        int result = instance.decrement();
        assertEquals(expResult, result);
        
   }
}