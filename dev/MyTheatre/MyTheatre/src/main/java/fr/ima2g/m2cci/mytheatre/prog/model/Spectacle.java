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
public class Spectacle {
   private int numero ; 
   private String nom;
   private  double prixDeBase;
   private String type;
   private String  cible;
   

   public Spectacle() {

    }

  public Spectacle (int numero,String nom,double prixDeBase, String type,String  cible){
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



}