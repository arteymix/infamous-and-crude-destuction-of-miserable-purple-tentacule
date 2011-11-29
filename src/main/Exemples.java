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
package main;

import content.SoundBank;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Collisionable;
import util.Dessinable;
import util.SoundManager;

/**
 * Cette classe Java montre des exemples d'utilisation de l'API à titre de 
 * référence pour le développement du jeu.
 * @author guillaume
 */
public class Exemples extends Dessinable implements Collisionable {

    private Rectangle rectangle;

    /**
     * Constructeur d'un exemple.
     */
    public Exemples() {
        this.image0 = Main.imageBank.BOSS;
        rectangle = new Rectangle(10, 10, 10, 10);
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawImage(image0, 1, 1, 1, 1, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawLine(1, 1, 1, 1);
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        SoundManager.play(SoundBank.MISSILE);
    }

    @Override
    public int getDommage() {
        return 5;
    }
}
