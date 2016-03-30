package dao;

import metier.modele.Activite;
import metier.modele.Adherent;
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
            Query q = em.createQuery("SELECT a FROM Demande a WHERE a.activite.id="+activite.getId());
            demande = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        System.out.println("demandes trouvees : "+demande.size());
        List<Demande> ret = new LinkedList<>();
        for (Demande d:demande) {
            if(/*d.getActivite().equals(activite) &&*/ d.getTraite().equals(false) && d.getDateEvenement().getDate()==demandeDate.getDate() && 
                    d.getDateEvenement().getMonth()==demandeDate.getMonth() && d.getDateEvenement().getYear()==demandeDate.getYear())
            {
                ret.add(d);
            }
            else{
                System.out.println("traitee: "+d.getTraite()+" date: "+d.getDateEvenement()+" != "+demandeDate);
            }
        }
        System.out.println("demande selectionnee: "+ret.size());
        return ret;
    }

    public List<Demande> findByAdherentOrderByDate(Adherent adherent) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> demande = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a WHERE a.demandeur.id = "+adherent.getId()+" ORDER BY a.dateDemande ASC");
            demande = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        /*List<Demande> ret = new LinkedList<>();
        for (Demande d:demande) {
            if(d.getDemandeur().equals(adherent))
            {
                ret.add(d);
            }
        }

        return ret;*/
        return demande;
    }

    public List<Demande> findByAdherentOrderByDateDesc(Adherent adherent) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> demande = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a WHERE a.demandeur.id = "+adherent.getId()+" ORDER BY a.dateEvenement DESC");
            demande = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        /*List<Demande> ret = new LinkedList<>();
        for (Demande d:demande) {
            if(d.getDemandeur().equals(adherent))
            {
                ret.add(d);
            }
        }

        return ret;*/
        return demande;
    }

    public List<Demande> findAllOrderByActivite() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> ret = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a ORDER BY a.activite.denomination ASC");
            ret = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return ret;
    }

    public List<Demande> findAllOrderByActiviteDesc() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Demande> ret = null;
        try {
            Query q = em.createQuery("SELECT a FROM Demande a ORDER BY a.activite.denomination DESC");
            ret = (List<Demande>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return ret;
    }
}
