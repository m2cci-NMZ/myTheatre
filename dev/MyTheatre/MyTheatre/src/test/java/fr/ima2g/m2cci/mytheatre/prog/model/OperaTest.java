/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marti236
 */
public class OperaTest {
    private static Opera opera = null;
    public OperaTest() {
    }
    
    @Before
    public void setUp() {
        opera = new Opera(10, "nom", 10.5, "comedie", "toutPublic", true);
    }

    /**
     * Test of getAUnOrchestre method, of class Opera.
     */
    @Test
    public void testGetAUnOrchestre() {
        System.out.println("getAUnOrchestre");
        assertEquals(true, opera.getAUnOrchestre());

    }

    /**
     * Test of toString method, of class Opera.
     */
    @Test
    public void testToStringFalse() {
        System.out.println("toString");
        opera = new Opera(10, "nom", 10.5, "comedie", "toutPublic", false);
        assertEquals("Spectacle : nom (comedie, toutPublic) à 10.5€", opera.toString());
    }
    
        @Test
    public void testToStringTrue() {
        System.out.println("toString");
        assertEquals("Spectacle : nom (comedie, toutPublic) à 10.5€, a un Orchestre ", opera.toString());
    }
    
}
