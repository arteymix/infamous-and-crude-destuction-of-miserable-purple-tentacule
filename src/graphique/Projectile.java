package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency
 */
public class Projectile extends Dessinable implements Collisionable {

    private Vecteur position, vitesse = new Vecteur(10, -10);
    
    private final int POSITION_INIT_Y;

    public Projectile(Vecteur point,Vecteur orientation) {
        position = point;
        POSITION_INIT_Y = (int) point.y;
    }    

    @Override
    public void dessiner(Graphics g) {
        g.drawRect((int) (position.x += vitesse.x), (int) (position.y += vitesse.y), 10, 10);
        vitesse.y += 1;
        if (position.y >= POSITION_INIT_Y) {
            isDessinable = false;
        }
    }   

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawRect((int) (position.x += vitesse.x), (int) (position.y += vitesse.y), 10, 10);
        vitesse.y += 1;
        if (position.y >= POSITION_INIT_Y) {
            isDessinable = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.x, 10, 10);
    }

    @Override
    public void collision(Collisionable c) {
        if (!(c instanceof Projectile)) {
            // Le projectile a frappé quelque chose, il sera détruit!
            this.isDessinable = false;

        }
    }

}
