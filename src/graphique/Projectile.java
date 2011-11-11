package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency
 */
public class Projectile extends Dessinable implements Collisionable, Serializable {

    private Vecteur position, vitesse = new Vecteur(10, -10);
    private static double GRAVITY = 1.0;

    /**
     * Un Projectile est un objet qui représente un tir de canon.
     * @param point est le point initial d'où le projectile est tiré.
     * @param orientation est l'orientation initiale du projectile.
     * @param id est propre à chaque projectile et définit ses caractéristiquesé
     */
    public Projectile(Vecteur point, Vecteur orientation, int id) {
        position = point;
        vitesse = orientation;
        image = Main.imageBank.missile;
    }

    @Override
    public void dessiner(Graphics g) {
        if (position.y > 700) {
            this.isDessinable = false;
        }

        g.drawImage(image, (int) ((position.x) -= vitesse.x) - 5, (int) (position.y -= vitesse.y) - 5, 50, 50, null);
        vitesse.y -= GRAVITY;
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.y > 700) {
            this.isDessinable = false;
        }
        g.drawRect((int) ((position.x) -= vitesse.x) - 5, (int) (position.y -= vitesse.y) - 5, 10, 10);
        vitesse.y -= GRAVITY;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.x, 10, 10);
    }

    @Override
    public void collision(Collisionable c) {
        if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            // Le projectile a frappé quelque chose, il sera détruit!
            this.isDessinable = false;
            System.out.println(this + " reçoit collision de " + c);
        }
    }

    @Override
    public int getDommage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
