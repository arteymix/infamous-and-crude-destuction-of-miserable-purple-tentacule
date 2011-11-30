/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique.event;

import java.awt.Graphics;
import util.Dessinable;
import util.Vecteur;

/**
 *
 * @author guillaume
 */
/**
     * Event qui se produit quand le boss lance de l'encre sur l'écran!
     */
    public class BossInkScreen extends Dessinable {

        private int nbFrame = 1000000;
        private Vecteur position = new Vecteur(10, 10);

        private void onDessiner(Graphics g) {
            if (position.x == 0) {
                this.isDessinable = false;
            }
            nbFrame--;
            position.x--;
        }

        @Override
        public void dessiner(Graphics g) {
            g.fillRect(0, 0, 500, 500);
            onDessiner(g);
        }

        @Override
        public void dessinerDeboguage(Graphics g) {
            g.drawRect((int) position.x, (int) position.y, 10, 10);
            onDessiner(g);
        }
    }
