/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

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
        if (this.aUnOrchestre== true)
             return super.toString () + ", a un Orchestre ";
         else
            return super.toString ();   
    }
    
   }
