package metier.modele;

import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author pllefebvre
 */
@Entity
public class Evenement1equipe extends Evenement {

    @ManyToMany
    @ElementCollection
    private List<Adherent> listeAdherents;

    public Evenement1equipe() {
        super();
    }

    public Evenement1equipe(Date date, Activite activite, List<Adherent> listeAdherents) {
        super(date, activite);
        this.listeAdherents = listeAdherents;
    }

    public List<Adherent> getListeAdherents() {
        return listeAdherents;
    }

    public void setListeAdherents(List<Adherent> listeAdherents) {
        this.listeAdherents = listeAdherents;
    }
}
