/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.horaire);
        hash = 67 * hash + Objects.hashCode(this.spe);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Representation other = (Representation) obj;
        if (!Objects.equals(this.horaire, other.horaire)) {
            return false;
        }
        if (!Objects.equals(this.spe, other.spe)) {
            return false;
        }
        return true;
    }
    
}
