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
public class Ticket {

    /**
     * le login de l'utilisateur
     */
    private final String userId;
    /**
     * le numéro de la place
     */
    private final int noTicket;

    /**
     * le numéro de la place
     */
    private final int noPlace;

    /**
     * le rang de la place
     */
    private final int rang;

    /**
     * la representation associée de la place
     */
    private final Representation rep;

    /**
     *
     * @param noTicket
     * @param noPlace le numéro de la place
     * @param rang le rang
     * @param rep
     */
    public Ticket(String uId, int noTicket, int noPlace, int rang, Representation rep) {

        this.noTicket = noTicket;

        this.noPlace = noPlace;

        this.rang = rang;

        this.rep = rep;

        this.userId = uId;
    }

    public int getNoPlace() {
        return noPlace;
    }

    public int getRang() {
        return rang;
    }

    public Representation getRepresentation() {
        return rep;
    }

    public int getNumeroTicket() {
        return noTicket;
    }

    public String getUserId() {
        return userId;
    }
}
