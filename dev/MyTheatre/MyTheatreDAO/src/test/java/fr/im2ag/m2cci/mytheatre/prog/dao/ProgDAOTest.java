/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author marti236
 */
public class ProgDAOTest {
    
    DataSource ds = new MockDataSource("org.sqlite.JDBC" , "jdbc:sqlite:/home/marti236/m2cci-1920-pi-GP02/bd/sql/bd.sqlite3", null, null);
    
    public ProgDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
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
//    @Test
//    public void testRepresentationsFiltrees() throws Exception {
//        System.out.println("representationsFiltrees");
//        DataSource ds = null;
//        Date horaireDebut = null;
//        Date horaireFin = null;
//        String cibleSpe = "";
//        List<String> typesSpe = null;
//        List<Representation> expResult = null;
//        List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
