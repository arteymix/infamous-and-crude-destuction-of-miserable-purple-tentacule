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
import java.io.Serializable;
import java.util.Random;
import main.Main;
import util.Dessinable;
import util.Vecteur;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class DecorFlottant extends Dessinable implements Serializable {

    /**
     * 
     */
    private Vecteur position = new Vecteur(0, (new Random()).nextInt(100) + 50);
    

    private DecorFlottant(int id) {

        switch (id) {

            case NUAGE:
                image0 = Main.imageBank.NUAGE;





        }


    }
    private static final int NUAGE = 0;

    /**
     * 
     */
    public static void createNuage() {
        if ((new Random()).nextInt(Main.gameValues.nuage.PROBABILITE_APPARITION_NUAGE) == 1) {
            Main.gameValues.composantesDessinables.add(new DecorFlottant(NUAGE));
        }



    }

    @Override
    public void dessiner(Graphics g) {

        if (position.x > Main.gameValues.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawImage(image0, (int) position.x, (int) position.y, null);
            position.x += 0.5;
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.x > Main.gameValues.canvasSize.x) {
            isDessinable = false;
        } else {
            g.drawRect((int) position.x, (int) position.y, 200, 100);
            g.drawString("Nuage", (int) position.x + 100, (int) position.y + 50);
            position.x += 0.25;
        }
    }
}
