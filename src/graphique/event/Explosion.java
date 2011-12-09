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
package graphique.event;

import java.awt.Graphics;
import util.Dessinable;
import util.Vecteur;
import main.Main;

/**
 * Objet pour générer une explosion dans le jeu. Devrait éventuellement permettre
 * des explosions de toutes tailles!
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public class Explosion extends Dessinable {

    private int nbFrame = 10;
    private final Vecteur POSITION;
    

    /**
     * Constructeur pour une explosion!
     * @param position est la position de l'explosion.
     */
    public Explosion(Vecteur position) {
        POSITION = position;
        image0 = Main.imageBank.explosion;
    }

    @Override
    public void dessiner(Graphics g) {

        if (nbFrame > 0) {
            g.drawImage(image0, (int) POSITION.x, (int) POSITION.y,60,60, null);
            
            nbFrame--;
           
        } else {
            this.isDessinable = false;
           
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (nbFrame > 0) {
            g.drawString("EXPLOSION!", (int) POSITION.x, (int) POSITION.y);
            nbFrame--;
            
        } else {
            this.isDessinable = false;
           
        }
    }
}
