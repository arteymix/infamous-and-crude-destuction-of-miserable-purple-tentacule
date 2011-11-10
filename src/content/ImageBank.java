/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usager
 */
public class ImageBank {
    
    public final Image canon0;

    public ImageBank() throws IOException {
    canon0 = ImageIO.read((ClassLoader.getSystemResourceAsStream("canon0.jpg")));

    }





    
    
}
