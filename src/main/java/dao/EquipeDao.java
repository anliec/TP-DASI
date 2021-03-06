package dao;

import metier.modele.Evenement;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EvenementDao {
    
    public void create(Evenement evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(evenement);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Evenement update(Evenement evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            evenement = em.merge(evenement);
        }
        catch(Exception e){
            throw e;
        }
        return evenement;
    }
    
    public Evenement findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Evenement evemenent = null;
        try {
            evemenent = em.find(Evenement.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return evemenent;
    }
    
    public List<Evenement> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Evenement> evenement = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a");
            evenement = (List<Evenement>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenement;
    }
    
    public List<Evenement> findSansLieu() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Evenement> evenement = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a WHERE a.lieu IS NULL");
            evenement = (List<Evenement>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenement;
    }
    
    /**
     * Methode renvoyant la liste des evenements affectes a un lieu donne, utlisee
     * pour determiner si un lieu a deja ete affecte a un evenement
     * @param idLieu l'id du lieu
     * @return la lsite d'evenements en question
     */
    
    public List<Evenement> findByIdLieu(Long idLieu) {
        
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Evenement> evenement = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a WHERE a.lieu.id = " + idLieu + "");
            evenement = (List<Evenement>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenement;
    }
}
