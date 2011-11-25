package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import main.Main;
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
     * Cette variable est particulièrement utile lorsque le joueur joue en mode
     * solo, ainsi les projectiles auto-guidées ne se dirigent pas vers le
     * deuxième canon.
     */
    boolean isCanon2ValidTarget = false;
    private Vecteur A, B, C, D, E, F, G, H;
    // La variable position devrait être changée par les points E F G et H.
    private Vecteur position;
    // TODO Algorithme de draw pour positionner le canon sur la partie la plus basse de l'écran.
    /**
     * 
     */
    public int vie;
    private final double NUMERO_DU_CANON;
    private boolean peutTirer = true;
    private Image imageSubCanon;

    /**
     * Constructeur pour l'objet de canon.
     * @param numeroDuCanon est le numéro de canon. Deux valeurs sont possibles,
     * 0 qui définit le canon 1 et 1 qui définit le canon 2.
     */
    public Canon(int numeroDuCanon) {
        switch (numeroDuCanon) {
            case 0:
                image = Main.imageBank.CANON_0;
                imageSubCanon = Main.imageBank.SUBCANON1;
                position = new Vecteur(0, 699);
                break;
            case 1:
                image = Main.imageBank.CANON_1;
                imageSubCanon = Main.imageBank.SUBCANON2;
                position = new Vecteur(689, 699);
                break;
        }
        NUMERO_DU_CANON = numeroDuCanon;
        A = piedDeCanon().additionAffine(new Vecteur(15, -70));
        B = piedDeCanon().additionAffine(new Vecteur(-15, -70));
        C = piedDeCanon().additionAffine(new Vecteur(-15, +0));
        D = piedDeCanon().additionAffine(new Vecteur(15, +0));
        vie = Main.gameValues.canon.VIE_INIT_CANON;
    }

    /**
     * Retourne le vecteur position du pied de canon. Les missiles prennent
     * origine à cette position.
     * @return ce vecteur.
     */
    public Vecteur piedDeCanon() {
        return new Vecteur(position.x + Main.gameValues.canon.width / 2, position.y + Main.gameValues.canon.heigh / 4);
    }

    /**
     * 
     * @param e
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
        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), -Main.gameValues.canon.ANGLE_INCREMENT_CANON);
    }

    private void moveCanonDroite() {
        this.A.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.B.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.C.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
        this.D.rotation(new Vecteur(this.piedDeCanon().x, this.piedDeCanon().y), Main.gameValues.canon.ANGLE_INCREMENT_CANON);
    }

    /**
     * Déplace le canon vers la gauche de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveGauche() {
        if (this.position.x > 0) {
            A.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            B.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            C.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            D.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            position.x -= Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Déplace le canon vers la droite de MOVEMENT_INCREMENT_CANON pixels.
     */
    private void moveDroite() {
        if (this.position.x + Main.gameValues.canon.width + 1 < Main.gameValues.CANVAS_SIZE.x) {
            A.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            B.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            C.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            D.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
            position.x += Main.gameValues.canon.MOVEMENT_INCREMENT_CANON;
        }
    }

    /**
     * Effectue un tir!
     */
    private void tirer() {
        // TODO Gérer les tirs fou!
        if (peutTirer) {
            Main.gameValues.composantesDessinables.add(new Projectile(piedDeCanon(), new Vecteur((D.x - A.x) / 2, (D.y - A.y) / 2), 0));
            peutTirer = false;
            // Le Thread sert à attendre un certain temps avant d'effectuer un autre tir.
            (new Thread("Thread pour le temps d'attente entre chaque tir de canon.") {

                @Override
                public void run() {
                    try {
                        Thread.sleep(Main.gameValues.canon.LATENCE_DU_TIR);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    } finally {
                        peutTirer = true;
                    }
                }
            }).start();
        }
    }

    @Override
    public void dessiner(Graphics g) {
        if (!isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        ////////////
        /*
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = new AffineTransform();
        //rotate the image 
        affineTransform.translate(piedDeCanon().x + 20, piedDeCanon().y);
        affineTransform.rotate(Math.toRadians(180));
        //draw the image using the AffineTransform 
        g2d.drawImage(imageSubCanon, affineTransform, null);
        ///*/
        g.drawImage(imageSubCanon, xPoints[1], yPoints[1], null);
        g.drawImage(image, (int) position.x, (int) position.y, null);
        g.setColor(Color.RED);
        g.fillRect((int) position.x, (int) position.y, vie / 5, 10);
        g.setColor(Color.BLACK);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (!isCanon2ValidTarget && this.NUMERO_DU_CANON == 1) {
            return;
        }
        int[] xPoints = {(int) A.x, (int) B.x, (int) C.x, (int) D.x};
        int[] yPoints = {(int) A.y, (int) B.y, (int) C.y, (int) D.y};
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawRect((int) (position.x), (int) (position.y), (int) Main.gameValues.canon.width, (int) Main.gameValues.canon.heigh);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) position.x, (int) position.y, (int) Main.gameValues.canon.width, (int) Main.gameValues.canon.heigh);
    }

    @Override
    public void collision(Collisionable c) {
        if (!(c instanceof Canon) && !(c instanceof Projectile)) {
            this.vie -= c.getDommage();
            System.out.println(this + " reçoit collision de " + c);
            System.out.println(vie);
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
