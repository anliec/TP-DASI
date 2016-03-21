/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.Date;
import java.util.LinkedList;
import metier.modele.Adherent;
import metier.service.ServiceMetier;
import metier.service.ServiceTechnique;
import metier.modele.Activite;
import metier.modele.Demande;
import metier.modele.Evenement;
import metier.modele.Evenement1equipe;
import metier.modele.Evenement2equipes;
import metier.modele.Lieu;

/**
 *
 * @author pllefebvre
 */
public class Main1 {
    
    public static void main(String[] args) {
        
        ServiceMetier serviceMetier = new ServiceMetier();
        LinkedList<Adherent> adherents = new LinkedList<Adherent>();
        LinkedList<Adherent> adhlist1 = new LinkedList<Adherent>();
        LinkedList<Adherent> adhlist2 = new LinkedList<Adherent>();
        Adherent adh1 = new Adherent("nom1", "prenom1", "adresse1", "mail1");
        Adherent adh2 = new Adherent("nom12", "prenom12", "adresse12", "mail12");
        adherents.add(adh1);
        adherents.add(adh2);
        adhlist1.add(adh1);
        adhlist2.add(adh2);
        serviceMetier.creerAdherent(adh1);
        serviceMetier.creerAdherent(adh2);
        Activite foot = new Activite("Foot", true, 2);
        Activite belotte = new Activite("Belotte", false, 2);
        serviceMetier.creerActivite(foot);
        serviceMetier.creerActivite(belotte);
        Lieu lieu = new Lieu("Gymnase test", "type test", "00 adresse test, 69100 Villeurbanne");
        serviceMetier.creerLieu(lieu);
        serviceMetier.creerDemande(new Demande(new Date(), new Date(), adh1, foot));
        Evenement event1equipe = new Evenement1equipe(new Date(), belotte, adherents);
        Evenement event2equipe = new Evenement2equipes(new Date(), foot, adhlist1, adhlist2);
        serviceMetier.creerEvenement(event1equipe);
        serviceMetier.creerEvenement(event2equipe);
        serviceMetier.affecterLieuAEvenement(lieu, event1equipe);
        serviceMetier.affecterLieuAEvenement(lieu, event2equipe);
    }  
}
