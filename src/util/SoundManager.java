/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import main.Main;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class SoundManager extends Thread {
public SoundManager() {
super("Thread pour le son");
}
    /**
     * Joue le AudioInputStream en entrée.
     * @param ais 
     */
    public void play(AudioInputStream ais) {
        
    }

    @Override
    public void run() {
        while (Main.isRunning) {

// Joue le contenu audio demandé

            while (Main.isPaused) {
                try {
                    // Joue une musique d'ambiance quand le jeu est en pause
                    
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep((int)Main.latency);
            } catch (InterruptedException ex) {
                Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        
    }
}
