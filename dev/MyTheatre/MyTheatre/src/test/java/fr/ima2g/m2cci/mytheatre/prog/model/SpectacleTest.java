/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marti236
 */
public class SpectacleTest {
    
    public SpectacleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getNom method, of class Spectacle.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Spectacle instance = null;
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrixDeBase method, of class Spectacle.
     */
    @Test
    public void testGetPrixDeBase() {
        System.out.println("getPrixDeBase");
        Spectacle instance = null;
        double expResult = 0.0;
        double result = instance.getPrixDeBase();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCible method, of class Spectacle.
     */
    @Test
    public void testGetCible() {
        System.out.println("getCible");
        Spectacle instance = null;
        String expResult = "";
        String result = instance.getCible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Spectacle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Spectacle instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Spectacle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Spectacle instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
