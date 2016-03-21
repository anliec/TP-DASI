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
    
    public void creerAdherent(Adherent adherent) {
        
        try {
            JpaUtil.creerEntityManager();
           
            try {
                JpaUtil.ouvrirTransaction();
                adherentDao.create(adherent);
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
    
    public void creerLieu(Lieu lieu) {
        
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

    public Activite trouverActivite(String name)
    {
        Activite ret=null;
        try {
            JpaUtil.creerEntityManager();
            try {
                JpaUtil.ouvrirTransaction();
                ret = activiteDao.findByName(name);
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

    public List<Activite> afficherActivitees()
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

    public List<Demande> afficherDemandesTrierParDatePourAdherent(Adherent adherent)
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

    public List<Demande> afficherDemandesTrierParActivitee()
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

    public List<Evenement> afficherEvenementSansLieu() {
        
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
                    //répartie les demendeurs dans les équipes (si plusieurs équipes)
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

        } catch (Exception e) {

            System.err.println("entiyManager creation error");
            e.printStackTrace();
        }
    }

}
