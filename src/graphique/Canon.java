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
package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Classe pour le Canon. Les canons sont instanciés au début du programme et non
 * à la volée. Il y a deux canons possibles.
 * @author Nafie Hamrani && Guillaume Poirier-Morency
 */
public final class Canon extends Dessinable implements Collisionable, Serializable {

    /**
     * Variable définissant si le canon 2 est une cible valide pour un projectile ennemi.
     * Cette variable est particulièrement utile lorsque le joueur joue en mode
     * solo, ainsi les projectiles auto-guidées ne se dirigent pas vers le
     * deuxième canon.
     */
    private Vecteur A, B, C, D;
    private Vecteur position;
    // TODO Algorithme de draw pour positionner le canon sur la partie la plus basse de l'écran.
    /**
     * 
     */
    int vie;
    final int NUMERO_DU_CANON;
    private boolean peutTirer = true;

    /**
     * Constructeur pour l'objet de canon.
     * @param numeroDuCanon est le numéro de canon. Deux valeurs sont possibles,
     * 0 qui définit le canon 1 et 1 qui définit le canon 2.
     */
    public Canon(int numeroDuCanon) {

        switch (numeroDuCanon) {
            case 0:
                image0 = Main.imageBank.CANON_0;
                image1 = Main.imageBank.SUBCANON1;
                position = new Vecteur(0, 699);
                break;
            case 1:
                image0 = Main.imageBank.CANON_1;
                image1 = Main.imageBank.SUBCANON2;
                position = new Vecteur(689, 699);
                break;
        }
        NUMERO_DU_CANON = numeroDuCanon;
        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = Main.gameValues.canon.VIE_INIT_CANON;
    }

    /**
     * Retourne le vecteur position du pied de canon. Les missiles prennent
     * origine à cette position.
     * @return ce vecteur.
     */
    public Vecteur piedDeCanon() {
        return new Vecteur(position.x + Main.gameValues.canon.LARGEUR_DU_CANON / 2, position.y + Main.gameValues.canon.HAUTEUR_DU_CANON / 4);
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
        if (NUMERO_DU_CANON == 0) {
            switch (e) {
                case KeyEvent.VK_LEFT:
                    moveGauche();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveDroite();
                    break;
                case KeyEvent.VK_UP:
                    this.tirer();
                    break;
                case KeyEvent.VK_NUMPAD2:
                    moveCanonGauche();
                    // 
                    break;
                case KeyEvent.VK_NUMPAD3:
                    moveCanonDroite();
                    break;
            }
            // On gere l'evenement
        } else if (NUMERO_DU_CANON == 1 && Main.gameValues.canon.isCanon2ValidTarget) {
            switch (e) {
                case KeyEvent.VK_A:
                    moveGauche();
                    break;
                case KeyEvent.VK_D:
                    moveDroite();
                    break;
                case KeyEvent.VK_W:
                    this.tirer();
                    break;
                case KeyEvent.VK_O:
                    moveCanonGauche();
                    break;
                case KeyEvent.VK_P:
                    moveCanonDroite();
                    break;
            }
        }
    }

    /**
     * Déplace le canon (direction de tir) vers la gauche.
     */
    private void moveCanonGauche() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);

    }

    /**
     * Déplace le canon (direction de tir) vers la gauche.
     */
    private void moveCanonDroite() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);

    }

    /**
     * Déplace le canon vers la gauche de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveGauche() {
        if (this.position.x > 0) {
            A.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            B.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            C.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            D.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            position.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Déplace le canon vers la droite de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveDroite() {
        if (this.position.x + Main.gameValues.canon.LARGEUR_DU_CANON + 1 < Main.gameValues.canvasSize.x) {
            A.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            B.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            C.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            D.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            position.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Effectue un tir!
     */
    private void tirer() {

        if (peutTirer) {
            Main.gameValues.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((D.x - A.x) / 2, (D.y - A.y) / 2), 0));
            peutTirer = false;
            // Le Thread sert à attendre un certain temps avant d'effectuer un autre tir.
            (new Thread("Thread pour le temps d'attente entre chaque tir de canon.") {

                @Override
                public void run() {
                    try {
                        Thread.sleep(Main.gameValues.canon.LATENCE_DU_TIR);
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
        if (!Main.gameValues.canon.isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        ////////////
        /*
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = new AffineTransform();
        //rotate the image0 
        affineTransform.translate(piedDeCanon().x + 20, piedDeCanon().y);
        affineTransform.rotate(Math.toRadians(180));
        //draw the image0 using the AffineTransform 
        g2d.drawImage(imageSubCanon, affineTransform, null);
        ///*/
        g.drawImage(image1, xPoints[1], yPoints[1], null);
        g.drawImage(image0, (int) position.x, (int) position.y, null);
        g.setColor(Color.RED);
        g.fillRect((int) position.x, (int) position.y, vie / 5, 10);
        g.setColor(Color.BLACK);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (!Main.gameValues.canon.isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (position.x), (int) (position.y), (int) Main.gameValues.canon.LARGEUR_DU_CANON, (int) Main.gameValues.canon.HAUTEUR_DU_CANON);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.y, (int) Main.gameValues.canon.LARGEUR_DU_CANON, (int) Main.gameValues.canon.HAUTEUR_DU_CANON);
    }

    @Override
    public void collision(Collisionable c) {
        if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            if (this.NUMERO_DU_CANON == 1 && !Main.gameValues.canon.isCanon2ValidTarget) {
            } else {
                this.vie -= c.getDommage();
            }
        }
        if (vie < 0) {
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        // Entrer en collision avec un canon ne cause pas de dommages, mais le projectile ennemi disparaît quand même.
        return 0;
    }
}
