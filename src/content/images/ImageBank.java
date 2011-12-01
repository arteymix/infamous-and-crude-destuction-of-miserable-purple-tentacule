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
import java.io.Serializable;

/**
 * Fichier d'objet pour la banque d'images.
 * @author Guillaume Poirier-Morency
 */
public class ImageBank implements Serializable {

    public Image ship1;
    public Image ship2;
    public Image subcanon1;
    public Image subcanon2;
    public Image decorFlottant;
    public Image background;
    public Image ennemi;
    public Image ennemiSupersonic;
    public Image ennemiOr;
    public Image ennemiSupersonicOr;
    public Image boss;
    public Image projectile;
    public Image projectileEnnemi;
    public Image projectileEnnemiOr;
    public Image projectileEnnemiSupersonic;
    public Image projectileEnnemiSupersonicOr;
    public Image projectileBoss;
    public Image explosion;
    public Image powerup;
    
    public final Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/icon.png"));

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
        projectileEnnemiSupersonic = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiSupersonic.gif"));
        projectileEnnemiSupersonicOr = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiSupersonicOr.gif"));
        projectileBoss = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileBoss.gif"));
        explosion = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/explosion.gif"));
        powerup = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/powerup.gif"));
    }
}
