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
     * Retourne le Rectangle de l'objet Collisionable. Ce Rectangle sert d'objet
     * de calcul lorsqu'un objet Collisionable entre en collision avec un autre
     * objet du même type. 
     * @return le rectangle de l'objet Collisionable.
     */
    public Rectangle getRectangle();

    /**
     * Provoque une collision de l'objet courant avec un autre objet Collisionable.
     * @param c est l'objet Collisionable qui a provoqué la collision.
     */
    public void collision(Collisionable c);

    /**
     * Méthode qui obtient les dommage qu'un objet collisionable cause lors d'une collision.
     * @return les dommages que l'objet collisionnable cause lors d'une collision.
     */
    public int getDommage();
}
