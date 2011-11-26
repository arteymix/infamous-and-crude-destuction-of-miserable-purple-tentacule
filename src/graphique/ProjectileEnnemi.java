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
package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile ennemi.
 * @author Guillaume Poirier-Morency
 */
public final class ProjectileEnnemi extends Dessinable implements Collisionable, Serializable {

    /**
     * Vecteur définissant la position du projectile ennemi dans l'espace.
     */
    private Vecteur position;
    /**
     *
     */
    private Rectangle rectangle = new Rectangle(0, 0, 30, 30);

    /**
     * 
     * @param init
     * @param id
     */
    public ProjectileEnnemi(Vecteur init, int id) {
        position = new Vecteur(init.x, init.y);
        image = Main.imageBank.MEDUSE;

    }

    @Override
    public void dessiner(Graphics g) {
        g.drawImage(image, (int) position.x, (int) position.y++, rectangle.width, rectangle.height, null);
        if (position.y >= Main.gameValues.canvasSize.y) {
            isDessinable = false;

        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawRect((int) position.x, (int) position.y++, rectangle.width, rectangle.height);
        if (position.y >= Main.gameValues.canvasSize.y) {
            isDessinable = false;

        }
    }

    @Override
    public Rectangle getRectangle() {
        rectangle.x = (int) this.position.x;
        rectangle.y = (int) this.position.y;
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        if (c instanceof Projectile) {

            this.isDessinable = false;

        } else if (c instanceof Canon) {
            if (((Canon) c).NUMERO_DU_CANON == 1 && !Main.gameValues.canon.isCanon2ValidTarget) {
          // Ne rien faire, le canon ne  peut être atteint, il est invisible.
            } else {
                this.isDessinable = false;
            }

        }
    }

    @Override
    public int getDommage() {
        return 10;
    }
}
