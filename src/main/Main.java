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
        if (i == 0) {
            Main.gameValues.isRunning = false;
            Serialization.serialize(gameValues, "save.serial");
            System.out.println("saved");
            interfaceGraphique.dispose();
            System.exit(i);
        } else {
            System.out.println("Le programme ferme avec une erreur! Statut de la fermeture : " + i);
            interfaceGraphique.dispose();
            System.exit(i);
        }
    }

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
