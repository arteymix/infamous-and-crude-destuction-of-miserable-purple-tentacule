package graphique;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.Random;
import main.Main;
import util.Dessinable;
import util.Vecteur;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Nuage extends Dessinable implements Serializable {

    /**
     * 
     */
    
    private Vecteur position = new Vecteur(0, (new Random()).nextInt(100) + 50);
    Image img = Main.imageBank.NUAGE;
    
    /**
     * 
     */
    public static void createNuage() {
    if ((new Random()).nextInt(Main.gameValues.nuage.PROBABILITE_APPARITION_NUAGE) == 1) {
            Main.gameValues.composantesDessinables.add(new Nuage());
        }
    
    
    
    }

    @Override
    public void dessiner(Graphics g) {
        
        if (position.x > Main.gameValues.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawImage(img, (int) position.x, (int) position.y, null);
            position.x += 0.5;
        }
    }
    
    

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.x > Main.gameValues.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawRect((int) position.x, (int) position.y, 200, 100);
            g.drawString("Nuage", (int) position.x + 100, (int) position.y + 50);
            position.x += 0.25;
        }
    }

    
}
