/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author marti236
 */
public class RepresentationTest {

    private static Representation representation = null;

    public RepresentationTest() {
    }

    @BeforeEach
    public void setUp() {
        Date horaire = new Date(10,10,10,10,0);
        Spectacle spectacle= new Spectacle(10, "nom", 10.5, "comedie", "toutPublic");
        representation = new Representation(horaire, spectacle);
    }

    /**
     * Test of getDate method, of class Representation.
     */
    @Test
    public void testGetHoraire() {
        System.out.println("getHoraire");
        Date horaire = new Date(10,10,10,10,0);
        assertEquals(horaire, representation.getHoraire());
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
        Date horaire = new Date(10,10,10,10,0);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HHh");
        assertEquals("Representation : Horraire =" + formatDate.format(horaire) + "\nSpectacle : nom (comedie, toutPublic) à 10.5€\n", representation.toString());
    }

}
