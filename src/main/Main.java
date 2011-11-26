package main;

import content.GameValues;
import content.Highscores;
import content.ImageBank;
import graphique.InterfaceGraphique;
import java.io.IOException;
import java.io.Serializable;
import util.Serialization;
import util.SoundManager;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Main implements Serializable {

    public static GameValues gameValues = new GameValues();
    private static Thread rendu;
    private static InterfaceGraphique interfaceGraphique;
    /**
     * Objet pour la banque d'images qui contient des images pour le rendu.
     */
    public static ImageBank imageBank;
    /**
     * 
     */
    public static Highscores highscore = new Highscores();
    /**
     * Système de gestion du son pour agrémenter l'expérience de l'utilisateur
     * avec un serveur de sons.
     */
    public static SoundManager son = new SoundManager();

    /**
     * Lance la fermeture du jeu. Pour l'instant, cette méthode ne contient
     * qu'un System.exit(0), mais pourra éventuellement gérer une fermeture plus
     * complexe.
     * @param i est le statut de fermeture.
     */
    public static void close(int i) {
        if (i == CODE_DE_SORTIE_OK) {
            Main.gameValues.isRunning = false;
            /* On récupère 
             * 
             */            
            i = Serialization.serialize(gameValues, "save.serial");
            if (i == CODE_DE_SORTIE_OK) {
                System.out.println("Save was successful!");
            } else {
                System.out.println("Save failed! Code de sortie : " + i);
            }
            // Le thread de swing est stoppé
            interfaceGraphique.dispose();
            System.exit(i);
        } else {
            System.out.println("Le programme ferme avec une erreur! Statut de la fermeture : " + i);
             // Le thread de swing est stoppé
            interfaceGraphique.dispose();
            System.exit(i);
        }
    }
    
    public static final int CODE_DE_SORTIE_OK = 0,
            CODE_DE_SORTIE_SERIALIZATION_FAILED = 1,
            CODE_DE_SORTIE_AUTRE = 2;

    /**
     * 
     * @param args
     * @throws Exception  
     */
    public static void main(String[] args) throws Exception {
        if ((gameValues = (GameValues) Serialization.unSerialize("save.serial")) == null) {
            gameValues = new GameValues();
            System.out.println("Un nouveau gameValues sera généré");

        } else {
            System.out.println("Une ancien gameValues sera utilisé");
        }

        try {
            imageBank = new ImageBank();
        } catch (IOException ex) {
            System.out.println("La banque d'images n'a pas pu être instancié!"
                    + "Le programme va s'interrompre!");
            close(1);
        }
        interfaceGraphique = new InterfaceGraphique();
        rendu = new Thread(interfaceGraphique, "Thread pour le rendu graphique");
        rendu.start();
    }
}
