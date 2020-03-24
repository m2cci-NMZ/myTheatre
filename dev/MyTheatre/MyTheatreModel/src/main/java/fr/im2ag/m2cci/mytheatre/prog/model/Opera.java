/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

/**
 *
 * @author marti236
 */
public class Opera extends Spectacle {

    private final boolean aUnOrchestre;

    public Opera(int numero, String nom, double prixDeBase, String type, String cible, boolean aUnOrchestre) {
        super(numero, nom, prixDeBase, type, cible);
        this.aUnOrchestre = aUnOrchestre;
    }

    /**
     * Renvoie si l'opera a un orchestre ou non
     *
     * @return
     *
     */
    public boolean getAUnOrchestre() {
        return this.aUnOrchestre;
    }

    @Override
    public String toString() {
        if (this.aUnOrchestre) {
            return super.toString() + ", a un Orchestre ";
        } else {
            return super.toString();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + super.hashCode();
        hash = 71 * hash + (this.aUnOrchestre ? 1 : 0);
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
        final Opera other = (Opera) obj;
        if (this.aUnOrchestre != other.aUnOrchestre) {
            return false;
        }
        return true;
    }
    
    
}
