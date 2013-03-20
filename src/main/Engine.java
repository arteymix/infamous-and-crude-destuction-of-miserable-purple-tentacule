/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import collision.CollisionManager;
import collision.Collisionable;
import util.Dessinable;

/**
 * Game engine.
 *
 * @author guillaume
 */
public class Engine extends Thread {

    private boolean isRunning;
    /**
     * Surface pour dessiner.
     */
    private Surface mSurface;
    private CollisionManager mCollisionManager;

    public Engine(Surface pSurface) {
        mSurface = pSurface;
    }
    
    public void run() {
        mCollisionManager.start();
    }

    public void addDessinable(Dessinable d) {
        mSurface.addDessinable(d);
        if (d instanceof Collisionable) {
            mCollisionManager.addCollisionable((Collisionable) d);
        }
    }

    public void removeDessinable(Dessinable d) {
        mSurface.addDessinable(d);
        if (d instanceof Collisionable) {
            mCollisionManager.removeCollisionable((Collisionable) d);
        }
    }
}
