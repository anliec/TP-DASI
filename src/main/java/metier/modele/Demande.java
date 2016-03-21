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
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEvenement;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    private Boolean traite;
    @ManyToOne
    private Adherent demandeur;
    @ManyToOne
    private Activite activite;

    public Demande() {
    }

    public Demande(Date dateEvenement, Date dateDemande, Adherent demandeur, Activite activite) {
       
        this.dateEvenement = dateEvenement;
        this.dateDemande = dateDemande;
        this.traite = false;
        this.demandeur = demandeur;
        this.activite = activite;
    }

    public Long getId() {
        return id;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public Boolean getTraite() {
        return traite;
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

    public void setTraite(Boolean traite) {
        this.traite = traite;
    }

    public void setDemandeur(Adherent demandeur) {
        this.demandeur = demandeur;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", Demandeur=" + demandeur +  
                ", Activite=" + activite.getDenomination() + ", DateDemande=" + 
                dateDemande + ", DateEvenement=" + dateEvenement + '}';
    }
       
}
