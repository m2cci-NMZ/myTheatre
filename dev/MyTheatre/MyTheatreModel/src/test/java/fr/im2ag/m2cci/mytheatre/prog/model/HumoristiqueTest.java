/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



/**
 *
 * @author marti236
 */
public class HumoristiqueTest {
    
    public HumoristiqueTest() {
    }

    /**
     * Test of getestUnOneWomanManShow method, of class Humoristique.
     */
    @Test
    public void testGetestUnOneWomanManShow() {
        System.out.println("getestUnOneWomanManShow");
        Humoristique instance = new Humoristique(0, null , 0, null, null, false);
        boolean expResult = false;
        boolean result = instance.getEstUnOneWomanManShow();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Humoristique.
     */
    @Test
    public void testToStringtrue() {
        System.out.println("toString");
        Humoristique instance = new Humoristique(10, "nom", 10.5, "comedie", "toutPublic", true);
        String expResult = "Spectacle : nom (comedie, toutPublic) à 10.5€, est un OneWomanManShow";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testToStringfalse() {
        System.out.println("toString");
        Humoristique instance = new Humoristique(10, "nom", 10.5, "comedie", "toutPublic", false);
        String expResult = "Spectacle : nom (comedie, toutPublic) à 10.5€";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
     
}
