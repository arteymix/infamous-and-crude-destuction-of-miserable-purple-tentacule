package content;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Fichier d'objet pour la banque d'images.
 * @author Guillaume Poirier-Morency
 */
public class ImageBank implements Serializable {

    public Image canon0 = Toolkit.getDefaultToolkit().getImage("");
    public final Image nuage;
    public final Image missile;
    public final Image CANON1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image CANON2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMI = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONIC = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    public  Image canon1;

    public ImageBank() throws Exception {
        nuage = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/nuage.png")));
        missile = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/missile.png")));
        canon0 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/canon0.jpg")));
    }
}
