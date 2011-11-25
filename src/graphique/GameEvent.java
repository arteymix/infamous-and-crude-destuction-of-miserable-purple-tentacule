package graphique;

import java.awt.Graphics;
import util.Dessinable;
import util.Vecteur;
import main.Main;

/**
 * Contient une série d'évévements que l'on peut afficher en jeu à divers moments.
 * @author Guillaume Poirier-Morency
 */
public class GameEvent {

    /**
     * Exemple d'objet dessinable pour les GameEvent.
     */
    public static Dessinable omg = new Dessinable() {

        private int nbFrame = 100;
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
            g.drawImage(Main.imageBank.NUAGE, (int) position.x, (int) position.y, null);
            onDessiner(g);
        }

        @Override
        public void dessinerDeboguage(Graphics g) {
            g.drawRect((int) position.x, (int) position.y, 10, 10);
            onDessiner(g);
        }
    };
}
