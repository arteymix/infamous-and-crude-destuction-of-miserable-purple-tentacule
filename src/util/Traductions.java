package util;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe pour le système de traductions.
 * @author Guillaume Poirier-Morency
 */
public class Traductions implements Serializable {

    private static String langue = "fr";
    private static boolean isInitAlready = false;

    /**
     * 
     * @param variable 
     * @return la traduction correspondant à la variable.
     */
    public static String get(String variable) {
        if (!isInitAlready) {
            init();
            isInitAlready = true;
        }
        if (langue.equals("fr")) {
            return fr.get(variable);
        }
        else if (langue.equals("en")) {
            return en.get(variable);
        }
        System.out.println("Langue non supportée!");
        return null;
    }

    /**
     * Change la langue du dictionnaire. La valeur retournée par get(String variable)
     * est définit en fonction de la langue.
     * @param s est le String représentant la langue. Il peut prendre les 
     * valeurs "en" et "fr".
     */
    public static void setLangue(String s) {
        langue = s;
    }

    /**
     * Initialise le dictionnaire de traductions.
     */
    public static void init() {
        fr.put("menu.fichier", "Fichier");
        fr.put("menu.editer", "Éditer");
        fr.put("menu.langue", "Langues");
        fr.put("menu.aide", "?");
        fr.put("menu.quitter", "Quitter");
        fr.put("menu.modedebogage", "Mode de débogage");
        fr.put("menu.deuxcanons", "Deux canons");
        fr.put("menu.francais", "Français");
        fr.put("menu.anglais", "Anglais");
        fr.put("menu.nouvelle", "Nouvelle Partie");

        fr.put("debug.latence", "Latence");
        fr.put("debug.tempsrendu", "Temps de rendu");
        fr.put("debug.modedebogage", "Mode de débogage");
        fr.put("debug.nombrecomposantesdessinables", "Nombre de composantes dessinables");
        fr.put("debug.composantes", "composantes");
        fr.put("debug.active", "Activé");
        fr.put("debug.desactive", "Désactivé");
        
        fr.put("debug.tempsdurendu", "Temps du rendu");
        fr.put("debug.tempsdurendu", "Temps du rendu");
        fr.put("debug.tempsdurendu", "Temps du rendu");
    }
    private static HashMap<String, String> en = new HashMap<String, String>();
    private static HashMap<String, String> fr = new HashMap<String, String>();
}
