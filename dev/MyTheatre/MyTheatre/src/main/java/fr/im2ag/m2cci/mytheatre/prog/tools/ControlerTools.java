/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author iXeRay
 */
public class ControlerTools {
    
    
    /**
     * Calcule le nombre de semaines de la période délimitée par deux dates
     * On compte chaque semaine dans lequel il y a un jour, si c'est du dimanche 
     * au lundi, c'est donc 2 semaines, cele du dimanche et celle du lundi
     *
     * @param d1 : Premier jour
     * @param d2 : Dernier jour
     * @return : int correspondant au nombre de semaine
     */
    public static int nbSemaineEntre(Date d1, Date d2) {
        // Calcul du nombre de jour entre les deux dates
        LocalDate date1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long nbJour = ChronoUnit.DAYS.between(date1, date2) + 1;    // On ajoute un pour inclure d1 et d2

        // Calcul du nombre de semaines
        int nbSem = 0;
        int nbJourAEnlever = numeroJourSemaine(d2) + 1;     // On commence par enlever le nombre de jours dans la dernière semaine
        while (nbJour > 0) {
            nbSem++;
            nbJour -= nbJourAEnlever;
            nbJourAEnlever = 7;
        }

        return nbSem;
    }
    
    
    /**
     * Calcul le numéro du jour de la semaine correspondant à la date donnée en
     * paramètre : 0 pour lundi, ..., 6 pour dimanche
     *
     * @param d : Date
     * @return : int correspondant au numéro du jour
     */
    public static int numeroJourSemaine(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int numeroJour = c.get(Calendar.DAY_OF_WEEK);        // Retourne 1 pour Dimanche puis 2 pour Lundi, ...
        numeroJour -= 1;
        if (numeroJour == 0) {
            numeroJour = 7;
        }
        numeroJour -= 1;    // On fait commencer le lundi à 0

        return numeroJour;
    }
    
    
    /**
     * Retourne une nouvelle date de même jour que à celle passée en paramètre,
     * mais ayant pour heure 23h59 Permet de faire des comparaison entre jours
     *
     * @param d : Date
     * @return : Date fixée à 23h59
     */
    public static Date toFinDeJournee(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR, +23);
        c.add(Calendar.MINUTE, +59);

        return c.getTime();
    }
}
