/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author guillaume
 */
public class Serialization {
     /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/ 
     */
    public static void serialize(Object o, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(o);
                oos.flush();
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     * L'objet Interface doit être en mesure de se reconstruire à partir d'un
     * objet de configuration.
     * @param filename est le nom du fichier à désérialiser.
     * @return l'objet désérialisé.
     * @throws IOException est lancée lorsque le fichier d'objet ne peut être récupéré. 
     * @throws ClassNotFoundException est lancée lorsque l'objet trouvé ne correspond pas à ce qui est recherché. 
     */
    public static Object unSerialize(String filename) {
        Object p = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                p = ois.readObject();
            } finally {
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch (IOException ioe) {
        } catch (ClassNotFoundException cnfe) {
        } finally {
            return  p;

        }

    }
}
