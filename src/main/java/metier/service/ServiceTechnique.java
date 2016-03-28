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
import metier.modele.*;

import java.util.List;

/**
 *
 * @author pllefebvre
 */
public abstract class ServiceTechnique {
    
    final static GeoApiContext CONTEXTE_GEOAPI = 
            new GeoApiContext().setApiKey("AIzaSyAhf3JleYpal9S-xouJYH8lf7Dvz5Y2Nko");
   
    /**
     * Methode de recuperation de la latitude et longitude
     * @param adresse a localiser
     * @return latitude et longitude
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
   
    /**
     * Methode de mise a jour de la latitude et longitude d'un Lieu
     * @param lieu a localiser puis mettre a jour
     */
    public static void majCoordonnees(Lieu lieu) {
       
        lieu.setCoordonnees(getLatLng(lieu.getAdresse()));
    }
   
    /**
     * Methode de mise a jour de la latitude et longitude d'un Adherent	
     * @param adherent a localiser puis mettre a jour
     */
    public static void majCoordonnees(Adherent adherent) {
       
        adherent.setCoordonnees(getLatLng(adherent.getAdresse()));
    }
   
    /**
     * Affiche sur la console le mail que l'adherent recoit en cas de
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
     * Affiche sur la console le mail que le responsable recoit en cas de
     * confirmation de l'inscription d'un adherent passe en parametre
     * @param adherent l'adherent dont l'inscription a ete confirmee   
     */
    public static void mailConfirmationInscriptionResponsable(Adherent adherent) {
       
        System.out.println("Expediteur : collectif@collectif.org\n"
                + "Pour : responsable@collectif.com\n" 
                + "Sujet : confirmation d'incription de l'adherent : "
                + adherent.getMail()
                + "\n\n"
                + "Madame, Monsieur,\n"
                + "Nous confirmons l'inscription a notre service Collect'IF de \n"
                + "l'adherent : "+ adherent.getPrenom() +" "+ adherent.getNom()
                + "a l'adresse mail : " + adherent.getMail() + ".\n\n");
    }
   
    /**
     * Affiche sur la console le mail que l'adherent recoit en cas d'infirmation
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
     * Affiche sur la console le mail que le responsable recoit en cas 
     * d'infirmation de l'inscription d'un adherent passe en parametre
     * @param adherent l'adherent dont l'inscription a ete infirmee
     */
    public static void mailInfirmationInscriptionResponsable(Adherent adherent) {
       
        System.out.println("Expediteur : collectif@collectif.org\n"
                + "Pour : responsable@collectif.com\n" 
                + "Sujet : confirmation d'incription de l'adherent : "
                + adherent.getMail()
                + "\n\n"
                + "Madame, Monsieur,\n"
                + "L'inscription a notre service Collect'IF de \n"
                + "l'adherent : "+ adherent.getPrenom() +" "+ adherent.getNom()
                + "a l'adresse mail : " + adherent.getMail() + " a echoue.\n\n");
    }
    /**
     * Affiche sur la console le mail que le responsable envoie en cas 
     * de creation d'un evenement passe en parametre
     * @param evenement l'evenement venant d'etre cree
     */
    public static void mailEvenement(Evenement evenement) {
       
        System.out.print( "Expediteur : collectif@collectif.org\n"
                + "Pour : ");
        for(Adherent adherent : obtenirAdherentAEvenement(evenement))        
            System.out.print(adherent.getMail() + ", ");
        System.out.print( "\nSujet : Nouvel Evenement Collect'IF\n"
                + "\n"
                + "Bonjour ");
        for(Adherent adherent : obtenirAdherentAEvenement(evenement))        
            System.out.print(adherent.getNom() + ", ");
        System.out.println( "\nComme vous l'aviez souhaite, COLLECT'IF organise \n"
                + "un evenement de " + evenement.getActivite().getDenomination()
                + "le " + evenement.getDateEvenement() + ". Vous trouverez ci-dessous \n"
                + "les details de cet evenement. \n\n"
                + "Associativement vôtre,\n\n"
                + "Le Responsable de l'association\n\n"
                + "Evenement " + evenement.getActivite().getDenomination() + "\n"
                + "Date : " + evenement.getDateEvenement() + "\n"
                + "Lieu : " + evenement.getLieu().getDenomination() + ", "
                + evenement.getLieu().getAdresse()); 
   }
   
   /**
     * Methode renvoyant tous les adherent a un evenement donne
     * @param evenement l'enevenement en question
     * @return la liste de tous les adherents
     */
    private static List<Adherent> obtenirAdherentAEvenement(Evenement evenement) {
        
        if(evenement instanceof Evenement1equipe) {
            
            return ((Evenement1equipe) evenement).getListeAdherents();
        } 
      
        List<Adherent> ret = ((Evenement2equipes) evenement).getListeEquipeA();
        ret.addAll(((Evenement2equipes) evenement).getListeEquipeA());
        return ret;
    }
}
