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
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Projectile extends Dessinable implements Collisionable, Serializable {

    private Vecteur position, vitesse = new Vecteur(8, -8);
    private Rectangle rectangle;

    /**
     * Un Projectile est un objet qui représente un tir de canon.
     * @param point est le point initial d'où le projectile est tiré.
     * @param orientation est l'orientation initiale du projectile.
     * @param id est propre à chaque projectile et définit ses caractéristiquesé
     */
    public Projectile(Vecteur point, Vecteur orientation, int id) {
        position = point;
        vitesse = orientation;
        image0 = Main.imageBank.projectile;
        rectangle = new Rectangle((int) point.x, (int) point.y, 10, 10);
    }

    @Override
    public void dessiner(Graphics g) {
        if (position.y > Main.gameValues.canvasSize.y | position.x < 0 | position.x > Main.gameValues.canvasSize.x) {
            this.isDessinable = false;
        }



        g.drawImage(image0, (int) ((position.x) -= vitesse.x) - 25, (int) (position.y -= vitesse.y) - 10, null);

        vitesse.y -= Main.gameValues.projectile.GRAVITY;
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.y > Main.gameValues.canvasSize.y | position.x < 0 | position.x > Main.gameValues.canvasSize.x) {
            this.isDessinable = false;
        }
        g.drawRect((int) ((position.x) -= vitesse.x) - 5, (int) (position.y -= vitesse.y) - 5, 10, 10);
        vitesse.y -= Main.gameValues.projectile.GRAVITY;
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
            Main.gameValues.composantesDessinables.add(new Explosion(this.position));
            System.out.println(this + " reçoit collision de " + c);
        }
    }

    @Override
    public int getDommage() {
        return 25;
    }
}
