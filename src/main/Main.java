package main;

import content.GameValues;
import content.Highscores;
import content.ImageBank;
import graphique.InterfaceGraphique;
import java.io.IOException;
import java.io.Serializable;
import util.Serialization;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Main implements Serializable {

    public static GameValues gameValues = new GameValues();
    private static Thread rendu;
    private static InterfaceGraphique ig;
    /**
     * Objet pour la banque d'images qui contient des images pour le rendu.
     */
    public  static ImageBank imageBank;
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
        Main.gameValues.isRunning = false;
        Serialization.serialize(gameValues, "save.serial");
        System.exit(0);
    }

    /**
     * 
     * @param args
     * @throws Exception  
     */
    public static void main(String[] args) throws Exception {
        if((gameValues = (GameValues) Serialization.unSerialize("save.serial"))== null) {
        gameValues = new GameValues();
        
        }
        
        try {          
            imageBank = new ImageBank();
        } catch (IOException ex) {
            System.out.println("La banque d'images n'a pas pu être instancié!"
                    +"Le programme va s'interrompre!");
            close();
        }
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
