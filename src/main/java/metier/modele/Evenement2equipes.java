package metier.modele;

import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author pllefebvre
 */
@Entity
public class Evenement2equipes extends Evenement {

    @ManyToMany
    @JoinTable(name = "ListeEquipeA")
    @ElementCollection
    private List<Adherent> equipeA;
    @ManyToMany
    @JoinTable(name = "ListeEquipeB")
    @ElementCollection
    private List<Adherent> equipeB;

    public Evenement2equipes() {
        super();
    }

    public Evenement2equipes(Date date, Activite activite, List<Adherent> listeEquipeA, List<Adherent> listeEquipeB) {
        super(date, activite);
        this.equipeA = listeEquipeA;
        this.equipeB = listeEquipeB;
    }

    public List<Adherent> getListeEquipeA() {
        return equipeA;
    }

    public List<Adherent> getListeEquipeB() {
        return equipeB;
    }

    public void setListeEquipeA(List<Adherent> listeEquipeA) {
        this.equipeA = listeEquipeA;
    }

    public void setListeEquipeB(List<Adherent> listeEquipeB) {
        this.equipeB = listeEquipeB;
    }
}
