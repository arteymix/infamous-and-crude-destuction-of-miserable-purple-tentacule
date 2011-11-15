package graphique;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Classe pour le Canon. Les canons sont instanciés au début du programme et non
 * à la volée. Il y a deux canons possibles.
 * @author Nafie Hamrani && Guillaume Poirier-Morency
 */
public final class Canon extends Dessinable implements Collisionable, Serializable {

    /**
     * Variable définissant si le canon 2 est une cible valide pour un projectile ennemi.
     */
    boolean isCanon2ValidTarget = false;
    private Vecteur A, B, C, D, E, F, G, H;
    // La variable position devrait être changée par les points E F G et H.
    private Vecteur position;
    // TODO Algorithme de draw pour positionner le canon sur la partie la plus basse de l'écran.
    private double heigh = 100, width = 100;
    private int vie;
    private final double NUMERO_DU_CANON;
    private static final int VIE_INIT_CANON = 10;
    private static final double MOVEMENT_INCREMENT_CANON = 1.0;
    private static final double ANGLE_INCREMENT_CANON = Math.PI / 600.0;

    public Canon(Vecteur v, int numeroDuCanon) {
        position = v;
        switch (numeroDuCanon) {
            case 0:
                //this.image = Main.imageBank.canon0;
                break;
            case 1:
                //this.image = Main.imageBank.canon1;
                break;
        }
        NUMERO_DU_CANON = numeroDuCanon;
        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = VIE_INIT_CANON;
    }

    /**
     * 
     * @return 
     */
    public Vecteur piedDeCanon() {
        return new Vecteur(position.x + width / 2, position.y + heigh / 4);
    }

    /**
     * 
     * @param e
     * @param eventType 
     */
    public void gererEvenementDuClavier(Integer e) {
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
        } else if (NUMERO_DU_CANON == 1 && isCanon2ValidTarget) {

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

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -ANGLE_INCREMENT_CANON);
    }

    private void moveCanonDroite() {

        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), ANGLE_INCREMENT_CANON);
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
            A.x -= MOVEMENT_INCREMENT_CANON;
            B.x -= MOVEMENT_INCREMENT_CANON;
            C.x -= MOVEMENT_INCREMENT_CANON;
            D.x -= MOVEMENT_INCREMENT_CANON;
            position.x -= MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * 
     */
    private void moveDroite() {
        if (this.position.x + width + 1 < MainCanvas.canvasSize.x) {
            A.x += MOVEMENT_INCREMENT_CANON;
            B.x += MOVEMENT_INCREMENT_CANON;
            C.x += MOVEMENT_INCREMENT_CANON;
            D.x += MOVEMENT_INCREMENT_CANON;
            position.x += MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Effectue un tir!
     */
    private void tirer() {


        InterfaceGraphique.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((D.x - A.x) / 2, (D.y - A.y) / 2), 0));

    }

    @Override
    public void dessiner(Graphics g) {
        if (!isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }

        g.drawImage(image, (int) position.x, (int) position.y, this);

    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (!isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }

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
        if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            this.vie -= c.getDommage();

            System.out.println(this + " reçoit collision de " + c);
        }
        if (vie < 0) {
            this.isDessinable = false;
            System.out.println(this + " a été détruit par " + c);
        }
    }

    @Override
    public int getDommage() {
        // Entrer en collision avec un canon ne cause pas de dommages, mais le projectile ennemi disparaît quand même.
        return 0;
    }
}
