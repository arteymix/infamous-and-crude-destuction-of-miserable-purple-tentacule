package util;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Vecteur {

    /**
     * Composante horizontale du vecteur.
     */
    /**
     * Composante verticale du vecteur.
     */
    public double x, y;

    /**
     * 
     * @param x
     * @param y 
     */
    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Évalue l'orientation de ce vecteur.
     * @return l'orientation de ce vecteur.
     */
    public double orientation() {
        return Math.tan(y / x);

    }

    /**
     * Évalue la norme de ce vecteur.
     * @return la norme de ce vecteur.
     */
    public double norme() {
        return Math.sqrt(x * x + y * y);
    }

    public Vecteur additionAffine(Vecteur v) {
        return new Vecteur(this.x += v.x, this.y += v.y);
    }

    public void ajouterAngle(double a) {
        double nouvelangle = orientation() + a;
        y = norme() * Math.sin(nouvelangle);
        x = norme() * Math.cos(nouvelangle);

    }

    /**
     * Effectue le produit scalaire de ce verteur avec le vecteur v.
     * @param v est un vecteur.
     * @return la valeur du produit scalaire.
     */
    public double produitScalaire(Vecteur v) {
        return this.x * v.x + this.y * v.y;
    }

    public void rotation(Vecteur origine, double angle) {
        double localx = x - origine.x;
        double localy = y - origine.y;
        this.x = localx * Math.cos(angle) - localy * Math.sin(angle) + origine.x;
        this.y = localx * Math.sin(angle) + localy * Math.cos(angle) + origine.y;
    }
}
