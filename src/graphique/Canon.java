package graphique;

import content.ImageBank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * 
 * @author Nafie Hamrani
 */
public final class Canon extends Dessinable implements Collisionable {

    private Vecteur A, B, C, D, E, F, G, H;
    double x, y, xDirection;
    double heigh = 100, width = 100;
    int vie;
    Color col;
    private final double NUMERO_DU_CANON;
    private Vecteur teteDeCanon;

    public Canon(Vecteur v, int numeroDuCanon, Image img) {
        teteDeCanon = piedDeCanon().additionAffine(new Vecteur(5, -20));
        this.x = v.x;
        this.y = v.y;

        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));

        vie = 10;

        image = Main.ib.canon0;
        NUMERO_DU_CANON = numeroDuCanon;
    }

    public Vecteur piedDeCanon() {

        return new Vecteur(x + width / 2, y + 20);


    }

    /**
     * 
     * @param e
     * @param eventType 
     */
    public void gererEvenementDuClavier(KeyEvent e, int eventType) {
        if (NUMERO_DU_CANON == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    moveGauche();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveDroite();
                    break;
                case KeyEvent.VK_UP:
                    this.tirer();
                    break;
                case KeyEvent.VK_NUMPAD2:
                    moveCanonGauche();
                    // 
                    break;
                case KeyEvent.VK_NUMPAD3:
                    moveCanonDroite();
                    break;
            }
            // On gere l'evenement

        } else if (NUMERO_DU_CANON == 1) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    moveGauche();
                    break;
                case KeyEvent.VK_D:
                    moveDroite();
                    break;
                case KeyEvent.VK_W:
                    this.tirer();
                    break;
                case KeyEvent.VK_O:
                    moveCanonGauche();
                    // 
                    break;
                case KeyEvent.VK_P:
                    moveCanonDroite();
                    break;
            }
        }
    }

    private void moveCanonGauche() {
        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Math.PI / 100);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Math.PI / 100);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Math.PI / 100);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Math.PI / 100);
        System.out.println(this.piedDeCanon().y);
    }

    private void moveCanonDroite() {
        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Math.PI / 100);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Math.PI / 100);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Math.PI / 100);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Math.PI / 100);
    }

    public void draw(Graphics g) {
    }

    public void setImage(String s) {
        image = Toolkit.getDefaultToolkit().getImage(s);
    }

    public void moveGauche() {
        A.x -= 3;
        B.x -= 3;
        C.x -= 3;
        D.x -= 3;
        x -= 3;



    }

    public void moveDroite() {
        A.x += 3;
        B.x += 3;
        C.x += 3;
        D.x += 3;
        x += 3;


    }

    /**
     * Effectue un tir!
     */
    private void tirer() {
        InterfaceGraphique.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur(C.x - A.x, C.y - A.y)));
    }

    @Override
    public void dessiner(Graphics g) {

        g.drawImage(image, (int) x, (int) y, null);

    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (x), (int) (y), 100, 100);

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, (int) width, (int) this.heigh);
    }

    @Override
    public void collision(Collisionable c) {
        // On ne gere pas la collision avec le canon...
    }
}
