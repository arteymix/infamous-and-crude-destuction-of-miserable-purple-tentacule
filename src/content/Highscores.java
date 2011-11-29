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
package content;

import java.io.Serializable;
import java.util.HashMap;
import javax.swing.JOptionPane;
import util.Serialization;

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
     * Constructeur pour l'objet de highscores, il se désérialise lui-même si
     * il est disponible, autrement il en crée un nouveau.
     */
    public Highscores() {
        Highscores h;
        if ((h = (Highscores) Serialization.unSerialize("highscores.serial")) != null) {
            super.putAll(h);
            System.out.println("Un fichier de highscores a pu être récupéré et sera utilisé.");
        } else {
            System.out.println("Aucun fichier de highscores n'a été trouvé, un nouveau fichier sera généré!");
        }
    }
    
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
        if (this.containsKey(s)) {
            JOptionPane.showMessageDialog(null, "Voulez-vousCe joueur existe déjà, s'il s'agit de vous, voulez-vous remplacer votre score?"
                    + "\nAutrement, il ne sera pas sauvegardé!");
            return 0; // TODO Configurer les return ici...
        }
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
        }
        return highscores;
    }

    /**
     * Sérialize en cas de fermeture soudaine afin de protéger les scores.
     */
    public void close() {
        Serialization.serialize(this, "highscores.serial");
        System.out.println("Les highscores sont sauvegardés");
    }
}
