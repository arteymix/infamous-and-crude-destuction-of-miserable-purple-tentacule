/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import java.util.HashSet;

/**
 * Gestionnaire de collisions.
 *
 * @author guillaume
 */
public class CollisionManager extends Thread {

    private HashSet<Collisionable> mCollisionables;

    public void addCollisionable(Collisionable c) {
        mCollisionables.add(c);
    }

    public void removeCollisionable(Collisionable c) {
        mCollisionables.remove(c);
    }

    @Override
    public void run() {
        for (Collisionable d : mCollisionables) {
            /* Sous-boucle pour les collisions, question de tenter toutes
             * les collisions possibles. 
             */
            if (d instanceof Collisionable) {
                for (Collisionable e : mCollisionables) {
                    // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                    if ((e instanceof Collisionable) && d != e) {
                        if (((Collisionable) d).getRectangle().intersects(((Collisionable) e).getRectangle())) {
                            // On provoque une collision entre chacun.
                            ((Collisionable) d).collision((Collisionable) e);
                            ((Collisionable) e).collision((Collisionable) d);
                        }
                    }
                }
            }
        }
    }
}
