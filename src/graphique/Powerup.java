/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import util.Collisionable;
import util.Dessinable;

/**
 * Objet dessinable pour les powerups.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Powerup extends Dessinable implements Collisionable, Serializable {

    @Override
    public void dessiner(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getRectangle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void collision(Collisionable c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
