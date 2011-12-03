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
package graphique.component;

import content.KeySetting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.Main;

import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Classe pour le Canon. Les canons sont instanciés au début du programme et non
 * à la volée. Il y a deux canons possibles.
 * @author Nafie Hamrani && Guillaume Poirier-Morency
 */
public final class Canon extends Dessinable implements Collisionable {
    ////////////////////////////////////////////////////////////////////////////
    // Variables propres aux canons.

    /**
     * ID pour le canon 1.
     */
    /**
     * ID pour le canon 2.
     */
    public static final int CANON1_ID = 0, CANON2_ID = 1;
    /**
     * 
     */
    public static boolean isCanon2ValidTarget = false;
    /**
     * 
     */
    public static final int VIE_INIT_CANON = 1000;
    /**
     * 
     */
    private static final double MOVEMENT_INCREMENT_CANON = 3.0;
    /**
     * 
     */
    private static final double ANGLE_INCREMENT_CANON = Math.PI / 60.0;
    /**
     * 
     */
    private static final int LATENCE_DU_TIR = 250;
    /**
     * 
     */
    /**
     * 
     */
    private static final int HAUTEUR_DU_CANON = 100, LARGEUR_DU_CANON = 255;
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Variables définissant si le canon 2 est une cible valide pour un projectile ennemi.
     * Cette variable est particulièrement utile lorsque le joueur joue en mode
     * solo, ainsi les projectiles auto-guidées ne se dirigent pas vers le
     * deuxième canon.
     */
    private Vecteur A, B, C, D;
    private double tetha = Math.PI;
    /**
     * 
     */
    private Vecteur position;
    // TODO Algorithme de draw pour positionner le canon sur la partie la plus basse de l'écran.
    /**
     * 
     */
    private int vie;
    /**
     * 
     */
    final int NUMERO_DU_CANON;
    /**
     * 
     */
    private boolean peutTirer = true;

    /**
     * Getter pour les vies, cela empêche les autres composantes du programme
     * d'altérer les points de vies propres au canon.
     * @return les points de vie du canon.
     */
    public int getVie() {
        return vie;
    }

    /**
     * Constructeur pour l'objet de canon.
     * @param numeroDuCanon est le numéro de canon. Deux valeurs sont possibles,
     * 0 qui définit le canon 1 et 1 qui définit le canon 2.
     */
    public Canon(int numeroDuCanon) {

        switch (numeroDuCanon) {
            case 0:
                image0 = Main.imageBank.ship1;
                image1 = Main.imageBank.subcanon1;
                position = new Vecteur(0, Main.getCanvasSizeY() - 101);
                break;
            case 1:
                image0 = Main.imageBank.ship2;
                image1 = Main.imageBank.subcanon2;
                position = new Vecteur(Main.getCanvasSizeX() - 256, Main.getCanvasSizeY() - 101);
                break;
        }
        NUMERO_DU_CANON = numeroDuCanon;
        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = VIE_INIT_CANON;
    }

    /**
     * Retourne le vecteur position du pied de canon. Les missiles prennent
     * origine à cette position.
     * @return ce vecteur.
     */
    public Vecteur piedDeCanon() {
        return new Vecteur(position.x + LARGEUR_DU_CANON / 2, position.y + HAUTEUR_DU_CANON / 4);
    }

    /**
     * Gère la touche e dans l'objet canon.
     * @param e est le code de la touche du clavier à gérer.
     */
    public void gererEvenementDuClavier(Integer e) {
        if (e == null) {
            // null check, un évenement null peut arriver.
            return;
        }
        if (Main.isPaused | Main.showHighscores) {
            return;
        }
        if (NUMERO_DU_CANON == 0) {
            switch (e) {
                case KeySetting.CANON_1_LEFT:
                    moveGauche();
                    break;
                case KeySetting.CANON_1_RIGHT:
                    moveDroite();
                    break;
                case KeySetting.CANON_1_SHOOT:
                    this.tirer();
                    break;
                case KeySetting.CANON_1_AIM_LEFT:
                    moveCanonGauche();
                    break;
                case KeySetting.CANON_1_AIM_RIGHT:
                    moveCanonDroite();
                    break;
            }
            // On gere l'evenement
        } else if (NUMERO_DU_CANON == 1 && isCanon2ValidTarget) {
            switch (e) {
                case KeySetting.CANON_2_LEFT:
                    moveGauche();
                    break;
                case KeySetting.CANON_2_RIGHT:
                    moveDroite();
                    break;
                case KeySetting.CANON_2_SHOOT:
                    this.tirer();
                    break;
                case KeySetting.CANON_2_AIM_LEFT:
                    moveCanonGauche();
                    break;
                case KeySetting.CANON_2_AIM_RIGHT:
                    moveCanonDroite();
                    break;
            }
        }
    }

    /**
     * Déplace le canon (direction de tir) vers la gauche.
     */
    private void moveCanonGauche() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        tetha -= ANGLE_INCREMENT_CANON;

    }

    /**
     * Déplace le canon (direction de tir) vers la gauche.
     */
    private void moveCanonDroite() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        tetha += ANGLE_INCREMENT_CANON;

    }

    /**
     * Déplace le canon vers la gauche de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveGauche() {
        if (this.position.x > 0) {
            A.x -= MOVEMENT_INCREMENT_CANON;
            B.x -= MOVEMENT_INCREMENT_CANON;
            C.x -= MOVEMENT_INCREMENT_CANON;
            D.x -= MOVEMENT_INCREMENT_CANON;
            position.x -= MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Déplace le canon vers la droite de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveDroite() {
        if (this.position.x + LARGEUR_DU_CANON + 1 < Main.getCanvasSizeX()) {
            A.x += MOVEMENT_INCREMENT_CANON;
            B.x += MOVEMENT_INCREMENT_CANON;
            C.x += MOVEMENT_INCREMENT_CANON;
            D.x += MOVEMENT_INCREMENT_CANON;
            position.x += MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Effectue un tir!
     */
    private void tirer() {

        if (peutTirer) {
            //Main.son.play(Main.soundBank.explosion);
            Main.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((D.x - A.x) / 2, (D.y - A.y) / 2), 0));
            peutTirer = false;
            // Le Thread sert à attendre un certain temps avant d'effectuer un autre tir.
            (new Thread("Thread pour le temps d'attente entre chaque tir de canon.") {

                @Override
                public void run() {
                    try {
                        Thread.sleep(LATENCE_DU_TIR);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    } finally {
                        peutTirer = true;
                    }
                }
            }).start();
        }
    }

    @Override
    public void dessiner(Graphics g) {
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        AffineTransform at = new AffineTransform();
        // TODO Rotation du dit canon!
        at.rotate(tetha);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image1, at, null);
        g.setColor(Color.BLACK);
        g.fillPolygon(xPoints, yPoints, 4);
        //g.drawImage(image1, xPoints[1], yPoints[1], null);
        g.drawImage(image0, (int) position.x, (int) position.y, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (position.x), (int) (position.y), (int) LARGEUR_DU_CANON, (int) HAUTEUR_DU_CANON);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.y, (int) LARGEUR_DU_CANON, (int) HAUTEUR_DU_CANON);
    }

    @Override
    public void collision(Collisionable c) {
        if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            if (this.NUMERO_DU_CANON == CANON2_ID && !isCanon2ValidTarget) {
            } else {
                this.vie -= c.getDommage();
            }
        }
        if (vie < 0) {
            this.isDessinable = false;
            // Un canon est mort, la partie est fini!
            Main.terminerPartie("Le canon " + this.NUMERO_DU_CANON + " est mort!");
        }
    }

    @Override
    public int getDommage() {
        // Entrer en collision avec un canon ne cause pas de dommages, mais le projectile ennemi disparaît quand même.
        return 0;
    }
}
