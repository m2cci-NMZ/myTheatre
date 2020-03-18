/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.tools;

import fr.im2ag.m2cci.mytheatre.prog.model.Representation;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iXeRay
 */
public class ControlerTools {
    
    
    /**
     * Calcule le nombre de semaines de la période délimitée par deux dates
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
    
    
    public static void ajouterAuRepParSemaine(Representation rep, int numeroSem, List<List<List<Representation>>> repParSemaine){
        if (repParSemaine.get(numeroSem).isEmpty()){
            // Premier ajout pour cette semaine, on crée les 7 List<Representation> pour chaque jour (Lundi, ..., Dimanche)
            for (int iJour = 0; iJour < 7; iJour++){
                repParSemaine.get(numeroSem).add(new ArrayList<Representation>());
            }
        }
        
        int numeroJourSemaineRep = numeroJourSemaine(rep.getHoraire());     // On trouve l'indice du jour
        repParSemaine.get(numeroSem).get(numeroJourSemaineRep).add(rep);    // On ajoute dans le bon jour
    }
}
