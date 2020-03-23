/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.im2ag.m2cci.mytheatre.prog.model;

/**
 *
 * @author nico
 */
public class Place {

    /**
     * le numéro de la place
     */
    private final int noPlace;

    /**
     * le rang de la place
     */
    private final int rang;

    /**
     *
     * @param noPlace le numéro de la place
     * @param rang le rang
     */
    public Place(int noPlace, int rang) {

        this.noPlace = noPlace;

        this.rang = rang;

    }

    public int getNoPlace() {
        return noPlace;
    }

    public int getRang() {
        return rang;
    }

}
