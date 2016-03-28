/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.*;
import metier.modele.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pllefebvre
 */

public class ServiceMetier {
    
    private final ActiviteDao activiteDao = new ActiviteDao();
    private final AdherentDao adherentDao = new AdherentDao();
    private final LieuDao lieuDao = new LieuDao();
    private final DemandeDao demandeDao = new DemandeDao();
    private final EvenementDao evenementDao = new EvenementDao();
    
    /**
     * Methode permettant d'ajouter un adherent a la base, seulement si celui-ci
     * identife par son adresse mail n'existe pas deja dans la base de donne, gere
     * aussi l'envoi des mails d'infirmation ou de confirmation d'inscription
     * @param adherent l'adherent a ajouter
     * @return 1 si l'adherent a ete correctement ajoute 0 sinon
     */
    public int creerAdherent(Adherent adherent) {
        
        ServiceTechnique.majCoordonnees(adherent);
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();

                if(adherentDao.findByMail(adherent.getMail()) == null) {

                    adherentDao.create(adherent);
                    JpaUtil.validerTransaction();
                    ServiceTechnique.mailConfirmationInscriptionAdherent(adherent);
                    ServiceTechnique.mailConfirmationInscriptionResponsable(adherent);
                    return 1;

                } else {

                    ServiceTechnique.mailInfirmationInscriptionAdherent(adherent);
                    ServiceTechnique.mailInfirmationInscriptionResponsable(adherent);
                    JpaUtil.annulerTransaction();
                }

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);

            }
            
            JpaUtil.fermerEntityManager();

        } catch (Exception e) {
            
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Methode permettant d'ajouter une activite a la base
     * @param activite l'activite a ajouter
     */
    public void creerActivite(Activite activite) {
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();
                activiteDao.create(activite);
                JpaUtil.validerTransaction();
           
            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JpaUtil.fermerEntityManager();
            
        } catch (Exception e) {
            
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }

    /**
     * Methode permettant d'ajouter un lieu a la base
     * @param lieu l'adherent a ajouter
     */
    public void creerLieu(Lieu lieu) {
        
        ServiceTechnique.majCoordonnees(lieu);
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();
                lieuDao.create(lieu);
                JpaUtil.validerTransaction();
           
            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JpaUtil.fermerEntityManager();
            
        } catch (Exception e) {
            
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }

    /**
     * Ajoute a la base de donnee une demande
     * @param demande la demande ajoute a la base de donnee
     */
    public void creerDemande(Demande demande) {
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();
                demandeDao.create(demande);
                JpaUtil.validerTransaction();
           
            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JpaUtil.fermerEntityManager();
            
        } catch (Exception e) {
            
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }

    /**
     * Ajoute a la base de donnee un evenement
     * @param evenement la demande ajoute a la base de donnee
     */
    public void creerEvenement(Evenement evenement) {
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();
                evenementDao.create(evenement);
                JpaUtil.validerTransaction();
           
            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JpaUtil.fermerEntityManager();
            
        } catch (Exception e) {
            
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }


    /**
     * Renvoie l'evenemnt corespondant a l'ID fournie
     * @param id le numero de l'evenement demande
     * @return l'evenemnt corespondant a l'ID fournie
     */
    public Evenement trouverEvenement(long id){
        Evenement ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = evenementDao.findById(id);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie le lieu corespondant a l'ID fournie
     * @param id le numero du lieu demande
     * @return le lieu corespondant a l'ID fournie
     */
    public Lieu trouverLieu(long id){
        Lieu ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = lieuDao.findById(id);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie l'adherent corespondant au mail fournie
     * @param mail mail de l'adherent demande
     * @return l'adherent corespondant au mail fournie
     */
    public Adherent trouverAdherent(String mail){
        Adherent ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = adherentDao.findByMail(mail);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie l'adherent corespondant a l'ID fournie
     * @param id le numero de l'adherent demande
     * @return l'adherent corespondant a l'ID fournie
     */
    public Adherent trouverAdherent(long id){
        Adherent ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = adherentDao.findById(id);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie l'activite corespondant au nom fournie
     * @param nom le nom de l'adherent demande
     * @return l'activite corespondant au nom fournie
     */
    public Activite trouverActivite(String nom)
    {
        Activite ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = activiteDao.findByName(nom);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie la liste complete des activitees presante dans la base de donnee
     * @return la liste complete des activitees presante dans la base de donnee
     */
    public List<Activite> obtenirActivitees()
    {
        List<Activite> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = activiteDao.findAll();
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie la liste des demandes de l'adherent donnee en parametre, trier par ordre de date croisante
     * @param adherent l'adherent conserne par la domande
     * @return la liste des demandes de l'adherent donnee en parametre, trier par ordre de date croisante
     */
    public List<Demande> obtenirDemandesTrierParDatePourAdherent(Adherent adherent)
    {
        List<Demande> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = demandeDao.findByAdherentOrderByDate(adherent);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie la liste des demandes de l'adherent donnee en parametre, trier par ordre de date decroisante
     * @param adherent l'adherent conserne par la domande
     * @return la liste des demandes de l'adherent donnee en parametre, trier par ordre de date decroisante
     */
    public List<Demande> afficherDemandesTrierParDateDescPourAdherent(Adherent adherent)
    {
        List<Demande> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = demandeDao.findByAdherentOrderByDateDesc(adherent);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie la liste des demandes contenue dans la base de donnee, trier par nom d'activitee croissant
     * @return la liste des demandes contenue dans la base de donnee, trier par nom d'activitee croissant
     */
    public List<Demande> obtenirDemandesTrierParActivitee()
    {
        List<Demande> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = demandeDao.findAllOrderByActivite();
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Renvoie la liste des demandes contenue dans la base de donnee, trier par nom d'activitee decroissant
     * @return la liste des demandes contenue dans la base de donnee, trier par nom d'activitee decroissant
     */
    public List<Demande> afficherDemandesTrierParActiviteeDesc()
    {
        List<Demande> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = demandeDao.findAllOrderByActiviteDesc();
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Methode renvoye une liste de tous les evenements affectes a aucun lieu
     * @return liste de tous les evenements affectes a aucun lieu
     */
    public List<Evenement> obtenirEvenementSansLieu() {
        
        List<Evenement> listeEvenements = null;

        try {
            JpaUtil.creerEntityManager();

            try {
                JpaUtil.ouvrirTransaction();
                listeEvenements = evenementDao.findSansLieu();
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }

            JpaUtil.fermerEntityManager();

        } catch (Exception e) {

            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
            return listeEvenements;
    }

    /**
     * Renvoie l'evenement creer si la creation a eu lieu, null sinon
     * @param activite activitee a chercher
     * @param date date de l'activitee
     * @return l'evenement creer si la creation a eu lieu, null sinon
     */
    public Evenement rechercherEtCreerEvenement(Activite activite, Date date){
        Evenement event = null;
        try {
            JpaUtil.creerEntityManager();

            try {
                JpaUtil.ouvrirTransaction();
                List<Demande> demandes = demandeDao.findNotValidedByActiviteeAndByDate(activite, date);
                if(activite.getNbParticipants() <= demandes.size() ) {
                    LinkedList<Adherent> equipeA = new LinkedList<>();
                    LinkedList<Adherent> equipeB = new LinkedList<>();
                    //repartie les demendeurs dans les equipes (si plusieurs equipes)
                    for(Demande d:demandes) {
                        Adherent a=d.getDemandeur();
                        if(equipeA.size() <= equipeB.size() || !activite.isParEquipe()) {
                            equipeA.add(a);
                        }
                        else {
                            equipeB.add(a);
                        }
                    }
                    if(activite.isParEquipe()) {
                        event = new Evenement2equipes(date,activite,equipeA,equipeB);
                    }
                    else{
                        event = new Evenement1equipe(date,activite,equipeA);
                    }
                    evenementDao.create(event);
                }
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }

            JpaUtil.fermerEntityManager();

        } catch (Exception e) {

            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return event;
    }

    /**
     * Affecte un lieu a un evenement et envoye le mail correspondant
     * @param lieu le lieu a affecter
     * @param evenement l'evenement en question
     */
    public void affecterLieuAEvenement(Lieu lieu, Evenement evenement) {

        evenement.setLieu(lieu);

         try {
            JpaUtil.creerEntityManager();

            try {
                JpaUtil.ouvrirTransaction();
                evenementDao.update(evenement);
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }

            JpaUtil.fermerEntityManager();
            
            ServiceTechnique.mailEvenement(evenement);

        } catch (Exception e) {

            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }

    /**
     * Renvoie la liste des Lieux contenue dans la base de donnee
     * @return la liste des Lieux contenue dans la base de donnee
     */
    public List<Lieu> obtenirLieux() {

        List<Lieu> ret =null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = lieuDao.findAll();
                JpaUtil.validerTransaction();

            } catch (Throwable ex) {
                JpaUtil.annulerTransaction();
                Logger.getLogger(ServiceMetier.class.getName()).log(Level.SEVERE, null, ex);
            }
            JpaUtil.fermerEntityManager();
        } catch (Exception e) {
            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
        return ret;
    }


}
