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

    public final Image canon0;
    public final Image nuage;
    public final Image missile;
     public final Image background1;
    public final Image CANON1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image CANON2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMI = Toolkit.getDefaultToolkit().getImage("");
    public final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONIC = Toolkit.getDefaultToolkit().getImage("");
    public final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    public final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    public Image canon1;

    /**
     * Constructeur pour ImageBank. 
     * @throws IOException 
     */
    public ImageBank() throws IOException {
        background1 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/background1.jpg")));
        nuage = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/nuage.png")));
        missile = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/missile.png")));
        canon0 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/images/submarine1.gif")));
    }
}
