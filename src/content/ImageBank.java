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
    
    public Image canon0 =Toolkit.getDefaultToolkit().getImage("");
    public final Image nuage;
    public  final Image CANON1 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image CANON2 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image ENEMI = Toolkit.getDefaultToolkit().getImage("");
    public  final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    public  final Image SUPERSONIC = Toolkit.getDefaultToolkit().getImage("");
    public  final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    public ImageBank() throws Exception  {
        
        
        /* Voici un exemple pour récupérer une image du JAR
         * 
         */
        //InputStream is = getClass().getResourceAsStream("canon0.jpg");
        
            //canon0 = ImageIO.read(is);
        
    nuage = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/nuage.png")));

    }





    
    
}
