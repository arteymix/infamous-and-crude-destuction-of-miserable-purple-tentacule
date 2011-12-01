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
package main;

import content.GameValues;
import util.Highscores;
import content.SoundBank;
import content.images.ImageBank;
import graphique.component.Canon;
import graphique.window.InterfaceGraphique;
import java.io.IOException;
import javax.swing.JOptionPane;
import util.KeyBoardListener;
import util.Serialization;
import util.SoundManager;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Main {

    /**
     * 
     */
    /**
     * 
     */
    /**
     * 
     */
    public static final int CODE_DE_SORTIE_OK = 0,
            CODE_DE_SORTIE_SERIALIZATION_FAILED = 1,
            CODE_DE_SORTIE_AUTRE = 2;
    /**
     * Constantes pour le niveau de jeu.
     */
    public static final int RESET = 0, LEVEL_1 = 1, LEVEL_2 = 2, LEVEL_3 = 3, LEVEL_BONUS = 42;
    /**
     * 
     */
    public static GameValues gameValues;
    private static Thread rendu;
    /**
     * 
     */
    public static InterfaceGraphique interfaceGraphique;
    /**
     * Objet pour la banque d'images qui contient des images pour le rendu.
     */
    public static ImageBank imageBank;
    /**
     * Objet contenant les highscores du joueur.
     */
    public static Highscores highscore;
    /**
     * Système de gestion du son pour agrémenter l'expérience de l'utilisateur
     * avec un serveur de sons.
     */
    public static SoundBank soundBank ;
    public static SoundManager son;
    
    /**
     * 
     */
    /**
     * 
     */
    public static Canon canon1, canon2;

    /**
     * Méthode principale du programme.
     * @param args est un tableau d'arguments provenant de la ligne de commande.
     * @throws IOException  
     */
    public static void main(String[] args) throws IOException {
        if ((highscore = (Highscores) Serialization.unSerialize("highscores.serial")) == null) {
            System.out.println("Un nouveau fichier de highscores sera généré.");
            highscore = new Highscores();
        }
        soundBank = new SoundBank();
        son = new SoundManager();
        gameValues = new GameValues();
        imageBank = new ImageBank();
        imageBank.setStage(1);
        canon1 = new Canon(gameValues.canon.CANON0_ID);
        canon2 = new Canon(gameValues.canon.CANON1_ID);
        gameValues.composantesDessinables.add(canon1);
        gameValues.composantesDessinables.add(canon2);
        interfaceGraphique = new InterfaceGraphique();
        interfaceGraphique.keyBoardListener = new KeyBoardListener(canon1, canon2);
        interfaceGraphique.keyBoardListener.start();
        rendu = new Thread(interfaceGraphique, "Thread pour le rendu graphique");
        rendu.start();
        //gameValues.composantesDessinables.add(new BossInkScreen());
    }

    /**
     * Une fois toutes les variables accessibles depuis gameValue, il devient
     * trivial de définir les paramètres associés à chaque niveau. Une série de
     * constantes est définis dans la classe Main sous forme de LEVEL_X. 
     * @param i
     */
    public static void setGameLevel(int i) {

        switch (i) {
            case RESET:
                /* Les valeurs du jeu sont remises à zéro en réinstanciant la
                 * variable gameValues. 
                 */
                gameValues = new GameValues();
            case LEVEL_1:
            case LEVEL_2:
            case LEVEL_3:
            case LEVEL_BONUS:
        }
    }

    /**
     * Méthode appellée lors qu'une nouvelle partie est demandée.
     */
    public static void restart() {
        Main.gameValues.isPaused = true;
        if (JOptionPane.showConfirmDialog(null, "Recommencer?", "", JOptionPane.YES_NO_OPTION) == 0) {
            gameValues = new GameValues();
            imageBank.setStage(1);
            canon1 = new Canon(gameValues.canon.CANON0_ID);
            canon2 = new Canon(gameValues.canon.CANON1_ID);
            gameValues.composantesDessinables.add(canon1);
            gameValues.composantesDessinables.add(canon2);
            interfaceGraphique.keyBoardListener = new KeyBoardListener(canon1, canon2);
            interfaceGraphique.keyBoardListener.start();
            rendu = new Thread(interfaceGraphique, "Thread pour le rendu graphique");
            rendu.start();

        }
        Main.gameValues.isPaused = false;

    }

    /**
     * Méthode appelée lorsqu'une partie se termine, la méthode close() est 
     * appelée par la suite. Cette méthode calcule si le joueur a obtenu les
     * trophées.
     */
    public static void terminerPartie() {
        gameValues.isPaused = true;
        highscore.partiesCompletes++;
        String achievements = "Achievements :\n";
        if (gameValues.points > 1000) {
            if (!highscore.LEET_OBTAINED) {
                achievements += "1337 obtenu!\n";

                highscore.LEET_OBTAINED = true;
            } else {
                // Leet déjà obtenu!
            }

        } else if (gameValues.points > 250) {
            if (!highscore.PRO_OBTAINED) {
                achievements += "Pro obtenu!\n";
                highscore.PRO_OBTAINED = true;
            } else {
                // pro deja obtenu
            }
        }
        highscore.serializeOnTheHeap();
        JOptionPane.showMessageDialog(null, "La partie est terminée! Vous avez obtenu\n" + gameValues.points + " points\n" + achievements);
        close(0);
    }

    /**
     * Lance la fermeture du jeu. Pour l'instant, cette méthode ne contient
     * qu'un System.exit(0), mais pourra éventuellement gérer une fermeture plus
     * complexe.
     * @param i est le statut de fermeture.
     */
    public static void close(int i) {
        Main.gameValues.isRunning = false;
        // On attent au moins la latence pour être sur que tous les threads sont stoppés.
        try {
            Thread.sleep((int) gameValues.latency);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // Le thread de swing est stoppé
        interfaceGraphique.dispose();
        if (i == CODE_DE_SORTIE_OK) {
            // On sérialise les highscore à la fermeture.
            highscore.serializeOnTheHeap();

            System.exit(i);
        } else {
            System.out.println("Le programme ferme avec une erreur! Statut de la fermeture : " + i);
            // Le thread de swing est stoppé            
            System.exit(i);
        }
    }
}
