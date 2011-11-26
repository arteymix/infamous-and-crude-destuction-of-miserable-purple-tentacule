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

import java.awt.Graphics;
import util.Dessinable;
import util.Vecteur;
import main.Main;

/**
 * Contient une série d'évévements que l'on peut afficher en jeu à divers moments.
 * @author Guillaume Poirier-Morency
 */
public class GameEvent {

    /**
     * Exemple d'objet dessinable pour les GameEvent.
     */
    public static Dessinable omg = new Dessinable() {

        private int nbFrame = 100;
        private Vecteur position = new Vecteur(10, 10);

        private void onDessiner(Graphics g) {
            if (position.x == 0) {
                this.isDessinable = false;
            }
            nbFrame--;
            position.x--;
        }

        @Override
        public void dessiner(Graphics g) {
            g.drawImage(Main.imageBank.NUAGE, (int) position.x, (int) position.y, null);
            onDessiner(g);
        }

        @Override
        public void dessinerDeboguage(Graphics g) {
            g.drawRect((int) position.x, (int) position.y, 10, 10);
            onDessiner(g);
        }
    };
}
