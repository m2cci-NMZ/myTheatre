/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    
    /**
    * Test de la method equals
    * Vérifie que deux opéras identiques sont égaux 
    */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Humoristique h1 = new Humoristique(11, "Humour", 10.0, "humoristique", "toutPublic", true);
        Humoristique h2 = new Humoristique(11, "Humour", 10.0, "humoristique", "toutPublic", true);
        assertEquals(h1, h2);
    }
    
    /**
     * Test de la method equals
     * Vérifie que deux opéras avec et sans orchestres sont différents
     */
    @Test
    public void testEqualsBoolean(){
        System.out.println("equals");
        Humoristique h1 = new Humoristique(11, "Humour", 10.0, "humoristique", "toutPublic", true);
        Humoristique h2 = new Humoristique(11, "Humour", 10.0, "humoristique", "toutPublic", false);
        assertFalse(h1.equals(h2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie que si la partie spectacle est différente, alors les operas sont differents
     */
    @Test
    public void testEqualsSpectacle(){
        System.out.println("equals");
        Humoristique h1 = new Humoristique(11, "Humour", 10.0, "humoristique", "toutPublic", true);
        Humoristique h2 = new Humoristique(11, "Humour", 12.0, "humoristique", "toutPublic", true);
        assertFalse(h1.equals(h2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie que si on compare un Humoristique et un Spectacle identique, ça marche pas
     */
    @Test
    public void testEqualsHumoristique(){
        System.out.println("equals : Spectacle et Humoristique");
        Spectacle s1 = new Spectacle(11, "Spectacle", 10.0, "humoristique", "toutPublic");
        Humoristique h2 = new Humoristique(11, "Spectacle", 12.0, "humoristique", "toutPublic", true);
        assertFalse(s1.equals(h2));
    }
}
