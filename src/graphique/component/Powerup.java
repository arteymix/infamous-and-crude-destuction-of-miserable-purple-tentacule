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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import util.Collisionable;
import util.Dessinable;

/**
 * Objet dessinable pour les powerups.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Powerup extends Dessinable implements Collisionable, Serializable {

    @Override
    public void dessiner(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getRectangle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void collision(Collisionable c) {
        // Un powerup peut peut être détruit par sa collision avec un projectile ennemi ou un ovni
        if (c instanceof ProjectileEnnemi | c instanceof Ovni) {
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        // Un powerup ne cause pas de dommages à son contact, sauf exceptions.
        return 0;
    }

   
}
