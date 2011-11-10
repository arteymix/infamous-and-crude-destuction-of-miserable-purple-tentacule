/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usager
 */
public class ImageBank {
    
    public final Image canon0;
  public  final Image CANON1 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image CANON2 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image ENEMI = Toolkit.getDefaultToolkit().getImage("");
    public  final Image ENEMIOR = Toolkit.getDefaultToolkit().getImage("");
    public  final Image SUPERSONIC = Toolkit.getDefaultToolkit().getImage("");
    public  final Image SUPERSONICOR = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS1 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS2 = Toolkit.getDefaultToolkit().getImage("");
    public  final Image BOSS3 = Toolkit.getDefaultToolkit().getImage("");
    public ImageBank() throws IOException {
    canon0 = ImageIO.read((ClassLoader.getSystemResourceAsStream("content/canon0.jpg")));

    }





    
    
}
