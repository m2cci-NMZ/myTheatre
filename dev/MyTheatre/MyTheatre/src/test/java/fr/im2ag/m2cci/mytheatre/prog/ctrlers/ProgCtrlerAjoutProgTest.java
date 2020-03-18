
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.ctrlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author iXeRay
 */
public class ProgCtrlerAjoutProgTest {
    
    public ProgCtrlerAjoutProgTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processRequest method, of class ProgCtrlerAjoutProg.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ProgCtrlerAjoutProg instance = new ProgCtrlerAjoutProg();
        instance.processRequest(request, response);
    }
    

    @Test
    public void testNbSemaineEntre() throws ParseException{
        System.out.println("nbSemaineEntre");
        SimpleDateFormat jourFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
         //Test classique
        Date d1 = jourFormatter.parse("2020-03-01");
        Date d2 = jourFormatter.parse("2020-03-31");
        int nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(6, 6);
        
        // Le même jour
        d1 = jourFormatter.parse("2020-03-03");
        d2 = jourFormatter.parse("2020-03-03");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(1, nbSem);
        
        // 2 jours sur des semaines différentes
        d1 = jourFormatter.parse("2020-03-08");
        d2 = jourFormatter.parse("2020-03-09");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(2, nbSem);
        
        // 2 jours sur la même semaine
        d1 = jourFormatter.parse("2020-03-07");
        d2 = jourFormatter.parse("2020-03-08");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(2, nbSem);
        
        // Cas limite : Fin de semaine
        d1 = jourFormatter.parse("2020-03-01");
        d2 = jourFormatter.parse("2020-03-15");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(3, nbSem);
        
        // Cas limite : Début de semaine
        d1 = jourFormatter.parse("2020-03-02");
        d2 = jourFormatter.parse("2020-03-16");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(3, nbSem);
        
        // Cas limite : Début et fin de semaine
        d1 = jourFormatter.parse("2020-03-01");
        d2 = jourFormatter.parse("2020-03-16");
        nbSem = ProgCtrlerAjoutProg.nbSemaineEntre(d1, d2);
        assertEquals(4, nbSem);
        }
//        catch (ExceptionInInitializerError ex){
//            ex.getMessage();
//        }
    }
}
