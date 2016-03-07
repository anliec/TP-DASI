/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import metier.modele.Adherent;
import metier.service.ServiceMetier;
import metier.service.ServiceTechnique;
import modele.Activite;
import modele.Lieu;

/**
 *
 * @author pllefebvre
 */
public class Main {
    
    public static void main(String[] args) {
        
        ServiceMetier serviceMetier = new ServiceMetier();
        ServiceTechnique serviceTehnique = new ServiceTechnique();
        
        serviceMetier.creerActivite(new Activite("Foot", true, 22));
        serviceMetier.creerActivite(new Activite("Belotte", false, 4));
        serviceMetier.creerLieu(new Lieu("Gymnase test", "type test", "00 adresse test, 69100 Villeurbanne"));
        
    }  
}
