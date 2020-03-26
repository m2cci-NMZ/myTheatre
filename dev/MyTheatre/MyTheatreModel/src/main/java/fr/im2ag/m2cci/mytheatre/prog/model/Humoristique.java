/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

public class Humoristique extends Spectacle {
    private final boolean estUnOneWomanManShow;

    public Humoristique(int numero, String nom, double prixDeBase, String type, String cible, boolean estUnOneWomanManShow) {
        super(numero, nom, prixDeBase, type, cible);
        this.estUnOneWomanManShow = estUnOneWomanManShow;
    }

    /**
     * Renvoie si le spectacle est estUnOneWomanManShow ou pas
     *
     * @return
    */
    public boolean getEstUnOneWomanManShow (){
        return this.estUnOneWomanManShow;
    }
    
    @Override
    public String toString() {
        if (this.estUnOneWomanManShow == true)
             return super.toString () + ", est un OneWomanManShow";
         else
            return  super.toString ();   
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + super.hashCode();
        hash = 71 * hash + (this.estUnOneWomanManShow ? 1 : 0);
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
        if (!super.equals(obj)){
            return false;
        }
        final Humoristique other = (Humoristique) obj;
        if (this.estUnOneWomanManShow != other.estUnOneWomanManShow) {
            return false;
        }
        return true;
    }
}
