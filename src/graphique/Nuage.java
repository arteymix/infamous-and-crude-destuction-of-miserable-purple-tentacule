package graphique;

import java.awt.Graphics;
import java.util.Random;
import util.Dessinable;
import util.Vecteur;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Nuage extends Dessinable {

    private Vecteur position = new Vecteur(0, (new Random()).nextInt(100) + 50);

    @Override
    public void dessiner(Graphics g) {
        if (position.x > InterfaceGraphique.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawRect((int) position.x, (int) position.y, 200, 100);
            position.x += 0.5;
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.x > InterfaceGraphique.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawRect((int) position.x, (int) position.y, 200, 100);
            g.drawString("Nuage", (int) position.x + 100, (int) position.y + 50);
            position.x += 0.5;
        }
    }
}
