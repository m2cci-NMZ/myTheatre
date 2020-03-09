/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marti236
 */
public class RepresentationTest {

    private static Representation representation = null;

    public RepresentationTest() {
    }

    @Before
    public void setUp() {
        Date date = new Date(10,10,10,10,0);
        Spectacle spectacle= new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        representation = new Representation(date, spectacle);
    }

    /**
     * Test of getDate method, of class Representation.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Date date = new Date(10,10,10,10,0);
        assertEquals(date, representation.getDate());
    }

    /**
     * Test of getSpectacle method, of class Representation.
     */
    @Test
    public void testGetSpectacle() {
        System.out.println("getSpectacle");
        Spectacle spectacle= new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        assertEquals(spectacle, representation.getSpectacle());
    }

    /**
     * Test of toString method, of class Representation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date date = new Date(10,10,10,10,0);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HHh");
        assertEquals("Representation : Horraire =" + formatDate.format(date) + "\nSpectacle : nom (comedie, toutPublic) à 10.5€\n", representation.toString());
    }

}
