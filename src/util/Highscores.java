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

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe pour l'objet de highscores. Cet objet hérite d'un dictionnaire 
 * String+Integer afin de stocker les scores des joueurs. Il est sérializable et
 * se sérialize automatiquement lorsqu'un score y est rajouté. Le dictionnaire
 * est trié lorsqu'on lui demande son contenu avec la méthode getScores() qui
 * retourne un ArrayList de String. Il est trié en fonction des points.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Highscores extends HashMap<String, Integer> implements Serializable {

    /**
     * Constructeur pour l'objet de Highscores. Met mon nom, avec 999999999 points
     * à battre.
     */
    public Highscores() {
        super();
        put("Guillaume", 999999999);
    }

    /**
     * Sérialize sur le champ!
     */
    public void serializeOnTheHeap() {
        Serialization.serialize(this, "highscores.serial");
    }
    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    public boolean NOOB_OBTAINED,
            OWN_OBTAINED,
            PWN_OBTAINED,
            NUKE_OBTAINED,
            PRO_OBTAINED,
            LEET_OBTAINED,
            BAZINGA_OBTAINED;
    /**
     *
     */
    public int partiesCompletes = 0;

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

    /**
     * Retourne tous les scores sous forme d'un tableau de String.
     * @return un tableau de String contenant les scores des joueurs.
     */
    public String[] getScores() {
        // TODO Finir l'implémentation des highscores ici!  

        String[] highscores = new String[this.size()];
        int i = 0;
        for (String s : this.keySet()) {
            highscores[i] = s + " | " + this.get(s);
            i++;            
        }
        // Tri du dictionnaire
        for (int j = 0; j < highscores.length; j++) {
            for (int k = j; k < highscores.length; k++) {
                if (Integer.parseInt(highscores[k].split(" ")[2]) > Integer.parseInt(highscores[j].split(" ")[2])) {
                    String temp = highscores[k];
                    highscores[k] = highscores[j];
                    highscores[j] = temp;
                }
            }
        }
        if(highscores.length > 5) {
            String[] s = new String[5];
            for(int z = 0; z < 5; z++) {
                s[z] = highscores[z];
            }
            highscores = s;
        }
        return highscores;
    }
}
