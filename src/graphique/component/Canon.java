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

import graphique.event.PowerUp;
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
    private static final double MOVEMENT_INCREMENT_CANON = 5.0;
    /**
     * 
     */
    private static final double ANGLE_INCREMENT_CANON = Math.PI / 60.0;
    /**
     * Variable qui définit la hauteur standard du canon.
     */
    /**
     * Variable qui définit la largeur standard du canon.
     */
    private static final int HAUTEUR_DU_CANON = 100, LARGEUR_DU_CANON = 255;
    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    /**
     * 
     */
    private int LATENCE_DU_TIR = 250;
    /**
     * Variables définissant si le canon 2 est une cible valide pour un projectile ennemi.
     * Cette variable est particulièrement utile lorsque le joueur joue en mode
     * solo, ainsi les projectiles auto-guidées ne se dirigent pas vers le
     * deuxième canon.
     */
    private Vecteur positionA, positionB, positionC, positionD;
    private double tetha = Math.PI;
    /**
     * Est le vecteur position du canon.
     */
    private Vecteur position;
    /**
     * Est le nombre de points de vie que le canon possède.
     */
    private int vie;
    /**
     * Est l'identifiant unique du canon.
     */
    final int NUMERO_DU_CANON;
    /**
     * Définit si le canon à l'autorisation de tirer. Cette variable est 
     * controllée par un thread qui la rends true un certain temps après avoir
     * tiré, ce qui empêche les tirs fous.
     */
    private boolean peutTirer = true;
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Constructeur pour l'objet de canon.
     * @param numeroDuCanon est le numéro de canon. Deux valeurs sont possibles,
     * 0 qui définit le canon 1 et 1 qui définit le canon 2.
     */
    public Canon(int numeroDuCanon) {

        switch (numeroDuCanon) {
            case 0:               
                position = new Vecteur(0, Main.getCanvasSizeY() - 101 - 30);
                break;
            case 1:                
                position = new Vecteur(Main.getCanvasSizeX() - 256, Main.getCanvasSizeY() - 101 - 30);
                break;
        }
        NUMERO_DU_CANON = numeroDuCanon;
        positionA = piedDeCanon().additionAffine(new Vecteur(15, -70));
        positionB = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        positionC = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        positionD = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = VIE_INIT_CANON;
    }

    /**
     * Getter pour les vies, cela empêche les autres composantes du programme
     * d'altérer les points de vies propres au canon.
     * @return les points de vie du canon.
     */
    public int getVie() {
        return vie;
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
        this.positionA.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.positionB.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.positionC.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.positionD.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        tetha -= ANGLE_INCREMENT_CANON;
    }

    /**
     * Déplace le canon (direction de tir) vers la gauche.
     */
    private void moveCanonDroite() {
        this.positionA.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.positionB.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.positionC.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.positionD.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        tetha += ANGLE_INCREMENT_CANON;
    }

    /**
     * Déplace le canon vers la gauche de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveGauche() {
        if (this.position.x > 0) {
            positionA.x -= MOVEMENT_INCREMENT_CANON;
            positionB.x -= MOVEMENT_INCREMENT_CANON;
            positionC.x -= MOVEMENT_INCREMENT_CANON;
            positionD.x -= MOVEMENT_INCREMENT_CANON;
            position.x -= MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Déplace le canon vers la droite de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveDroite() {
        if (this.position.x + LARGEUR_DU_CANON + 1 < Main.getCanvasSizeX()) {
            positionA.x += MOVEMENT_INCREMENT_CANON;
            positionB.x += MOVEMENT_INCREMENT_CANON;
            positionC.x += MOVEMENT_INCREMENT_CANON;
            positionD.x += MOVEMENT_INCREMENT_CANON;
            position.x += MOVEMENT_INCREMENT_CANON;
        }
    }
    private static int PROJECTILE_DEFAULT_DAMAGE = 5;

    /**
     * Effectue un tir!
     */
    private void tirer() {
        if (peutTirer) {
            int dommages = PROJECTILE_DEFAULT_DAMAGE;
            if (this.POWER_FAST_SHOT_OBTAINED | this.POWER_SHOT_OBTAINED) {
                dommages = PROJECTILE_DEFAULT_DAMAGE * 2;
            }
            Main.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((positionD.x - positionA.x) / 2, (positionD.y - positionA.y) / 2), 0, tetha, dommages));
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
    ////////////////////////////////////////////////////////////////////////////
    // Méthode propres aux Dessinable et Collisionable

    @Override
    public void dessiner(Graphics g) {
        AffineTransform at = new AffineTransform();
        double x = piedDeCanon().x;
        double y = piedDeCanon().y;
        at.translate(x, y);
        at.rotate(tetha, 10, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.NUMERO_DU_CANON == 0 ? Main.imageBank.subcanon1 : Main.imageBank.subcanon2, at, null);
        at.translate(-x, -y);
        g.setColor(Color.BLACK);
        g.drawImage(this.NUMERO_DU_CANON == 0 ? Main.imageBank.ship1 : Main.imageBank.ship2, (int) position.x, (int) position.y, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        int[] xPoints = {(int) positionA.x, (int) positionB.x, (int) positionC.x, (int) positionD.x};
        int[] yPoints = {(int) positionA.y, (int) positionB.y, (int) positionC.y, (int) positionD.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (position.x), (int) (position.y), (int) LARGEUR_DU_CANON, (int) HAUTEUR_DU_CANON);
        g.drawString("Canon " + this.NUMERO_DU_CANON, (int) position.x + 15, (int) position.y + 45);
        g.drawString("Latence du tir : " + this.LATENCE_DU_TIR + " ms", (int) position.x + 15, (int) position.y + 60);
        g.drawString("Vies : " + this.vie + " vies", (int) position.x + 15, (int) position.y + 75);
        g.drawString("Fast shot : " + FAST_SHOT_OBTAINED, (int) position.x + 15, (int) position.y + 90);
        g.drawString("Power fast shot : " + POWER_FAST_SHOT_OBTAINED, (int) position.x + 15, (int) position.y + 105);
        g.drawString("Power shot : " + POWER_SHOT_OBTAINED, (int) position.x + 15, (int) position.y + 120);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.y, (int) LARGEUR_DU_CANON, (int) HAUTEUR_DU_CANON);
    }
    private boolean FAST_SHOT_OBTAINED = false,
            POWER_FAST_SHOT_OBTAINED = false,
            POWER_SHOT_OBTAINED = false;

    @Override
    public void collision(Collisionable c) {
        if (c instanceof PowerUp) {
            switch (((PowerUp) c).id) {
                case PowerUp.FAST_SHOT:
                    LATENCE_DU_TIR = 125;
                    FAST_SHOT_OBTAINED = true;
                    break;
                case PowerUp.POWER_FAST_SHOT:
                    POWER_FAST_SHOT_OBTAINED = true;
                    LATENCE_DU_TIR = 125;
                    break;
                case PowerUp.POWER_SHOT:
                    POWER_SHOT_OBTAINED = true;
                    LATENCE_DU_TIR = 250;
                    break;
            }
        } else if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            if (this.NUMERO_DU_CANON == CANON2_ID && !isCanon2ValidTarget) {
            } else {
                this.vie -= c.getDommage() * Main.level;
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
    ////////////////////////////////////////////////////////////////////////////
}
