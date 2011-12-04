/*   This file is part of TP2.
 *
 *   TP2 is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   TP2 is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with TP2.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphique.component;

import graphique.event.Explosion;
import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Projectile extends Dessinable implements Collisionable {

    private static final double GRAVITY = 0.8;
    private Vecteur position, vitesse = new Vecteur(8, -8);
    private Rectangle rectangle;

    /**
     * Un Projectile est un objet qui représente un tir de canon.
     * @param point est le point initial d'où le projectile est tiré.
     * @param orientation est l'orientation initiale du projectile.
     * @param id est propre à chaque projectile et définit ses caractéristiquesé
     */
    public Projectile(Vecteur point, Vecteur orientation, int id, double tetha) {
        this.tetha = tetha + Math.PI;
        position = point;
        vitesse = orientation;
        image0 = Main.imageBank.projectile;
        rectangle = new Rectangle((int) point.x, (int) point.y, 10, 10);
    }

    @Override
    public void dessiner(Graphics g) {
        if (position.y > Main.getCanvasSizeY() | position.x < 0 | position.x > Main.getCanvasSizeX()) {
            this.isDessinable = false;
        }
        double x = this.position.x;
        double y = this.position.y;
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        at.rotate(tetha += Math.PI / 60, 0, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image0, at, null);
        at.translate(-x, -y);
        //g.drawImage(image0, (int) ((position.x) -= vitesse.x)-10, (int) (position.y -= vitesse.y) - 10, null);
        position.x -= vitesse.x;
        position.y -= vitesse.y;
        vitesse.y -= GRAVITY;
    }
    private double tetha;

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.y > Main.getCanvasSizeY() | position.x < 0 | position.x > Main.getCanvasSizeX()) {
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
        if (!(c instanceof Canon) && !(c instanceof Projectile) && !(c instanceof Powerup)) {
            // Le projectile a frappé quelque chose, il sera détruit!
            this.isDessinable = false;
            Main.composantesDessinables.add(new Explosion(this.position));
        }
    }

    @Override
    public int getDommage() {
        return 5;
    }
}
