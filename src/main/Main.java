package main;

import content.GameValues;
import content.Highscores;
import graphique.InterfaceGraphique;
import java.io.Serializable;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Main implements Serializable {
public static GameValues gameValues = new GameValues();
    private static Thread rendu;
    private static InterfaceGraphique ig;
    /**
     * 
     */
    public static Highscores highscore = new Highscores();
    /**
     * Lance la fermeture du jeu. Pour l'instant, cette méthode ne contient
     * qu'un System.exit(0), mais pourra éventuellement gérer une fermeture plus
     * complexe.
     */
    public static void close() {
        InterfaceGraphique.isRunning = false;
        
        
        
        System.exit(0);
    }

    /**
     * 
     * @param args
     * @throws Exception  
     */
    public static void main(String[] args) throws Exception {
        // Thread pour le rendu
        /*TODO Créer un objet pour le thread de rendu graphique!
         * 
         */
        //if ((ig = unSerialize()) == null) {
            ig = new InterfaceGraphique();
        //}

        rendu = new Thread(ig, "Thread pour le rendu graphique");
        rendu.start();
    }

   
}
