/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import content.SoundBank;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Collisionable;
import util.Dessinable;
import util.SoundManager;

/**
 * Cette classe Java montre des exemples d'utilisation de l'API à titre de 
 * référence pour le développement du jeu.
 * @author guillaume
 */
public class Exemples extends Dessinable implements Collisionable {

    private Rectangle r;

    public Exemples() {
        this.image = Main.imageBank.BOSS1;
        r = new Rectangle(10, 10, 10, 10);
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawImage(image, 1, 1, 1, 1, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawLine(1, 1, 1, 1);
    }

    @Override
    public Rectangle getRectangle() {
        return r;
    }

    @Override
    public void collision(Collisionable c) {
        SoundManager.play(SoundBank.MISSILE);
    }

    @Override
    public int getDommage() {
        return 5;
    }
}
