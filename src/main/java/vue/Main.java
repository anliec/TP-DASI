/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import metier.modele.*;
import metier.service.ServiceMetier;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pllefebvre
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static ServiceMetier serviceMetier = new ServiceMetier();

    public static void main(String[] args) {

        System.out.println("=========================================================");
        System.out.println("===  Bienvenue dans la méthode de test de Collect'IF  ===");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println();

        while(menu());

        System.out.println();
        System.out.println();
        System.out.println("au revoir");

        /*ServiceMetier serviceMetier = new ServiceMetier();
        ServiceTechnique serviceTehnique = new ServiceTechnique();
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
        serviceMetier.creerEvenement(new Evenement1equipe(new Date(), belotte, adherents));
        serviceMetier.creerEvenement(new Evenement2equipes(new Date(), foot, adhlist1, adhlist2));*/
    }

    public static boolean menu(){

        System.out.println("======================== Menu ========================");
        System.out.println();
        System.out.println(" 1) Lister les activites");
        System.out.println(" 2) Lister l'historique des demandes d’un adherent");
        System.out.println(" 3) Affecter un lieu");
        System.out.println(" 4) Creer un Adherent");
        System.out.println(" 5) Creer une Demande");
        System.out.println(" 6) Creer une Activite");
        System.out.println(" 0) Quitter");
        System.out.println();
        System.out.print("Que voulez-vous faire ? : ");
        int s = sc.nextInt();
        System.out.println();

        switch (s)
        {
            case 0:
                return false;
            case 1:
                listerActivites();
                break;
            case 2:
                listerHistoriqueDesDemandes();
                break;
            case 3:
                affecterUnLieu();
                break;
            case 4:
                creerAdherent();
                break;
            case 5:
                creerDemande();
                break;
            case 6:
                creerActivite();
                break;
            default:
                System.out.println("saissie incorect, veulliez entrer une valeur correcte");
                System.out.println();
        }

        return true;
    }

    public static void listerActivites(){
        System.out.println();
        System.out.println("liste des activitee:");
        List<Activite> activiteList = serviceMetier.obtenirActivitees();
        int i=0;
        for (Activite a:activiteList) {
            System.out.println((i++)+": "+a);
        }
        System.out.println();
        System.out.println();
    }

    public static void listerHistoriqueDesDemandes(){
        System.out.println();
        Adherent adherent;
        do{
            System.out.println("Pour quel adherent voullez vous avoir la liste ? [mail adherent]");
            String mail = sc.next();
            adherent = serviceMetier.trouverAdherent(mail);
        }while (adherent == null);
        System.out.println();
        System.out.println("liste de l'historique des demandes pour l'adherent:");
        List<Demande> demandes = serviceMetier.obtenirDemandesTrierParDatePourAdherent(adherent);
        int i=0;
        for(Demande d:demandes){
            System.out.println((i++)+": "+d);
        }
        System.out.println();
    }

    public static void affecterUnLieu(){
        System.out.println();
        System.out.print("Voulez vous une liste des evenements sans lieu ? [O/n]:");
        String r = sc.next();
        System.out.println();
        if(!r.equals("n") && !r.equals("N")){
            List<Evenement> evenementList = serviceMetier.obtenirEvenementSansLieu();
            int i=0;
            for(Evenement e:evenementList){
                System.out.println((i++)+": "+e);
            }
            System.out.println();
        }
        Evenement event;
        do{
            System.out.println("A quel evenement voullez vous affecter un lieu ? [id]");
            long eventId = sc.nextLong();
            event = serviceMetier.trouverEvenement(eventId);
        }while(event==null);
        //TODO finir liste lieux
        System.out.print("Voulez vous une liste des lieux ? [O/n]:");
        r = sc.next();
        if(!r.equals("n") && !r.equals("N")){
            List<Lieu> lieuList = serviceMetier.obtenirLieux();
            int i=0;
            for(Lieu l:lieuList){
                System.out.println((i++)+": "+l);
            }
            System.out.println();
        }
        Lieu lieu;
        do{
            System.out.println("A quel lieu voullez vous affecter cet evenement ? [id]");
            long lieuId = sc.nextLong();
            lieu = serviceMetier.trouverLieu(lieuId);
        }while(lieu==null);
        serviceMetier.affecterLieuAEvenement(lieu,event);
        System.out.println();
    }

    public static void creerAdherent(){
        System.out.println();
        System.out.println("creation d'un nouvel adherent:");
        System.out.print("adresse mail: ");
        String mail = sc.next();
        System.out.println("[les autres paramettres sont en dure dans le code]");
        Adherent a = new Adherent("Edouard","Dupont","42 rue des oies 00 000 Quelquepart",mail);
        if(serviceMetier.creerAdherent(a)==1){
            System.out.print("[OK]");
        }
        else{
            System.out.println("[ERR]");
        }
        System.out.println();
    }

    public static void creerDemande(){
        System.out.println();
        Adherent a;
        do{
            System.out.print("Quel adherent creer la demande ? [mail]: ");
            String mail = sc.next();
            a = serviceMetier.trouverAdherent(mail);
        }while (a==null);
        System.out.println("[les autres paramettres sont en dure dans le code]");
        Activite act = serviceMetier.obtenirActivitees().get(0);
        Demande demande = new Demande(new Date(), new Date(), a,act);
        serviceMetier.creerDemande(demande);
        System.out.println();
    }

    public static void creerActivite(){
        System.out.println();
        System.out.println("Creation d'une nouvelle activite:");
        System.out.print("Quel est le nom de cette activite ?: ");
        String nom = sc.next();
        System.out.println();
        System.out.print("Est-ce une activite par equipe ? [O/n]: ");
        String r = sc.next();
        boolean equipe = false;
        if(!r.equals("n") && !r.equals("N")){
            equipe = true;
        }
        System.out.println();
        System.out.print("Combien y a t'il de participant ?: ");
        int nbrParticipant = sc.nextInt();
        System.out.println();
        serviceMetier.creerActivite(new Activite(nom,equipe,nbrParticipant));
    }
}
