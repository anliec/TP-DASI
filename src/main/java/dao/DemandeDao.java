package dao;

import metier.modele.Activite;
import metier.modele.Demande;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DemandeDao {
    
    public void create(Demande demande) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(demande);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Demande update(Demande demande) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            demande = em.merge(demande);
        }
        catch(Exception e){
            throw e;
        }
        return demande;
    }
    
    public Demande findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Demande demande = null;
        try {
            demande = em.find(Demande.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return demande;
    }
    
    public List<Demande> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> demande = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a");
            demande = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return demande;
    }

    public List<Demande> findNotValidedByActiviteeAndByDate(Activite activite, Date demandeDate) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> demande = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a");
            demande = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        List<Demande> ret = new LinkedList<>();
        for (Demande d:demande) {
            if(d.getActivite().equals(activite) && d.getTraite().equals(false) && d.getDateEvenement().equals(demandeDate))
            {
                ret.add(d);
            }
        }

        return ret;
    }
}
