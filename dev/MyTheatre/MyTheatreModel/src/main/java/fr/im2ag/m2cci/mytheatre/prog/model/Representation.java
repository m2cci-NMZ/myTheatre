/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Representation {
    private final Date horaire;
    private final Spectacle spe;

    public Representation(Date date, Spectacle spe) {
        this.horaire = date;
        this.spe = spe;
    }

    /**
     * Renvoie la date du spectacle
     *
     * @return
     */
    public Date getHoraire() {
        return this.horaire;
    }

    /**
     * Renvoie l'objet spectacle associé a sa representation
     *
     * @return
     */
    public Spectacle getSpectacle() {
        return this.spe;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HHh");
        
        return "Representation : " + "Horraire =" + formatDate.format(horaire) + "\n" + spe  + "\n";
    }
}
