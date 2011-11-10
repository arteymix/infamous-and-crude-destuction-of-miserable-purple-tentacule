/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Rectangle;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public interface Collisionable {
    /**
     * Retourne le Rectangle de l'objet Collisionable.
     * @return 
     */
    public Rectangle getRectangle();
    /**
     * Provoque une collision de l'objet courant avec un autre objet Collisionable.
     * @param c est l'objet Collisionable qui a provoqué la collision.
     */
    public void collision(Collisionable c);

    
}
