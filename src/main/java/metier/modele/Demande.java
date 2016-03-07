package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEvenement;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    @ManyToOne
    private Adherent demandeur;
    @ManyToOne
    private Activite activite;

    public Demande() {
    }

    public Demande(Date dateEvenement, Date dateDemande, Adherent demandeur, Activite activite) {
       
        this.dateEvenement = dateEvenement;
        this.dateDemande = dateDemande;
        this.demandeur = demandeur;
        this.activite = activite;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public Adherent getDemandeur() {
        return demandeur;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setDemandeur(Adherent demandeur) {
        this.demandeur = demandeur;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", Demandeur=" + demandeur +  ", Activite=" + activite + ", DateDemande=" + dateDemande + ", DateEvenement=" + dateEvenement + '}';
    }
       
}
