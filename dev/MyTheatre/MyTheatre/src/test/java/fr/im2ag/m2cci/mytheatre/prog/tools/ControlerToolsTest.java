/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.tools;

import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hydro
 */
public class ControlerToolsTest {
    
    public ControlerToolsTest() {
    }

    /**
     * Test of nbSemaineEntre method, of class ControlerTools.
     */
    @Test
    public void testNbSemaineEntreDebutFinMois() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-31");
        int expResult = 6;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }
    @Test
    public void testNbSemaineEntreMemeJour() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-03");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-03");
        int expResult = 1;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }
 @Test
    public void testNbSemaineEntreDifferentesSemaines() throws Exception {
        System.out.println("nbSemaineEntreE");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-08");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-09");
        int expResult = 2;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }
     @Test
    public void testNbSemaineEntreMemeSemaine() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-07");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-08");
        int expResult = 1;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
        //A verifier si expResult = 1 et non 2
    }
      @Test
    public void testNbSemaineEntreFinSemaine() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-15");
        int expResult = 3;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }
          @Test
    public void testNbSemaineEntreDebutSemaine() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-02");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-16");
        int expResult = 3;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }
    @Test
    public void testNbSemaineEntreDebutEtFinSemaine() throws Exception {
        System.out.println("nbSemaineEntre");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-16");
        int expResult = 4;
        int result = ControlerTools.nbSemaineEntre(d1, d2);
        assertEquals(expResult, result);
    }

    /**
     * Test of numeroJourSemaine method, of class ControlerTools.
     */
    @Test
    public void testNumeroJourSemaineDebut() throws Exception {
        System.out.println("numeroJourSemaine");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-02");
        int expResult = 0;
        int result = ControlerTools.numeroJourSemaine(d);
        assertEquals(expResult, result);
    }
        @Test
    public void testNumeroJourSemaineFin() throws Exception {
        System.out.println("numeroJourSemaine");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        int expResult = 6;
        int result = ControlerTools.numeroJourSemaine(d);
        assertEquals(expResult, result);
    }        @Test
    public void testNumeroJourSemaineAleatoire() throws Exception {
        System.out.println("numeroJourSemaine");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04");
        int expResult = 2;
        int result = ControlerTools.numeroJourSemaine(d);
        assertEquals(expResult, result);
    }

    /**
     * Test of toFinDeJournee method, of class ControlerTools.
     */
    @Test
    public void testToFinDeJournee() throws Exception {
        System.out.println("toFinDeJournee");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-16");
        Date expResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-16 23:59:00");
        Date result = ControlerTools.toFinDeJournee(d);
        assertEquals(expResult, result);
    }
}
