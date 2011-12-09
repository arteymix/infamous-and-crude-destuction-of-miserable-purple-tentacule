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
import java.awt.Font;
import java.awt.Graphics;
import util.Dessinable;

/**
 *
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public final class PointsObtenus extends Dessinable {

    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    /**
     * 
     */
    private int size = 15;
    /**
     * 
     */
    private int delay = 30;
    /**
     * 
     */
    private final String POINTS;
    /**
     * 
     */
    private Font font = new Font("Comic sans ms", Font.BOLD, size);
    /**
     * 
     */
    /**
     * 
     */
    private final int POSITION_X, POSITION_Y;
    ////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * @param x
     * @param y
     * @param points
     */
    public PointsObtenus(int x, int y, String points) {
        this.POINTS = points + " points";
        this.POSITION_X = x;
        this.POSITION_Y = y;
    }

    @Override
    public void dessiner(Graphics g) {
        action();
        g.setFont(font);
        g.drawString(POINTS, POSITION_X, POSITION_Y);
        g.setFont(MainCanvas.FONT);

    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        action();
        g.setFont(font);
        g.drawString(POINTS, POSITION_X, POSITION_Y);
        g.setFont(MainCanvas.FONT);
    }

    private void action() {
        delay--;
        font = new Font("Comic sans ms", Font.BOLD, size++);
        if (delay <= 0) {

            isDessinable = false;
        }
    }
}
