/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.io.Serializable;
import java.util.ArrayList;
import util.Dessinable;
import util.SoundManager;
import util.Vecteur;

/**
 * Contient tous les param
 * @author guillaume
 */
public class GameValues implements Serializable {
     /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public  boolean isRunning = true;
    /**
     * Système de gestion du son pour agrémenter l'expérience de l'utilisateur
     * avec un serveur de sons.
     */
    public  SoundManager son = new SoundManager();
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public  double latency = 10;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public  long tempsDuRendu = 0;
    
    /**
     * 
     */
    public  boolean isPaused = false;
    /**
     * Est true quand le rendu est fini, false quand le rendu est en cours.
     */
    public  boolean paintDone = false;
    /**
     * Variable qui contient le temps de jeu.
     */
    public  long time;
    /**
     * Timer qui donne le temps depuis le début du jeu.
     */
    public  long timerSeconds = 0;
 /**
     * ArrayList des composantes dessinables.
     */
    public boolean isDebugEnabled = true;
    public ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    /**
     * La variable points contient les points du/des joueur/s.
     */
    public int points = 0;
    /**
     * Cette variable définit si les highscores doivent être affiché.
     */
    public int level = 0;
    public Nuage nuage = new Nuage();
    public Canon canon = new Canon();
    public Projectile projectile = new Projectile();
    public Ovni ovni = new Ovni();
     /**
     * Ce vecteur est le vecteur dimension du canvas ou les composants et
     * graphics sont dessinés.
     */
    public  Vecteur CANVAS_SIZE = new Vecteur(800, 800);

    public class Nuage {

        public int PROBABILITE_APPARITION_NUAGE = 1000;
    }

    public class Canon {

        public final int VIE_INIT_CANON = 1000;
        public final double MOVEMENT_INCREMENT_CANON = 3.0;
        public final double ANGLE_INCREMENT_CANON = Math.PI / 200.0;
        public final int LATENCE_DU_TIR = 250;
        public double heigh = 100, width = 255;
    }

    public class Projectile {

        public double GRAVITY = 0.8;
    }

    public class Ovni {

        public int PROBABILITE_APPARITION_OVNI = 100;
        public boolean isBoss = false;
        /**
         * 
         */
        public final int ENNEMI_NORMAL = 1;
        /**
         * 
         */
        public final int ENNEMI_SUPERSONIQUE = 2;
        /**
         * 
         */
        public final int ENNEMI_BOSS_1 = 3;
        /**
         * 
         */
        public final int ENNEMI_BOSS_2 = 4;
        /**
         * 
         */
        public final int ENNEMI_BOSS_3 = 5;
    }
}
