package graphique;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import util.Collisionable;
import util.Dessinable;

/**
 *
 * @author Nafie Hamrani
 */
public class Ovni extends Dessinable implements Collisionable {

    int x, vitesseX = 1, id, vie;
    int y = 10;
    Image img;
    Rectangle ovni;

    public Ovni(int x, int id) {
        this.x = x;
        this.id = id;
    }

    public void mouvement() {
        switch (id) {

            case 1:// mouvement d'un enemi normal 
                x += 3 * vitesseX;
                ovni.setLocation(x, y);
                break;
            case 2:// mouvement du supersonic
                break;
            case 3:// mouvement du boss 1 
                break;
            case 4:// mouvement du boss 2
                break;
            case 5:// mouvement du boss 3
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");

        }
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void setImage(String s) {
        img = Toolkit.getDefaultToolkit().getImage(s);
    }

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
        return new Rectangle(x, y, 50, 50);
    }

    @Override
    public void collision(Collisionable c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
