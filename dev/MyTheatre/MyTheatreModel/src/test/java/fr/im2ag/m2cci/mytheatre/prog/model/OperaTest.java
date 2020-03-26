/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author marti236
 */
public class OperaTest {

    private static Opera opera = null;

    public OperaTest() {
    }

    @BeforeEach
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
    
    
    /**
     * Test de la method equals
     * Vérifie que deux opéras identiques sont égaux 
     */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Opera o1 = new Opera(11, "Opera", 10.0, "opera", "toutPublic", false);
        Opera o2 = new Opera(11, "Opera", 10.0, "opera", "toutPublic", false);
        assertEquals(o1, o2);
    }
    
    /**
     * Test de la method equals
     * Vérifie que deux opéras avec et sans orchestres sont différents
     */
    @Test
    public void testEqualsBoolean(){
        System.out.println("equals");
        Opera o1 = new Opera(11, "Opera", 10.0, "opera", "toutPublic", false);
        Opera o2 = new Opera(11, "Opera", 10.0, "opera", "toutPublic", true);
        assertFalse(o1.equals(o2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie que si la partie spectacle est différente, alors les operas sont differents
     */
    @Test
    public void testEqualsSpectacle(){
        System.out.println("equals");
        Opera o1 = new Opera(11, "Opera", 12.0, "opera", "toutPublic", true);
        Opera o2 = new Opera(11, "Opera", 10.0, "opera", "toutPublic", true);
        assertFalse(o1.equals(o2));
    }
    
    /**
     * Test de la methode equals
     * Vérifie qu'un Opera ne peut être égal à un Spectacle identique
     */
    @Test
    public void testEqualsOperaSpectacle(){
        System.out.println("equals : Opera et Spectacle");
        Spectacle s1 = new Spectacle(11, "Spectacle", 10.0, "opera", "toutPublic");
        Opera o2 = new Opera(11, "Spectacle", 10.0, "opera", "toutPublic", true);
        assertFalse(o2.equals(s1));
    }
}
