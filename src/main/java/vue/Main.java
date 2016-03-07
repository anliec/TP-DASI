/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.AdherentDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Adherent;

/**
 *
 * @author pllefebvre
 */
public class Main {
    
    public static void main(String[] args) {
        
        AdherentDao adhDao = new AdherentDao();
        adhDao.create(new Adherent("Dubois", "François", "30 rue des fleurs", "fdubois@gmail.com"));
    }  
}
