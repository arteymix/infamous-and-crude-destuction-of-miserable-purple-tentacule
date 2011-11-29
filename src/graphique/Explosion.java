/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Graphics;
import util.Dessinable;
import util.Vecteur;
import main.Main;

/**
 * Objet pour générer une explosion dans le jeu. Devrait éventuellement permettre
 * des explosions de toutes tailles!
 * @author guillaume
 */
class Explosion extends Dessinable {

    private int nbFrame = 10;
    private final Vecteur POSITION;
    

    /**
     * Constructeur pour une explosion!
     * @param position est la position de l'explosion.
     */
    public Explosion(Vecteur position) {
        POSITION = position;
        image0 = Main.imageBank.EXPLOSION;
    }

    @Override
    public void dessiner(Graphics g) {

        if (nbFrame > 0) {
            g.drawImage(image0, (int) POSITION.x, (int) POSITION.y,60,60, null);
            
            nbFrame--;
            System.out.println("shit");
        } else {
            this.isDessinable = false;
            System.out.println("shat");
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (nbFrame > 0) {
            g.drawString("EXPLOSION!", (int) POSITION.x, (int) POSITION.y);
            nbFrame--;
            System.out.println("shit");
        } else {
            this.isDessinable = false;
            System.out.println("shat");
        }
    }
}
