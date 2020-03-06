/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import java.util.Date;

/**
 *
 * @author marti236
 */
public class Representation {

    private Date date;
    private Spectacle spe;

    public Representation(Date date, Spectacle spe) {
        this.date = date;
        this.spe = spe;
    }

    /**
     * Renvoie la date du spectacle
     *
     * @return
     */
    public Date getDate() {
        return this.date;
    }
    /**
     * Renvoie l'objet spectacle associé a sa representation
     *
     * @return
     */
    public Spectacle getSpectacle() {
        return this.spe;
    }
}
