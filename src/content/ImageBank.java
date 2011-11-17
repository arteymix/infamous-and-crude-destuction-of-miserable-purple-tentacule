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

    public final Image CANON_0;
    public final Image NUAGE;
    public final Image MISSILE;
    public final Image BACKGROUND_1;
    public final Image CANON1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image CANON2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMI = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONIC = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    public final Image CANON_1;
    public final Image SUBCANON1;

    /**
     * Constructeur pour ImageBank. 
     * @throws IOException si un fichier ne peut être lu.
     */
    public ImageBank() throws IOException {
        BACKGROUND_1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/background1.jpg")));
        NUAGE = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/nuage.png")));
        MISSILE = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/missile.png")));
        CANON_0 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/submarine1.gif")));
        CANON_1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/canon0.jpg")));
        SUBCANON1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/subcanon1.gif")));
    }
}
