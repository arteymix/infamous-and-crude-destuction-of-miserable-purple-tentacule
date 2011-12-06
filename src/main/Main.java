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

import util.Highscores;

import content.images.ImageBank;

import graphique.component.Canon;
import graphique.component.Ovni;
import graphique.window.InterfaceGraphique;
import graphique.window.MainCanvas.Activity;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import util.Dessinable;
import util.KeyBoardListener;
import util.Serialization;
import util.Traductions;

/**
 * Classe principale du programme.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Main {
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Si true, les highscores sont affichés.
     */
    public static boolean showHighscores = false;
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public static boolean isRunning = true;
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public static double latency = 15;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public static long tempsDuRendu = 0;
    /**
     * Si true, le jeu est en pause, tous les threads sont en attente.
     */
    public static boolean isPaused = false;
    /**
     * Est true quand le rendu est fini, false quand le rendu est en cours.
     */
    public static boolean paintDone = false;
    /**
     * Timer qui donne le temps depuis le début du jeu.
     */
    public static long timerSeconds = 0;
    /**
     * ArrayList des composantes dessinables.
     */
    public static boolean isDebugEnabled = false;
    public static boolean isGameOver = false;
    /**
     * ArrayList contenant les objets dessinables.
     */
    public static ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    /**
     * La variable points contient les points du/des joueur/s.
     */
    public static int points = 0;
    /**
     * Nombre de tentacules butés pour les trophées.
     */
    public static int tentaculesKilled = 0;
    /**
     * Cette variable définit le niveau du jeu.
     */
    public static int level = 1;

    /**
     * Ce vecteur est le vecteur dimension du canvas ou les composants et
     * graphics sont dessinés.
     * @return 
     */
    //public static Vecteur canvasSize = new Vecteur(1024, 768);
    public static int getCanvasSizeX() {
        if (interfaceGraphique == null) {
            return 1024;
        } else {
            return interfaceGraphique.mainCanvas.getWidth();
        }
    }

    /**
     * 
     * @return
     */
    public static int getCanvasSizeY() {
        if (interfaceGraphique == null) {
            return 768;
        } else {
            return interfaceGraphique.mainCanvas.getHeight();
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Quand tout est okay!
     */
    /**
     * Lorsqu'une erreur de sérialization survient.
     */
    /**
     * Pour les autres erreurs...
     */
    public static final int CODE_DE_SORTIE_OK = 0,
            CODE_DE_SORTIE_SERIALIZATION_FAILED = 1,
            CODE_DE_SORTIE_AUTRE = 2;
    /**
     * Constantes pour le niveau de jeu.
     */
    public static final int LEVEL_1 = 1, LEVEL_2 = 2, LEVEL_3 = 3, LEVEL_BONUS = 4;
    /**
     * Thread pour le rendu.
     */
    private static Thread rendu;
    /**
     * Instance de l'interface graphique.
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
     * Variable pour stocker le canon1
     */
    /**
     * Variable pour stocker le canon1
     */
    public static Canon canon1, canon2;
    /**
     * Variable qui définit combien de projectiles aliens fixes sont au sol, après
     * quatre, le jeu s'arrête.
     */
    public static int alienAuSol = 0;

    /**
     * Méthode principale du programme.
     * @param args est un tableau d'arguments provenant de la ligne de commande.     
     */
    public static void main(String[] args) {
        long loadingTime;
        long totalTime = 0;
        System.out.println("Chargement du jeu...");
        ////////////////////////////////////////////////////////////////////////
        loadingTime = System.currentTimeMillis();
        if ((highscore = (Highscores) Serialization.unSerialize("highscores.serial")) == null) {
            System.out.println("Un nouveau fichier de highscores sera généré.");
            highscore = new Highscores();
        }
        totalTime += System.currentTimeMillis() - loadingTime;
        System.out.println("Highscores chargés! (" + ((System.currentTimeMillis() - loadingTime)) + " ms)");
        ////////////////////////////////////////////////////////////////////////        
        loadingTime = System.currentTimeMillis();
        imageBank = new ImageBank();
        setGameLevel(Main.LEVEL_1);
        System.out.println("Images chargées! (" + ((System.currentTimeMillis() - loadingTime)) + " ms)");
        totalTime += System.currentTimeMillis() - loadingTime;
        ////////////////////////////////////////////////////////////////////////
        loadingTime = System.currentTimeMillis();
        canon1 = new Canon(Canon.CANON1_ID);
        canon2 = new Canon(Canon.CANON2_ID);
        composantesDessinables.add(canon1);
        composantesDessinables.add(canon2);
        System.out.println("Canons chargés! (" + ((System.currentTimeMillis() - loadingTime)) + " ms)");
        totalTime += System.currentTimeMillis() - loadingTime;
        ////////////////////////////////////////////////////////////////////////
        loadingTime = System.currentTimeMillis();
        interfaceGraphique = new InterfaceGraphique();
        interfaceGraphique.keyBoardListener = new KeyBoardListener(canon1, canon2);
        interfaceGraphique.keyBoardListener.start();
        rendu = new Thread(interfaceGraphique, "Thread pour le rendu graphique");
        System.out.println("Interface graphique chargé! (" + ((System.currentTimeMillis() - loadingTime)) + " ms)");
        totalTime += System.currentTimeMillis() - loadingTime;
        ////////////////////////////////////////////////////////////////////////
        System.out.println("Chargement complété en " + totalTime + " ms!");
        rendu.start();
        System.out.println("Le jeu est commencé!");
    }

    /**
     * Une fois toutes les variables accessibles depuis gameValue, il devient
     * trivial de définir les paramètres associés à chaque niveau. Une série de
     * constantes est définis dans la classe Main sous forme de LEVEL_X. 
     * Les boss apparaissent après un temps défini à chaque niveau. On passe
     * au niveau suivant si il meurt.
     * @param i est le niveau à configurer.
     */
    public static void setGameLevel(int i) {
        System.out.println("Level " + i + " activé");
        level = i;
        switch (i) {

            case LEVEL_1:
                imageBank.setStage(1);
                break;
            case LEVEL_2:
                imageBank.setStage(2);
                break;
            case LEVEL_3:
                imageBank.setStage(3);
                break;
            case LEVEL_BONUS:
                Ovni.PROBABILITE_APPARITION_OVNI = 100;

                break;
        }
    }

    /**
     * Méthode appellée lors qu'une nouvelle partie est demandée.
     */
    public static void restart() {
        isPaused = true;
        if (JOptionPane.showConfirmDialog(null, "Recommencer?", "", JOptionPane.YES_NO_OPTION) == 0) {
            long totalLoading = 0l;
            ////////////////////////////////////////////////////////////////////
            long timeLoading = System.currentTimeMillis();
            composantesDessinables = new ArrayList<Dessinable>();
            canon1 = new Canon(Canon.CANON1_ID);
            canon2 = new Canon(Canon.CANON2_ID);
            composantesDessinables.add(canon1);
            composantesDessinables.add(canon2);
            System.out.println("Réinitialisation des objets dessinables (" + (System.currentTimeMillis() - timeLoading) + " ms)");
            ////////////////////////////////////////////////////////////////////
            timeLoading = System.currentTimeMillis();
            imageBank.setStage(1);
            System.out.println("Réinitialisation des objets dessinables (" + (System.currentTimeMillis() - timeLoading) + " ms)");
            totalLoading += System.currentTimeMillis() - timeLoading;
            ////////////////////////////////////////////////////////////////////
            timeLoading = System.currentTimeMillis();
            interfaceGraphique.keyBoardListener = new KeyBoardListener(canon1, canon2);
            interfaceGraphique.keyBoardListener.start();
            System.out.println("Redémarrage du thread pour le multitouch (" + (System.currentTimeMillis() - timeLoading) + " ms)");
            totalLoading += System.currentTimeMillis() - timeLoading;
            ////////////////////////////////////////////////////////////////////
            timeLoading = System.currentTimeMillis();
            rendu = new Thread(interfaceGraphique, "Thread pour le rendu graphique");
            rendu.start();
            interfaceGraphique.mainCanvas.activity = Activity.JEU;
            System.out.println("Redémarrage de l'interface (" + (System.currentTimeMillis() - timeLoading) + " ms)");
            totalLoading += System.currentTimeMillis() - timeLoading;
            ////////////////////////////////////////////////////////////////////
            alienAuSol = 0;
            timerSeconds = 0;
            points = 0;
            Ovni.isBoss = false;
            System.out.println("Temps de redémarrage " + totalLoading + " ms");
        }
        isPaused = false;
    }

    /**
     * Méthode appelée lorsqu'une partie se termine, la méthode close() est 
     * appelée par la suite. Cette méthode calcule si le joueur a obtenu les
     * trophées.
     * @param s 
     */
    public static void terminerPartie(String s) {
        highscore.partiesCompletes++;
        Main.interfaceGraphique.mainCanvas.activity = Activity.GAME_OVER;
        isGameOver = true;
        messageDeFermeture = s;
    }
    /**
     * 
     */
    public static String messageDeFermeture;

    /**
     * Lance la fermeture du jeu. Pour l'instant, cette méthode ne contient
     * qu'un System.exit(0), mais pourra éventuellement gérer une fermeture plus
     * complexe.
     * @param i est le statut de fermeture.
     */
    public static void close(int i) {
        isPaused = true;

        if (tentaculesKilled >= 100) {
            highscore.NUKE_OBTAINED = !highscore.NUKE_OBTAINED;
        }
        if (highscore.partiesCompletes == 0) {
            highscore.NOOB_OBTAINED = !highscore.NOOB_OBTAINED;
        }
        if (highscore.partiesCompletes >= 1000) {
            highscore.PWN_OBTAINED = !highscore.PWN_OBTAINED;
        }
        if (highscore.partiesCompletes >= 10) {
            highscore.OWN_OBTAINED = !highscore.OWN_OBTAINED;
        }
        if (points == 0) {
            highscore.BAZINGA_OBTAINED = !highscore.BAZINGA_OBTAINED;
        }
        if (points >= 1000) {
            highscore.LEET_OBTAINED = !highscore.LEET_OBTAINED;

        }
        if (points >= 250) {
            highscore.PRO_OBTAINED = !highscore.PRO_OBTAINED;
        }
        if (i == 0) {
            if (JOptionPane.showConfirmDialog(null, Traductions.get("menu.confirmation"), "", JOptionPane.YES_NO_OPTION) == 0) {
                String s;
                boolean fail = false;
                do {
                    s = JOptionPane.showInputDialog((fail ? "Ce nom ne peut être utilisé, choisissez-en un autre!\n" : "") + "Veuillez entrer votre nom, celui-ci doit\nêtre composé de trois lettres :");
                    if (s == null) {
                        break;
                    }
                    fail = true;
                } while ("".equals(s) | highscore.containsKey(s) | s.length() != 3);

                if (s != null) {
                    highscore.put(s, Main.points);
                }
            } else {
                isPaused = false;
                return;
            }
            // On sérialize les highscores une ultime fois!
            System.out.println("Sérialization finale des highscores");
            highscore.serializeOnTheHeap();
        }
        isRunning = false;
        // On attent au moins la latence pour être sur que tous les threads sont stoppés.
        try {
            Thread.sleep((int) latency);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // Le thread de swing est stoppé
        interfaceGraphique.dispose();
        System.out.println("Le programme ferme avec une erreur! Statut de la fermeture : " + i);
        // Le thread de swing est stoppé            
        System.exit(i);

    }
}
