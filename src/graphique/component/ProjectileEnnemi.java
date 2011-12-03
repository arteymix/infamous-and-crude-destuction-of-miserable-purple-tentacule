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

import graphique.event.Explosion;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Dessinable;
import java.awt.Graphics;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile ennemi.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
final class ProjectileEnnemi extends Dessinable implements Collisionable, Serializable {
    ////////////////////////////////////////////////////////////////////////////
    // Constantes pour les projectiles ennemis.

    public static final int PROJECTILE_ENNEMI = 1,
            PROJECTILE_ENNEMI_OR = 2,
            PROJECTILE_ENNEMI_SUPERSONIC = 3,
            PROJECTILE_ENNEMI_SUPERSONIC_OR = 4,
            PROJECTILE_BOSS = 5;
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Vecteur définissant la position du projectile ennemi dans l'espace.
     */
    private Vecteur position;
    /**
     * Rectangle définissant l'aire occupé par le projectile ennemi.
     */
    private Rectangle rectangle = new Rectangle(0, 0, 60, 60);
    /**
     * 
     */
    private final int ID;
    /**
     * 
     */
    private int dommage = 10;
    /**
     * 
     */
    public boolean isInvincible = false;
    /**
     * Si true, on quitte à la prochaine itération afin de permettre au quatrième
     * tentacule d'être dessiné.
     */
    private boolean quitNextIterations = false;

    /**
     * Constructeur pour un projectile ennemi. Peut uniquement être appelé par
     * un objet ovni.
     * @param init est un vecteur position qui définit la position initiale du 
     * projectile ennemi.
     */
    ProjectileEnnemi(Vecteur init, int id) {
        position = new Vecteur(init.x, init.y);
        ID = id;
        switch (id) {
            case PROJECTILE_ENNEMI:
                image0 = Main.imageBank.projectileEnnemi;
                break;
            case PROJECTILE_ENNEMI_OR:
                image0 = Main.imageBank.projectileEnnemiOr;
                break;
            case PROJECTILE_ENNEMI_SUPERSONIC:
                image0 = Main.imageBank.projectileEnnemiSupersonic;
                break;
            case PROJECTILE_ENNEMI_SUPERSONIC_OR:
                image0 = Main.imageBank.projectileEnnemiSupersonicOr;
                break;
            case PROJECTILE_BOSS:
                image0 = Main.imageBank.projectileBoss;
                break;
        }
    }

    @Override
    public void dessiner(Graphics g) {

        if (quitNextIterations) {
            Main.terminerPartie("Quatre tentacules ont touché le sol!");
        }

        if (ID == ProjectileEnnemi.PROJECTILE_ENNEMI && position.y >= Main.canvasSize.y - rectangle.height) {
            if (isInvincible == false) {
                Main.alienAuSol++;
                if (Main.alienAuSol == 4) {
                    quitNextIterations = true;

                }
            }
            isInvincible = true;
            dommage = 0;

            g.drawImage(image0, (int) position.x, (int) position.y, rectangle.width, rectangle.height, null);

            return;
        }
        g.drawImage(image0, (int) position.x, (int) position.y++, rectangle.width, rectangle.height, null);
        if (position.y >= Main.canvasSize.y) {
            isDessinable = false;
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (quitNextIterations) {
            Main.terminerPartie("Quatre tentacules ont touché le sol!");
        }

        if (ID == ProjectileEnnemi.PROJECTILE_ENNEMI && position.y >= Main.canvasSize.y - rectangle.height) {
            if (isInvincible == false) {
                Main.alienAuSol++;
                if (Main.alienAuSol == 4) {
                    quitNextIterations = true;

                }
            }
            isInvincible = true;
            dommage = 0;

            g.drawRect((int) position.x, (int) position.y, rectangle.width, rectangle.height);

            return;
        }
        g.drawRect((int) position.x, (int) position.y++, rectangle.width, rectangle.height);
        if (position.y >= Main.canvasSize.y) {
            isDessinable = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        rectangle.x = (int) this.position.x;
        rectangle.y = (int) this.position.y;
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        if (isInvincible) {
            return;
        }
        if (c instanceof Projectile) {
            this.isDessinable = false;
            Main.points += 25;
            // On se sert de l'explosion du projectile...
        } else if (c instanceof Canon) {
            if (((Canon) c).NUMERO_DU_CANON == 1 && !Canon.isCanon2ValidTarget) {
                // Ne rien faire, le canon ne  peut être atteint, il est invisible.
            } else {
                this.isDessinable = false;
                Main.composantesDessinables.add(new Explosion(this.position));
            }
        }
    }

    @Override
    public int getDommage() {
        return dommage;
    }
}
