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
public class Humoristique extends Spectacle {

      private boolean estUnOneWomanManShow;

    public Humoristique() {

    }

/**
 * Renvoie si le spectacle est un onewomanshow ou pas
 * @return 
 */

    public boolean getEstUnOneWomanShow() {
        return false;
    }

public void estUnOneWomanManShow (){
                 
                    if(estUnOneWomanManShow ==1){
                        return true;
                    } else {
                        return false;
                    }    

}
