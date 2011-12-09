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
package content.images;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Fichier d'objet pour la banque d'images.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class ImageBank {

    /**
     * TODO Javadoc ici
     */
    public Image ship1;
    /**
     * TODO Javadoc ici
     */
    public Image ship2;
    /**
     * TODO Javadoc ici
     */
    public Image subcanon1;
    /**
     * TODO Javadoc ici
     */
    public Image subcanon2;
    /**
     * TODO Javadoc ici
     */
    public Image decorFlottant;
    /**
     * TODO Javadoc ici
     */
    public Image background;
    /**
     * TODO Javadoc ici
     */
    public Image ennemi;
    /**
     * TODO Javadoc ici
     */
    public Image ennemiSupersonic;
    /**
     * TODO Javadoc ici
     */
    public Image ennemiOr;
    /**
     * TODO Javadoc ici
     */
    public Image ennemiSupersonicOr;
    /**
     * TODO Javadoc ici
     */
    public Image boss;
    /**
     * TODO Javadoc ici
     */
    public Image projectile;
    /**
     * TODO Javadoc ici
     */
    public Image projectileEnnemi;
    /**
     * TODO Javadoc ici
     */
    public Image projectileEnnemiOr;
    /**
     * TODO Javadoc ici
     */
    public Image projectileEnnemiSupersonic;
    /**
     * TODO Javadoc ici
     */
    public Image projectileEnnemiSupersonicOr;
    /**
     * TODO Javadoc ici
     */
    public Image projectileBoss;
    /**
     * TODO Javadoc ici
     */
    public Image explosion;
    /**
     * TODO Javadoc ici
     */
    public Image powerup;
    /**
     * Icone de l'application.
     */
    public final Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/icon.png"));

    /**
     * Méthode qui change les images utilisés dans le jeu en changeant les références
     * des objets images.
     * @param i est le niveau du jeu actuel. i doit exister dans les package.
     */
    public void setStage(int i) {
        ship1 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ship1.gif"));
        ship2 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ship2.gif"));
        subcanon1 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/subcanon1.gif"));
        subcanon2 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/subcanon2.gif"));
        decorFlottant = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/objetFlottant.png"));
        background = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/background.jpg"));
        ennemi = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemi.gif"));
        ennemiSupersonic = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemiSupersonic.gif"));
        ennemiOr = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemiOr.gif"));
        ennemiSupersonicOr = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemiSupersonicOr.gif"));
        boss = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/boss.gif"));
        projectile = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectile.gif"));
        projectileEnnemi = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemi.gif"));
        projectileEnnemiOr = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiOr.gif"));
        projectileEnnemiSupersonic = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileSupersonic.gif"));
        projectileEnnemiSupersonicOr = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileSupersonicOr.gif"));
        projectileBoss = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileBoss.gif"));
        explosion = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/explosion.gif"));
        powerup = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/powerup.png"));
    }
}
