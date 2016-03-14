/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ActiviteDao;
import dao.AdherentDao;
import dao.DemandeDao;
import dao.EvenementDao;
import dao.JpaUtil;
import dao.LieuDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Adherent;
import metier.modele.Activite;
import metier.modele.Demande;
import metier.modele.Evenement;
import metier.modele.Lieu;

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

    public void rechercherEtCreerEvenement(String nomActivitee){
        try {
            JpaUtil.creerEntityManager();

            try {
                JpaUtil.ouvrirTransaction();
                List<Demande> demandes = demandeDao.findByActivitee(nomActivitee);
                Activite activite = activiteDao.findByName(nomActivitee);
                if(activite.getNbParticipants() < demandes.size() )
                {

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
