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
import metier.service.ServiceTest;

/**
 *
 * @author pllefebvre
 */
public class Main {

    private static final ServiceMetier serviceMetier = new ServiceMetier();

    public static void main(String[] args) {

        System.out.println("==========================================================");
        System.out.println("===  Bienvenue dans l'interface de test de Collect'IF  ===");
        System.out.println("==========================================================");
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
        System.out.println(" 7) Creer un Lieu");
        System.out.println(" 0) Quitter");
        System.out.println();
        System.out.print("Que voulez-vous faire ? : ");
        int s;
        if(ServiceTest.hasNextInt()){
            s = ServiceTest.nextInt();
        }
        else{
            while(!ServiceTest.hasNextInt()){
                ServiceTest.next();
            }
            s=-1;
        }
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
            case 7:
                creerLieu();
                break;
            default:
                System.out.println("saisie incorecte, veuillez entrer une valeur correcte");
                System.out.println();
        }

        return true;
    }

    public static void listerActivites(){
        System.out.println();
        System.out.println("liste des activites:");
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
            System.out.println("Pour quel adherent voulez vous avoir la liste ? [mail adherent]");
            String mail = ServiceTest.next();
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
        String r = ServiceTest.next();
        System.out.println();
        if(!r.equals("n") && !r.equals("N")){
            List<Evenement> evenementList = serviceMetier.obtenirEvenementSansLieu();
            int i=0;
            for(Evenement e:evenementList){
                System.out.println((i++)+": "+e);
            }
            System.out.println();
            if(evenementList.size() == 0){
                return;
            }
        }
        Evenement event;
        do{
            System.out.println("A quel evenement voulez vous affecter un lieu ? [id]");
            long eventId = ServiceTest.nextLong();
            event = serviceMetier.trouverEvenement(eventId);
            if(eventId == -1){
                return;
            }
        }while(event==null);
        System.out.print("Voulez vous une liste des lieux ? [O/n]:");
        r = ServiceTest.next();
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
            System.out.println("A quel lieu voulez vous affecter cet evenement ? [id]");
            long lieuId = ServiceTest.nextLong();
            lieu = serviceMetier.trouverLieu(lieuId);
        }while(lieu==null);
        serviceMetier.affecterLieuAEvenement(lieu,event);
        System.out.println();
    }

    public static void creerAdherent(){
        System.out.println();
        System.out.println("creation d'un nouvel adherent:");
        System.out.print("adresse mail: ");
        String mail = ServiceTest.next();
        System.out.println("[les autres parametres sont en dur dans le code]");
        Adherent a = new Adherent("Edouard","Dupont","20 avenue albert Einstein 69100 Villeurbanne France",mail);
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
            System.out.print("Quel adherent cree la demande ? [mail]: ");
            String mail = ServiceTest.next();
            a = serviceMetier.trouverAdherent(mail);
        }while (a==null);
        System.out.println("[les autres parametres sont en dur dans le code]");
        Activite act = serviceMetier.obtenirActivitees().get(0);
        Demande demande = new Demande(new Date(), new Date(), a,act);
        serviceMetier.creerDemande(demande);
        System.out.println();
    }

    public static void creerActivite(){
        System.out.println();
        System.out.println("Creation d'une nouvelle activite:");
        System.out.print("Quel est le nom de cette activite ?: ");
        String nom = ServiceTest.next();
        System.out.println();
        System.out.print("Est-ce une activite par equipe ? [O/n]: ");
        String r = ServiceTest.next();
        boolean equipe = false;
        if(!r.equals("n") && !r.equals("N")){
            equipe = true;
        }
        System.out.println();
        System.out.print("Combien y a t'il de participants ?: ");
        int nbrParticipant = ServiceTest.nextInt();
        System.out.println();
        serviceMetier.creerActivite(new Activite(nom,equipe,nbrParticipant));
    }

    public static void creerLieu() {
        System.out.println();
        System.out.println("Creation d'un nouveau lieu:");
        System.out.print("Quel est le nom de ce lieu ?: ");
        String nom = ServiceTest.nextLine();
        System.out.println();
        System.out.print("Pouvez-vous donner une description de ce lieu ?: ");
        ServiceTest.next();
        String description = ServiceTest.nextLine();
        System.out.println();
        System.out.print("Pouvez-vous donner l'adresse de ce lieu ?: ");
        ServiceTest.next();
        String adresse = ServiceTest.nextLine();
        System.out.println();
        serviceMetier.creerLieu(new Lieu(nom,description,adresse));
    }
}
