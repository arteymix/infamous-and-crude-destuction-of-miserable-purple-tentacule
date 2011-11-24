/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.io.Serializable;
import java.util.HashMap;

import util.Serialization;

/**
 *
 * @author guillaume
 */
public class Highscores extends HashMap<String, Integer> implements Serializable {

    public Highscores() {
        Highscores h;
        if ((h = (Highscores) Serialization.unSerialize("highscores.serial")) != null) {

            super.putAll(h);


        }
    }

    @Override
    public Integer put(String s, Integer i) {

        Integer jkl = super.put(s, i);

        // On sérialize quand le dictionnaire est altéré.
        Serialization.serialize(this, "highscores.serial");
        return jkl;

    }
}
