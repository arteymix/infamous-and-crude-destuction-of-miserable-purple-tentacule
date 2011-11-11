package graphique;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * 
 * @author Nafie Hamrani && Guillaume Poirier-Morency
 */
public final class Canon extends Dessinable implements Collisionable {

    /**
     * Variable définissant si le canon est une cible valide pour un projectile ennemi.
     */
    public boolean isValidTarget = true;
    private Vecteur A, B, C, D, E, F, G, H;
    // La variable position devrait être changée par les points E F G et H.
    private Vecteur position;
    // TODO Algorithme de draw pour positionner le canon sur la partie la plus basse de l'écran.
    private double heigh = 100, width = 100;
    private int vie;    
    private final double NUMERO_DU_CANON;
    private Vecteur teteDeCanon;
    private static final double MOVEMENT_INCREMENT = 3.0;
    private static final double ANGLE_INCREMENT = Math.PI / 100.0;

    public Canon(Vecteur v, int numeroDuCanon, Image img) {
        position = v;
        this.image = img;
        teteDeCanon = piedDeCanon().additionAffine(new Vecteur(5, -20));

        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = 10;

        //image = Main.ib.canon0;
        NUMERO_DU_CANON = numeroDuCanon;
    }

    public Vecteur piedDeCanon() {
        return new Vecteur(position.x + width / 2, position.y + 20);
    }

    /**
     * 
     * @param e
     * @param eventType 
     */
    public void gererEvenementDuClavier(Integer e, int eventType) {
        if (NUMERO_DU_CANON == 0) {
            switch (e) {
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

            switch (e) {
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
                    break;
                case KeyEvent.VK_P:
                    moveCanonDroite();
                    break;
            }
        }
    }

    /**
     * 
     */
    private void moveCanonGauche() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT);
    }

    private void moveCanonDroite() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT);
    }

    public void draw(Graphics g) {
    }

    /**
     * 
     * @param s 
     */
    public void setImage(String s) {
        image = Toolkit.getDefaultToolkit().getImage(s);
    }

    private void moveGauche() {
        if (this.position.x > 0) {
            A.x -= MOVEMENT_INCREMENT;
            B.x -= MOVEMENT_INCREMENT;
            C.x -= MOVEMENT_INCREMENT;
            D.x -= MOVEMENT_INCREMENT;
            position.x -= MOVEMENT_INCREMENT;
        }
    }

    /**
     * 
     */
    private void moveDroite() {
        if (this.position.x + width < InterfaceGraphique.canvasSize.x) {
            A.x += MOVEMENT_INCREMENT;
            B.x += MOVEMENT_INCREMENT;
            C.x += MOVEMENT_INCREMENT;
            D.x += MOVEMENT_INCREMENT;
            position.x += MOVEMENT_INCREMENT;
        }
    }

    /**
     * Effectue un tir!
     */
    private void tirer() {
        InterfaceGraphique.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((D.x - A.x) / 2, (D.y - A.y) / 2)));
    }

    @Override
    public void dessiner(Graphics g) {
        g.drawImage(image, (int) position.x, (int) position.y, null);

    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (position.x), (int) (position.y), (int) width, (int) heigh);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.y, (int) width, (int) this.heigh);
    }

    @Override
    public void collision(Collisionable c) {
        System.out.println("Kolizion!");
    }
}
