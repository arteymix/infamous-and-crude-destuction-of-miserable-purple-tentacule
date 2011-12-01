package graphique.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Classe pour les ovnis.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Ovni extends Dessinable implements Collisionable, Serializable
{

    /**
     * 
     */
    double x, y, vitesseX = 1.0;
    int id, vie;
    private static Random r = new Random();

    /*
     * 
     * 
     * 
     * 
     * 
     * LES CONSTANTES ONT ÉTÉ TRANSFÉRÉ DANS :
     * 
     * Main.gameValues.ovni.XXXXXXXXXXXXXXX
     * 
     * 
     * 
     * 
     */
    private Ovni(int x, int y, int id)
    {

        /* On va pas faire ça compliqué. Il y a une méthode en dessous qui
         * s'appelle create ovni vas-y! 
         */

        if (Main.gameValues.ovni.isBoss)
        {
            isDessinable = false;
        }
        this.id = id;
        this.x = x;
        this.y = y;

        configurerOvni(id);

    }

    /**
     * 
     */
    public static void createOvni()
    {
        // Ici tu mets l'algo de génération aléatoire pour le id
        if ((new Random()).nextInt(Main.gameValues.ovni.PROBABILITE_APPARITION_OVNI) == 1)
        {
            int i = initializeID();
            int y = new Random().nextInt(250);
            if (i == 0)
            {
                return;
            }
            if (i == 1)
            {
                Main.gameValues.composantesDessinables.add(new Ovni(0, y, 3));
            }
            else if (i == 2)
            {
                Main.gameValues.composantesDessinables.add(new Ovni((int) Main.gameValues.canvasSize.x - 100, y, i));
                System.out.println("supersonik");
            }
            else if (i == 3)
            {
                Main.gameValues.composantesDessinables.add(new Ovni((int) Main.gameValues.canvasSize.y - 100, y, i));
                System.out.println("supersonik");
            }

        }
        /* Bon je te laisse coder l'algorithme de génération en fonction de tes
         * besoins. Le constructeur peut être private maintenant!
         * Comme ça on ne surcharge pas le main avec du code et...
         * TU TE CHARGE DES MISSILES ENNEMIS MUHAHA!
         * C'est pour ça que je te fais une méthode tirer()...
         * 
         */
    }

    private static int initializeID()
    {
        int generateur = new Random().nextInt(100);
        if (!Main.gameValues.ovni.isBoss)
        {
            System.out.println(Main.gameValues.timerSeconds);
            if (Main.gameValues.timerSeconds == 15)
            {

                System.out.println("creation du boss");
                return 3;
            }
            if (generateur <= 75)
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        return 0;

    }

    /**
     * 
     * @param id est le id du missile à tirer. 
     */
    private void tirer(int i)
    {
        // Sa devrait être cool maintenant...
        Main.gameValues.composantesDessinables.add(new ProjectileEnnemi(new Vecteur(x, y), Main.gameValues.projectileEnnemi.MISSILE_0));
    }

    private void configurerOvni(int id)
    {

        switch (id)
        {
            case 1://img enemi et enemi or et vie
                if (r.nextInt(10000) == 1)
                {
                    image0 = Main.imageBank.ennemiOr;
                    vie = 30;
                }
                else
                {
                    image0 = Main.imageBank.ennemi;
                    vie = 10;
                }
                break;
            case 2:// image0 ennemiSupersonic et supersonicor
                if (r.nextInt(10000) == 1)
                {
                    image0 = Main.imageBank.ennemiSupersonicOr;
                    vie = 50 * Main.gameValues.level;
                    vitesseX = 3;
                }
                else
                {
                    image0 = Main.imageBank.ennemiSupersonic;
                    vie = 15 * Main.gameValues.level;
                    vitesseX = 3;
                }
                break;
            case 3:// image0 boss 1
                image0 = Main.imageBank.boss;
                vie = 1000;
                break;
            case 4:// image0 boss 2
                image0 = Main.imageBank.boss;
                vie = 1500;
                break;
            case 5:// image0 boss 3
                image0 = Main.imageBank.boss;
                vie = 2000;
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");
        }
    }

    /**
     * 
     */
    private void action()
    {
        switch (id)
        {
            case 1:// mouvement d'un enemi normal 
                x += 3 * vitesseX;
                break;
            case 2:// mouvement du ennemiSupersonic
                x -= 3 * vitesseX;
                break;
            case 3:// mouvement du boss 1
                mouvtBoss12(15, 300);
                break;
            case 4:// mouvement du boss 2
                vitesseX = 3;
                mouvtBoss12(15, 745);
                break;
            case 5:// mouvement du boss 3
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");

        }
        if ((new Random()).nextInt(100) == 1)
        {
            tirer(0);
        }
    }

    private void mouvtBoss12(int ymin, int ymax)
    {
        final int XMIN = 1;
        final int XMAX = (int) Main.gameValues.canvasSize.x-1;
        final int YMIN = ymin;
        final int YMAX = ymax;
        int deplacementX = 3;
        int  deplacementY =3;

        if (x == XMIN)
        {
            deplacementX=3;
        }
        else if (x == XMAX)
        {
            deplacementX=-3;
        }
        if (y == YMIN)
        {
           deplacementX=3;
        }
        else if (y == YMAX)
        {
            deplacementY=-3;
        }
        x += deplacementX * vitesseX;
        y += deplacementY * vitesseX;
    }

/**
 * 
 * @param vitesseX
 */
public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    @Override
        public void dessiner(Graphics g) {
        action();
        g.drawImage(image0, (int) x, (int) y, null);
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, vie * 7, 10);
        g.setColor(Color.BLACK);
        if (x > Main.gameValues.canvasSize.x) {
            isDessinable = false;


        }
    }

    @Override
        public void dessinerDeboguage(Graphics g) {
        action();
        g.drawOval((int) (x), (int) (y), 100, 100);
        if (x > Main.gameValues.canvasSize.x) {
            isDessinable = false;


        }

    }

    @Override
        public Rectangle getRectangle() {
        int longeur = 0, hauteur = 0;
        switch (id) {
            case 1://Grandeur du rectangle d'un enemi normal 
                longeur = hauteur = 100;
                break;
            case 2:// Grandeur du rectangle  du ennemiSupersonic
                longeur = 150;
                hauteur = 100;
                break;
            case 3:// Grandeur du rectangle  du boss 1
                longeur = 450;
                hauteur = 300;
                break;
            case 4:// Grandeur du rectangle  du boss 2
                longeur = hauteur = 450;
                break;
            case 5:// Grandeur du rectangle  du boss 3
                longeur = 500;
                hauteur = 400;
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");
        }
        rectangle.x = (int) x;
        rectangle.y = (int) y;
        rectangle.height = hauteur;
        rectangle.width = longeur;
        return rectangle;
    }
    private Rectangle rectangle = new Rectangle();

    @Override
        public void collision(Collisionable c) {
        if (c instanceof Projectile) {

            this.vie -= c.getDommage();
        }
        if (vie <= 0) {
            this.isDessinable = false;
            Main.gameValues.points += 100;
        }
    }

    @Override
        public int getDommage() {
        return 0;
    }
}
