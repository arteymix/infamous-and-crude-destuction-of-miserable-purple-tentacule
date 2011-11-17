package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.io.Serializable;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile ennemi.
 * @author Guillaume Poirier-Morency
 */
public final class ProjectileEnnemi extends Dessinable implements Collisionable, Serializable {

    /**
     *
     */
    private Vecteur position;
    public static final int MISSILE_0 = 0;
    public ProjectileEnnemi(int id) {
    
    
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawRect((int) position.x, (int) position.y, 10, 10);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawRect((int) position.x, (int) position.y, 10, 10);
    }

    @Override
    public Rectangle getRectangle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void collision(Collisionable c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getDommage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
