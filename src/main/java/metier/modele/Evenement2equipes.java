package metier.modele;

import java.util.Date;
import java.util.List;
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
    @JoinTable(name = "ListeEquipe1")
    private List<Adherent> listeEquipe1;
    @ManyToMany
    @JoinTable(name = "ListeEquipe2")
    private List<Adherent> listeEquipe2;

    public Evenement2equipes() {
        super();
    }

    public Evenement2equipes(Date date, Activite activite, List<Adherent> listeEquipe1, List<Adherent> listeEquipe2) {
        super(date, activite);
        this.listeEquipe1 = listeEquipe1;
        this.listeEquipe2 = listeEquipe2;
    }

    public List<Adherent> getListeEquipe1() {
        return listeEquipe1;
    }

    public List<Adherent> getListeEquipe2() {
        return listeEquipe2;
    }

    public void setListeEquipe1(List<Adherent> listeEquipe1) {
        this.listeEquipe1 = listeEquipe1;
    }

    public void setListeEquipe2(List<Adherent> listeEquipe2) {
        this.listeEquipe2 = listeEquipe2;
    }
}
