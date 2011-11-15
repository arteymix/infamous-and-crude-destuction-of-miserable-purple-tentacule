package main;

import content.ImageBank;
import content.SoundBank;
import graphique.InterfaceGraphique;
import java.io.Serializable;
import util.SoundManager;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Main implements Serializable {

    public static ImageBank imageBank;
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public static double latency = 10;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public static long tempsDuRendu = 0;
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public static boolean isRunning = true;
    /**
     * 
     */
    public static boolean isPaused = false;
    private static InterfaceGraphique tp2;
    /**
     * @param args the command line arguments
     */
    public static Thread rendu;
    public static SoundManager son = new SoundManager();
    public static long startedTime;
    public static boolean paintDone = false;

    public static void main(String[] args) {

        SoundManager.play(SoundBank.MISSILE);
        try {
            imageBank = new ImageBank();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tp2 = new InterfaceGraphique();

        // Thread pour le rendu
        /*TODO Créer un objet pour le thread de rendu graphique!
         * 
         */

        rendu = new Thread("Thread pour le rendu graphique") {

            @Override
            public void run() {

                while (isRunning) {

                    startedTime = System.currentTimeMillis();
                    paintDone = false;
                    // On peint l'interface, ce qui oblige les composantes à calculer leurs animations.
                    tp2.mainCanvas.repaint();
                    try {
                        while (!paintDone) {

                            Thread.sleep(0, 1);
                        }
                        tempsDuRendu = (System.currentTimeMillis() - Main.startedTime);
                        /* currentTime vaut le temps en millisecondes prit pour faire un rendu.
                         * En quelque sorte, si le rendu est trop long, on attendra moins 
                         * longtemps avant le suivant afin de ne pas causer d'accélération 
                         * subites en cours de jeu. Si le temps de redraw est plus grand que 10 ms,
                         * soit 100 fps, on passe directement au prochain frame.
                         */


                        if (tempsDuRendu > latency) {
                            Thread.sleep(0);
                        } else {
                            Thread.sleep((int) (latency - tempsDuRendu));
                        }
                        while (isPaused) {
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();

                    }
                }
            }
        };
        rendu.start();

    }
}
