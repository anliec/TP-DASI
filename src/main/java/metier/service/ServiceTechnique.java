/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import metier.modele.Adherent;
import metier.modele.Evenement;
import metier.modele.Lieu;

/**
 *
 * @author pllefebvre
 */
public abstract class ServiceTechnique {
    
   final static GeoApiContext CONTEXTE_GEOAPI =
        new GeoApiContext().setApiKey("AIzaSyAhf3JleYpal9S-xouJYH8lf7Dvz5Y2Nko");
   
   /**
    * Methode de récupération de 
    * @param adresse
    * @return 
    */
   private static LatLng getLatLng(String adresse) {
       
       try {
           GeocodingResult[] results = 
                   GeocodingApi.geocode(CONTEXTE_GEOAPI,adresse).await();
           return results[0].geometry.location;
       
       } catch (Exception ex) {
           
           ex.printStackTrace();
           return null;
       }
   }
   
   public static void majCoordonnees(Lieu lieu) {
       
       lieu.setCoordonnees(getLatLng(lieu.getAdresse()));
   }
   
   public static void majCoordonnees(Adherent adherent) {
       
       adherent.setCoordonnees(getLatLng(adherent.getAdresse()));
   }
   
   /**
    * Affiche sur la console le mail que l'adherent reçoit en cas de
    * confirmation de son inscription
    * @param adherent l'adherent en question
    */
   public static void mailConfirmationInscriptionAdherent(Adherent adherent) {
       
       System.out.println("Expediteur : collectif@collectif.org\n"
               + "Pour : " + adherent.getMail() + ",\n"
               + "Sujet : Bienvenue chez Collect'IF\n"
               + "\n"
               + "Bonjour " + adherent.getNom() + "\n"
               + "Nous vous confirmons votre adhesion a l'assocation COLLECT'IF."
               + "Votre numero d'adherent est : 2450"
               + ".\n\n");
   }
   
   /**
    * Affiche sur la console le mail que le responsable reçoit en cas de
    * confirmation de l'inscription d'un adherent passe en parametre
    * @param adherent l'adherent dont l'inscription a ete confirmee
    */
   public static void mailConfirmationInscriptionResponsable(Adherent adherent) {
       
       System.out.println("mailTo : responsable@collectif.com \n"
               + "from : noreply@collectif.com\n"
               + "object : confirmation d'incription de l'adherent : "
               + adherent.getMail()
               + "\n\n"
               + "Madame, Monsieur,\n"
               + "Nous confirmons l'inscription a notre service Collect'IF de \n"
               + "l'adherent : "+ adherent.getPrenom() +" "+ adherent.getNom()
               + "a l'adresse mail : " + adherent.getMail() + ".\n\n"
               + "Coridalement\n\n"
               + "Le Service Collect'IF\n\n"
               + "Veulliez ne pas repondre a ce mail.");
   }
   
   /**
    * Affiche sur la console le mail que l'adherent reçoit en cas d'infirmation
    * de son inscription
    * @param adherent l'adherent en question
    */
   public static void mailInfirmationInscriptionAdherent(Adherent adherent) {
       
       System.out.println("Expediteur : collectif@collectif.org\n"
               + "Pour : " + adherent.getMail() + ",\n"
               + "Sujet : Bienvenue chez Collect'IF\n"
               + "\n"
               + "Bonjour " + adherent.getNom() + "\n"
               + "Votre adhesion a l'assocation COLLECT'IF a malencontreusement "
               + "echoue... Merci de recommencer ulterieurement"
               + ".\n\n");
   }
   
   /**
    * Affiche sur la console le mail que le responsable reçoit en cas 
    * d'inrmationde l'inscription d'un adherent passe en parametre
    * @param adherent l'adherent dont l'inscription a ete infirmee
    */
   public static void mailInfirmationInscriptionResponsable(Adherent adherent) {
       
         System.out.println("mailTo : responsable@collectif.com \n"
               + "from : noreply@collectif.com\n"
               + "object : confirmation d'incription de l'adherent : "
               + adherent.getMail()
               + "\n\n"
               + "Madame, Monsieur,\n"
               + "L'inscription a notre service Collect'IF de \n"
               + "l'adherent : "+ adherent.getPrenom() +" "+ adherent.getNom()
               + "a l'adresse mail : " + adherent.getMail() + " a echoue.\n\n"
               + "Coridalement\n\n"
               + "Le Service Collect'IF\n\n"
               + "Veulliez ne pas repondre a ce mail.");
   }
   
   public static void mailEvenement(Evenement evenement) {
       
       
   }
}
