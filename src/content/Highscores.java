package content;

import java.io.Serializable;
import java.util.HashMap;

import util.Serialization;

/**
 * Classe pour l'objet de highscores. Cet objet hérite d'un dictionnaire 
 * String+Integer afin de stocker les scores des joueurs. Il est sérializable et
 * se sérialize automatiquement lorsqu'un score y est rajouté.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Highscores extends HashMap<String, Integer> implements Serializable {

    /**
     * Constructeur pour l'objet de highscores, il se désérialise lui-même si
     * il est disponible, autrement il en crée un nouveau.
     */
    public Highscores() {
        Highscores h;
        if ((h = (Highscores) Serialization.unSerialize("highscores.serial")) != null) {
            super.putAll(h);
        }
    }

    /**
     * Version altéré du put conventionel d'un dictionnaire afin de rajouter un
     * algorithme de tri et suivi d'une sérialization.
     * @param s est la chaîne de caractères correspondant au nom du joueur.
     * @param i est le pointage du joueur.
     * @return voir le retour d'un objet HashMap.
     */
    @Override
    public Integer put(String s, Integer i) {
        Integer jkl = super.put(s, i);
        /* TODO On trie le dictionnaire en fonction des scores.
         * On sérialize quand le dictionnaire est altéré.
         */
        Serialization.serialize(this, "highscores.serial");
        return jkl;
    }
}
