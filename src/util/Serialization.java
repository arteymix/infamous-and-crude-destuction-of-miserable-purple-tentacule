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
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.Main;

/**
 * Classe contenant les méthodes de sérialisation utiles.
 * @author guillaume
 */
public final class Serialization {

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/ 
     * @param o est l'objet à sérialiser.
     * @param filename est le nom du fichier où l'objet sera sérialisé.
     * @return  
     */
    public static int serialize(Object o, String filename) {
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
                    return Main.CODE_DE_SORTIE_OK;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return Main.CODE_DE_SORTIE_SERIALIZATION_FAILED;
        }
    }

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     * L'objet Interface doit être en mesure de se reconstruire à partir d'un
     * objet de configuration.
     * @param filename est le nom du fichier à désérialiser.
     * @return l'objet désérialisé.
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
            return p;
        }
    }
}
