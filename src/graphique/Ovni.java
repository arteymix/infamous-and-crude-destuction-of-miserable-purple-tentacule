package graphique;

import content.ImageBank;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 *
 * @author Nafie Hamrani
 */
public class Ovni extends Dessinable implements Collisionable
{

    private static boolean isBoss = false;
    double x, y, vitesseX = 1.0;
    int id, vie;
    Image img;
    private static Random r = new Random();

    public Ovni(Vecteur v, int id)
    {
        if (isBoss)
        {
            isDessinable = false;
        }
        this.x = v.x;
        this.id = id;
        configurerOvni(id);

    }

    private void configurerOvni(int id)
    {

        switch (id)
        {
            case 1://img enemi et enemi or et vie
                if (r.nextInt(10000) == 1)
                {
                    img = Main.imageBank.ENEMIOR;
                    vie = 30;
                }
                else
                {
                    img = Main.imageBank.ENEMI;
                    vie = 10;
                }
                break;
            case 2:// img supersonic et supersonicor
                if (r.nextInt(10000) == 1)
                {
                    img = Main.imageBank.SUPERSONICOR;
                    vie = 200;
                    vitesseX = 3;
                }
                else
                {
                    img = Main.imageBank.SUPERSONIC;
                    vitesseX = 2;
                }
                break;
            case 3:// img boss 1
                img = Main.imageBank.BOSS1;
                vie = 1000;
                break;
            case 4:// img boss 2
                img = Main.imageBank.BOSS2;
                vie = 1500;
                break;
            case 5:// img boss 3
                img = Main.imageBank.BOSS3;
                vie = 2000;
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");

        }

    }

    public void mouvement()
    {
        switch (id)
        {
            case 1:// mouvement d'un enemi normal 
                x += 3 * vitesseX;
                break;
            case 2:// mouvement du supersonic
                x -= 3 * vitesseX;
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

    public void setVitesseX(int vitesseX)
    {
        this.vitesseX = vitesseX;
    }

    @Override
    public void dessiner(Graphics g)
    {
        mouvement();
        g.drawImage(img, (int) x, (int) y, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g)
    {
        mouvement();
        g.drawOval((int) (x), (int) (y), 100, 100);

    }

    @Override
    public Rectangle getRectangle()
    {
        return new Rectangle();
    }

    @Override
    public void collision(Collisionable c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
