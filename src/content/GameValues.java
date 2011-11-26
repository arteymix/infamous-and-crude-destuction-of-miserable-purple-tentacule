package content;

import java.io.Serializable;
import java.util.ArrayList;
import util.Dessinable;
import util.Vecteur;

/**
 * Contient tous les paramètres du jeu.
 * Les paramètres propres aux objets ne doivent pas être placés ici, ils seront
 * sérialisé avec l'ArrayList. Il sert aussi à regrouper les variables afin de
 * faciliter l'implémentation de mode de jeu.
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public final class GameValues implements Serializable {

    /**
     * 
     */
    public boolean showHighscores = false;
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public boolean isRunning = true;
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public double latency = 10;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public long tempsDuRendu = 0;
    /**
     * 
     */
    public boolean isPaused = false;
    /**
     * Est true quand le rendu est fini, false quand le rendu est en cours.
     */
    public boolean paintDone = false;
    /**
     * Variable qui contient le temps de jeu.
     */
    public long time;
    /**
     * Timer qui donne le temps depuis le début du jeu.
     */
    public long timerSeconds = 0;
    /**
     * ArrayList des composantes dessinables.
     */
    public boolean isDebugEnabled = true;
    /**
     * 
     */
    public ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    /**
     * La variable points contient les points du/des joueur/s.
     */
    public int points = 0;
    /**
     * Cette variable définit si les highscores doivent être affiché.
     */
    public int level = 0;
    /**
     * 
     */
    public NuageValues nuage = new NuageValues();
    /**
     * 
     */
    public CanonValues canon = new CanonValues();
    /**
     * 
     */
    public ProjectileValues projectile = new ProjectileValues();
    /**
     * 
     */
    public OvniValues ovni = new OvniValues();
    /**
     * 
     */
    public ProjectileEnnemiValues projectileEnnemi = new ProjectileEnnemiValues();
    /**
     * Ce vecteur est le vecteur dimension du canvas ou les composants et
     * graphics sont dessinés.
     */
    public Vecteur canvasSize = new Vecteur(800, 800);

    /**
     * 
     */
    public class NuageValues implements Serializable {

        /**
         * 
         */
        public int PROBABILITE_APPARITION_NUAGE = 1000;
    }

    /**
     * 
     */
    public class CanonValues implements Serializable {

        /**
         * 
         */
        public boolean isCanon2ValidTarget = false;
        /**
         * 
         */
        public final int VIE_INIT_CANON = 1000;
        /**
         * 
         */
        public final double MOVEMENT_INCREMENT_CANON = 3.0;
        /**
         * 
         */
        public final double ANGLE_INCREMENT_CANON = Math.PI / 200.0;
        /**
         * 
         */
        public final int LATENCE_DU_TIR = 250;
        /**
         * 
         */
        /**
         * 
         */
        public final double HAUTEUR_DU_CANON = 100, LARGEUR_DU_CANON = 255;
    }

    /**
     * 
     */
    public class ProjectileValues implements Serializable {

        /**
         * 
         */
        public double GRAVITY = 0.8;
    }

    /**
     * 
     */
    public class ProjectileEnnemiValues implements Serializable {

        /**
         *
         */
        public final int MISSILE_0 = 0;
    }

    /**
     * 
     */
    public class OvniValues implements Serializable {

        /**
         * 
         */
        public final int PROBABILITE_APPARITION_OVNI = 100;
        /**
         * 
         */
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
