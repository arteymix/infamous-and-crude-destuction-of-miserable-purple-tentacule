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

import graphique.window.MainCanvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Main;
import util.Dessinable;

/**
 * Classe contenant l'objet qui affiche les points que l'utilisateur
 * obtient.
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public final class PointsObtenus extends Dessinable {

    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    /**
     * size est la variable qui détermine la taille du texte qui apparait.
     */
    private int size = 15;
    /**
     * delay est la variable de temps en milliseconde de l'apparition du texte.
     */
    private int delay = 30;
    /**
     * POINTS est la variable qui détermine le pointage de l'action performé.
     */
    private final int POINTS;
    /**
     * font est la variable qui détermine le font du texte apparue.
     */
    private Font font = new Font("Comic sans ms", Font.BOLD, size);
    /**
     * POSITION_X est la position en x de l'apparition du texte.
     */
    /**
     * POSITION_Y est la position en y de l'apparition du texte.
     */
    private final int POSITION_X, POSITION_Y;
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Méthode qui détermine les points obtenus
     * @param x est la position en x de l'apparition du texte.
     * @param y  est la position en y de l'apparition du texte.
     * @param points est la variable qui détermine le pointage de l'action performé.
     */
    public PointsObtenus(int x, int y, int points) {
        this.POINTS = points;
        this.POSITION_X = x;
        this.POSITION_Y = y;
    }

    @Override
    public void dessiner(Graphics g) {
        action();
        g.setFont(font);
        if ((POINTS) > 0) {
            g.setColor(Color.BLUE);
            g.drawString(POINTS + " points", POSITION_X, POSITION_Y);
        } else {
            g.setColor(Color.RED);
            g.drawString(POINTS + " points", (Main.getCanvasSizeX() / 2) - 60, 45);
        }
        g.setFont(MainCanvas.FONT);
        g.setColor(Color.BLACK);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        action();
        g.setFont(font);
        if ((POINTS) > 0) {
            g.setColor(Color.BLUE);
            g.drawString(POINTS + " points", POSITION_X, POSITION_Y);
        } else {
            g.setColor(Color.RED);
            g.drawString(POINTS + " points", (Main.getCanvasSizeX() / 2) - 60, 45);
        }
        g.setFont(MainCanvas.FONT);
        g.setColor(Color.BLACK);
    }

    private void action() {
        delay--;
        font = new Font("Comic sans ms", Font.BOLD, size++);
        if (delay <= 0) {

            isDessinable = false;
        }
    }
}
