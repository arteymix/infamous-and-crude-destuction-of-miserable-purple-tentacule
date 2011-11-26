package content;

import java.io.Serializable;
import java.util.HashMap;

import javax.swing.JOptionPane;
import util.Serialization;

/**
 * Classe pour l'objet de highscores. Cet objet hérite d'un dictionnaire 
 * String+Integer afin de stocker les scores des joueurs. Il est sérializable et
 * se sérialize automatiquement lorsqu'un score y est rajouté.
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
     * @return 
     */
    public String[] getScores() {
        // TODO Finir l'implémentation ici!
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
