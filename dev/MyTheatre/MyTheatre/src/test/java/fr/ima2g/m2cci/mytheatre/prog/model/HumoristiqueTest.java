/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import org.junit.Test;
import static org.junit.Assert.*;

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
        boolean result = instance.getestUnOneWomanManShow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Humoristique.
     */
    @Test
    public void testToStringtrue() {
        System.out.println("toString");
        Humoristique instance = new Humoristique(0, "test" , 0, null, null, true);
        String expResult = "test, est un OneWomanManShow";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testToStringfalse() {
        System.out.println("toString");
        Humoristique instance = new Humoristique(0, "test" , 0, null, null, false);
        String expResult = "test";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
