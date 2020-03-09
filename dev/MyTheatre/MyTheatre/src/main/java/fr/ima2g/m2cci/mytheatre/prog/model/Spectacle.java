/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

import java.util.Objects;

public class Spectacle {
    private final int numero ; 
    private final String nom;
    private final  double prixDeBase;
    private final String type;
    private final String  cible;

    public Spectacle (int numero,String nom,double prixDeBase, String type, String  cible){
        this.numero = numero;
        this.nom = nom;
        this.prixDeBase= prixDeBase;
        this.type = type;
        this.cible = cible;
    }

    /**
    * Renvoie du nom du spectacle
    * @return 
    */
     public String getNom() {
         return this.nom;
     }

    /**
    * Renvoie le prix de base du spectacle
    * @return 
    */
     public double getPrixDeBase() {
         return this.prixDeBase;
     }

    /**
    * Renvoie le public cible
    * @return 
    */
     public String getCible() {
         return this.cible;
     }

    /**
    * Renvoie le type de spectacle
    * @return 
    */
     public String getType() {
         return this.type;
     }

    @Override
    public String toString() {
        return "Spectacle : " + nom + " (" + type + ", " + cible + ") à " + prixDeBase + "€";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.numero;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.prixDeBase) ^ (Double.doubleToLongBits(this.prixDeBase) >>> 32));
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.cible);
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
        final Spectacle other = (Spectacle) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (Double.doubleToLongBits(this.prixDeBase) != Double.doubleToLongBits(other.prixDeBase)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.cible, other.cible)) {
            return false;
        }
        return true;
    }
     
     
}
