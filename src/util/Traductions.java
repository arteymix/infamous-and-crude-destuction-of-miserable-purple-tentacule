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
            initFr();
            isInitAlready = true;
        }
        if (langue.equals("fr")) {
            return fr.get(variable);
        }
        else if (langue.equals("en")) {
            return en.get(variable);
        } else {
        System.out.println("Langue non supportée!");
        return null;
        }
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
    private static void initFr() {
        fr.put("title", "La cruelle et infâme destruction du misérable tentacule mauve (suite et fin... si il y en a une)");
        
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
        
        fr.put("menu.item.aide", "Aide...");
        fr.put("menu.item.tableau", "Tableau de pointage...");
        fr.put("menu.item.trophe", "Trophées...");
        fr.put("menu.item.apropos", "À propos...");      

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
        fr.put("debug.tempsdurendu", "Temps du rendu");
        fr.put("debug.tempsdurendu", "Temps du rendu");
        fr.put("debug.tempsdurendu", "Temps du rendu");
    }
    
    private static void initEn() {
    
    
    }
    private static HashMap<String, String> en = new HashMap<String, String>();
    private static HashMap<String, String> fr = new HashMap<String, String>();
}
