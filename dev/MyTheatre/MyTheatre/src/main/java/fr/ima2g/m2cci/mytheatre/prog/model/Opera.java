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

    public Opera(boolean aUnOrchestre) {
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
}
