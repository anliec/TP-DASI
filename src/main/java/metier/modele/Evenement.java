package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateEvenement;
    @ManyToOne
    protected Activite activite;
    @ManyToOne
    protected Lieu lieu;

    public Evenement() {
    }

    public Evenement(Date date, Activite activite) {
       
        this.dateEvenement = date;
        this.activite = activite;
    }

    public Long getId() {
        return id;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
    
     @Override
    public String toString() {
        return "Evenement{" + "id=" + id +  ", Activite=" + 
                activite.getDenomination() + ", DateEvenement=" + dateEvenement 
                + '}';
    }
}
