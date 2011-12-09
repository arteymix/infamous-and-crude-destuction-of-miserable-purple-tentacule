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
package util;

import java.awt.Rectangle;

/**
 * Interface utilisé pour rendre un objet Dessinable collisionable. Ainsi, le
 * instanceof retournera true et le cast (Collisionable) sera légal pour tester
 * une collision entre deux objets du type Collisionable.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public interface Collisionable {

    /**
     * Retourne le Rectangle de l'objet Collisionable. Ce Rectangle sert d'objet
     * de calcul lorsqu'un objet Collisionable entre en collision avec un autre
     * objet du même type. 
     * @return le rectangle de l'objet Collisionable.
     */
    public Rectangle getRectangle();

    /**
     * Provoque une collision de l'objet courant avec un autre objet Collisionable.
     * @param c est l'objet Collisionable qui a provoqué la collision.
     */
    public void collision(Collisionable c);

    /**
     * Méthode qui obtient les dommage qu'un objet collisionable cause lors d'une collision.
     * @return les dommages que l'objet collisionnable cause lors d'une collision.
     */
    public int getDommage();
}
