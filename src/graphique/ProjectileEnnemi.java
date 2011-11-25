package graphique;

import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile ennemi.
 * @author Guillaume Poirier-Morency
 */
public final class ProjectileEnnemi extends Dessinable implements Collisionable, Serializable {

    /**
     *
     */
    private Vecteur position;
    /**
     * 
     */
    public static final int MISSILE_0 = 0;
Rectangle rectangle = new Rectangle(0,0,30,30);
    /**
     * 
 * @param init 
 * @param id
     */
    public ProjectileEnnemi(Vecteur init,int id) {
        position = new Vecteur(init.x,init.y);
        image = Main.imageBank.MEDUSE;
        
    }

    @Override
    public void dessiner(Graphics g) {        
        g.drawImage(image, (int) position.x, (int) position.y++,rectangle.width, rectangle.height, null);
        if(position.y >= Main.gameValues.CANVAS_SIZE.y) {
        isDessinable = false;
        
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawRect((int) position.x, (int) position.y++, rectangle.width, rectangle.height);
        if(position.y >= Main.gameValues.CANVAS_SIZE.y) {
        isDessinable = false;
        
        }
    }

    @Override
    public Rectangle getRectangle() {
        rectangle.x = (int)this.position.x;
        rectangle.y = (int)this.position.y;
        return rectangle;
    }
    
    

    @Override
    public void collision(Collisionable c) {
        if(c instanceof Projectile | c instanceof Canon) {
        this.isDessinable = false;
        
        }
    }

    @Override
    public int getDommage() {
      return 10;
    }

    
}
