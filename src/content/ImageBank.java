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
package content;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Fichier d'objet pour la banque d'images.
 * @author Guillaume Poirier-Morency
 */
public class ImageBank implements Serializable {

    /**
     * 
     */
    public final Image CANON_0;
    /**
     * 
     */
    public final Image NUAGE;
    /**
     * 
     */
    public final Image MISSILE;
    /**
     * 
     */
    public final Image BACKGROUND_1;    
    /**
     * 
     */
    public final Image ENEMI;
    /**
     * 
     */
    public final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    /**
     * 
     */
    public final Image SUPERSONIC;
    /**
     * 
     */
    public final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    /**
     * 
     */
    public final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    /**
     * 
     */
    public final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    /**
     * 
     */
    public final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    /**
     * 
     */
    public final Image CANON_1;
    /**
     * 
     */
    public final Image SUBCANON1;
    /**
     * 
     */
    public final Image SUBCANON2;
    /**
     * 
     */
    public final Image PROJECTILE_PIEUVRE;

    /**
     * Constructeur pour ImageBank. 
     * @throws IOException si un fichier ne peut être lu.
     */
    public ImageBank() throws IOException {
        
        BACKGROUND_1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/background1.jpg")));
        NUAGE = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/nuage.png")));
        MISSILE = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/missile.png")));
        CANON_0 = Toolkit.getDefaultToolkit().getImage("C:/Users/nafir/Desktop/space invaders/submarine1.gif");
        CANON_1 = Toolkit.getDefaultToolkit().getImage("C:/Users/nafir/Desktop/space invaders/submarine1.gif");
        SUBCANON1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/subcanon1.gif")));       
        SUBCANON2 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/subcanon2.gif"))); 
        ENEMI = Toolkit.getDefaultToolkit().getImage("C:/Users/nafir/Desktop/space invaders/pieuvre.gif");
        PROJECTILE_PIEUVRE =ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/meduse.gif")));
        SUPERSONIC = Toolkit.getDefaultToolkit().getImage("C:/Users/nafir/Desktop/space invaders/meduse.gif");
        
    }
}
