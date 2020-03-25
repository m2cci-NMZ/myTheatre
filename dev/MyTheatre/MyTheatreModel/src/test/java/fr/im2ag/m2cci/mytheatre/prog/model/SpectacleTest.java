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
    
    // ##############################
    // ### Tests de la methode equals
    // ##############################
    
    /**
     * Test de la methode equals
     */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(11, "Opera", 10, "opera", "toutPublic");
        assertEquals(s1, s2);
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'on retourne faux si c'est pas le même numéro
     */
    @Test
    public void testEqualsNum(){
        System.out.println("equals : numero");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(12, "Opera", 10.0, "opera", "toutPublic");
        assertFalse(s1.equals(s2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'on retourne faux si c'est pas le même nom
     */
    @Test
    public void testEqualsNom(){
        System.out.println("equals : nom");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(11, "opera", 10.0, "opera", "toutPublic");
        assertFalse(s1.equals(s2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'on retourne faux si c'est pas le même prix
     */
    @Test
    public void testEqualsPrix(){
        System.out.println("equals : prix");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(11, "Opera", 10.5, "opera", "toutPublic");
        assertFalse(s1.equals(s2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'on retourne faux si c'est pas le même type
     */
    @Test
    public void testEqualsType(){
        System.out.println("equals : type");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(11, "Opera", 10.0, "musical", "toutPublic");
        assertFalse(s1.equals(s2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'on retourne faux si c'est pas le même cible
     */
    @Test
    public void testEqualsCible(){
        System.out.println("equals : type");
        Spectacle s1 = new Spectacle(11, "Opera", 10.0, "opera", "toutPublic");
        Spectacle s2 = new Spectacle(11, "Opera", 10.0, "opera", "adulte");
        assertFalse(s1.equals(s2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'un Spectacle ne peut être égal à un Humoristique identique
     */
    @Test
    public void testEqualsSpectacleHumoristique(){
        System.out.println("equals : Spectacle et Humoristique");
        Spectacle s1 = new Spectacle(11, "Spectacle", 10.0, "humoristique", "toutPublic");
        Humoristique h2 = new Humoristique(11, "Spectacle", 10.0, "humoristique", "toutPublic", true);
        assertFalse(s1.equals(h2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'un Spectacle ne peut être égal à un Opera identique
     */
    @Test
    public void testEqualsSpectacleOpera(){
        System.out.println("equals : Spectacle et Opera");
        Spectacle s1 = new Spectacle(11, "Spectacle", 10.0, "opera", "toutPublic");
        Opera o2 = new Opera(11, "Spectacle", 10.0, "opera", "toutPublic", true);
        assertFalse(s1.equals(o2));
    }
}
