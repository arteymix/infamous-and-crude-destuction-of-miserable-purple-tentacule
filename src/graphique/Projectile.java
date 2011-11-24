package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency
 */
public class Projectile extends Dessinable implements Collisionable, Serializable {

    private Vecteur position, vitesse = new Vecteur(8, -8);
    private static double GRAVITY = 0.8;
    private final TypeDeProjectile TDP;
    private Rectangle rectangle;

    private enum TypeDeProjectile {

        PROJECTILE_0(InterfaceGraphique.imageBank.MISSILE, 1),
        PROJECTILE_1(InterfaceGraphique.imageBank.MISSILE, 2),
        PROJECTILE_2(InterfaceGraphique.imageBank.MISSILE, 3),
        PROJECTILE_3(InterfaceGraphique.imageBank.MISSILE, 4),
        PROJECTILE_4(InterfaceGraphique.imageBank.MISSILE, 5),
        PROJECTILE_5(InterfaceGraphique.imageBank.MISSILE, 6),
        PROJECTILE_6(InterfaceGraphique.imageBank.MISSILE, 7),
        PROJECTILE_7(InterfaceGraphique.imageBank.MISSILE, 8),
        PROJECTILE_8(InterfaceGraphique.imageBank.MISSILE, 9),
        PROJECTILE_9(InterfaceGraphique.imageBank.MISSILE, 10);
        public final Image IMAGE;
        public final int DOMMAGES;

        private TypeDeProjectile(Image img, int dommages) {
            this.IMAGE = img;
            this.DOMMAGES = dommages;
        }
    }

    /**
     * Un Projectile est un objet qui représente un tir de canon.
     * @param point est le point initial d'où le projectile est tiré.
     * @param orientation est l'orientation initiale du projectile.
     * @param id est propre à chaque projectile et définit ses caractéristiquesé
     */
    public Projectile(Vecteur point, Vecteur orientation, int id) {
        position = point;
        vitesse = orientation;
        image = InterfaceGraphique.imageBank.MISSILE;
        TDP = TypeDeProjectile.PROJECTILE_0;
        rectangle = new Rectangle((int) point.x, (int) point.y, 10, 10);
    }

    @Override
    public void dessiner(Graphics g) {
        if (position.y > MainCanvas.CANVAS_SIZE.y | position.x < 0 | position.x > MainCanvas.CANVAS_SIZE.x) {
            this.isDessinable = false;
        }



        g.drawImage(TDP.IMAGE, (int) ((position.x) -= vitesse.x) - 25, (int) (position.y -= vitesse.y) - 10, 50, 50, null);

        vitesse.y -= GRAVITY;
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.y > MainCanvas.CANVAS_SIZE.y | position.x < 0 | position.x > MainCanvas.CANVAS_SIZE.x) {
            this.isDessinable = false;
        }
        g.drawRect((int) ((position.x) -= vitesse.x) - 5, (int) (position.y -= vitesse.y) - 5, 10, 10);
        vitesse.y -= GRAVITY;
    }

    @Override
    public Rectangle getRectangle() {
        rectangle.x = (int) position.x;
        rectangle.y = (int) position.y;
        return rectangle;
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
        return TDP.DOMMAGES;
    }
}
