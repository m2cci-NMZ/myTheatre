/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ima2g.m2cci.mytheatre.prog.model;

public class Humoristique extends Spectacle {
    private final boolean estUnOneWomanManShow;

    public Humoristique(int numero, String nom, double prixDeBase, String type, 
            String cible, boolean estUnOneWomanManShow) {
        super(numero, nom, prixDeBase, type, cible);
        this.estUnOneWomanManShow = estUnOneWomanManShow;
    }

    /**
     * Renvoie si le spectacle est estUnOneWomanManShow ou pas
     *
     * @return
    */
    public boolean getestUnOneWomanManShow (){
        return this.estUnOneWomanManShow;
    }
    
    @Override
    public String toString() {
        if (this.estUnOneWomanManShow == true)
             return super.toString () + " est un OneWomanManShow";
         else
            return  super.toString ();   
        

    }
    
}
