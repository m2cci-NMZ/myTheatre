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

    /**
     * Test of getNom method, of class Spectacle.
     */
    @Test
    public void testGetNom() {
        Spectacle spectacle = new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        System.out.println("getNom");
        assertEquals("nom", spectacle.getNom());

    }

    /**
     * Test of getPrixDeBase method, of class Spectacle.
     */
    @Test
    public void testGetPrixDeBase() {
        System.out.println("getPrixDeBase");
        Spectacle spectacle = new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        assertEquals(10.5, spectacle.getPrixDeBase(),0.1);

    }

    /**
     * Test of getCible method, of class Spectacle.
     */
    @Test
    public void testGetCible() {
        System.out.println("getCible");
        Spectacle spectacle = new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        assertEquals("toutPublic", spectacle.getCible());

    }

    /**
     * Test of getType method, of class Spectacle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Spectacle spectacle = new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        assertEquals("comedie", spectacle.getType());

    }

    /**
     * Test of toString method, of class Spectacle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Spectacle spectacle = new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        assertEquals("Spectacle : nom (comedie, toutPublic) à 10.5€", spectacle.toString());
    }

}
