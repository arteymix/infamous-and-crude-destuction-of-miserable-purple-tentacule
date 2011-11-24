/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

/**
 * Contient tous les param
 * @author guillaume
 */
public class GameValues {

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

        public int PROBABILITE_APPARITION_OVNI = 1000;
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
