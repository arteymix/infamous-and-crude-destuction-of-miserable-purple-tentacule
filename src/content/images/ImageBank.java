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

    public Image SHIP_1;
    public Image SHIP_2;
    public Image SUBCANON_1;
    public Image SUBCANON_2;
    public Image NUAGE;
    public Image BACKGROUND;
    public Image ENNEMI;
    public Image SUPERSONIC;
    public Image ENNEMI_OR;
    public Image SUPERSONIC_OR;
    public Image BOSS;
    public Image PROJECTILE;
      public Image PROJECTILE_ENNEMI;
    public Image PROJECTILE_ENNEMI_OR;
    public Image PROJECTILE_SUPERSONIC;
    public Image PROJECTILE_SUPERSONIC_OR;
    public Image PROJECTILE_BOSS;
    public Image EXPLOSION;

    
    
    public void setStage(int i) {


        SHIP_1 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ship1.gif"));
        SHIP_2 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ship2.gif"));
        SUBCANON_1 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/subcanon1.gif"));
        SUBCANON_2 = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/subcanon2.gif"));
        NUAGE = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/objetFlottant.png"));
        BACKGROUND = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/background.jpg"));
        ENNEMI = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemi.gif"));
        SUPERSONIC = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemiSupersonic.gif"));
        ENNEMI_OR = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemi.gif"));
        SUPERSONIC_OR = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/ennemiSupersonicOr.gif"));
        BOSS = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/boss.gif"));
       PROJECTILE = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectile.gif"));
       PROJECTILE_ENNEMI = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemi.gif"));
       PROJECTILE_ENNEMI_OR = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiOr.gif"));
        PROJECTILE_SUPERSONIC = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiSupersonic.gif"));
        PROJECTILE_SUPERSONIC_OR = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileEnnemiSupersonicOr.gif"));
        PROJECTILE_BOSS = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/projectileBoss.gif"));
EXPLOSION= Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("content/images/stage" + i + "/explosion.gif"));
    }

    
}
