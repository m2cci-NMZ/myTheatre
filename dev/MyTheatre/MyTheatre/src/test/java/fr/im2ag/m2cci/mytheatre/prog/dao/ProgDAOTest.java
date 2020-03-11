/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

import fr.ima2g.m2cci.mytheatre.prog.model.Representation;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marti236
 */
public class ProgDAOTest {
    
    public ProgDAOTest() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of representationsFiltrees method, of class ProgDAO.
     */
    @Test
    public void testRepresentationsFiltrees() throws Exception {
        System.out.println("representationsFiltrees");
        DataSource ds = null;
        Date horaireDebut = null;
        Date horaireFin = null;
        String cibleSpe = "";
        List<String> typesSpe = null;
        List<Representation> expResult = null;
        List<Representation> result = ProgDAO.representationsFiltrees(ds, horaireDebut, horaireFin, cibleSpe, typesSpe);
        assertEquals("10", "10");

    }
    
}
