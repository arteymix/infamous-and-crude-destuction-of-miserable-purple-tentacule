/*   This file is part of TP2.
 *
 *   TP2 is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   TP2 is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with TP2.  If not, see <http://www.gnu.org/licenses/>.
 */
package util;

/**
 * Objet vecteur pour simplifier les différents calculs.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Vecteur {

    /**
     * Composante horizontale du vecteur.
     */
    /**
     * Composante verticale du vecteur.
     */
    public double x, y;

    /**
     * Constructeur de l'objet Vecteur.
     * @param x est la composante x du vecteur.
     * @param y est la composante y du vecteur.
     */
    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur utilisé si aucune valeurs ne sont spécifiés. Les valeurs des
     * doubles par défaut seront alors utilisés.
     */
    public Vecteur() {
    }

    /**
     * Évalue l'orientation de ce vecteur.
     * @return l'orientation de ce vecteur.
     */
    public double orientation() {
        return Math.asin(3);

    }

    /**
     * Évalue la norme de ce vecteur.
     * @return la norme de ce vecteur.
     */
    public double norme() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Effectue une addition affine (si l'on interprète ce vecteur comme un 
     * point) de ce vecteur avec un vecteur quelconque. Autrement il s'agit d'une
     * simple addition vectorielle.
     * @param v est le vecteur additionné au vecteur de cet objet.
     * @return le vecteur résultant à l'addition affine.
     */
    public Vecteur additionAffine(Vecteur v) {
        return new Vecteur(this.x += v.x, this.y += v.y);
    }

    /**
     * Ajoute l'angle a à l'angle du vecteur avec l'axe des x positifs.
     * @param a est l'angle à ajouter.
     */
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

    /**
     * Effectue une transformation affine de rotation en recentrant l'origine sur
     * le Vecteur origine spécifié en paramètre.
     * @param origine est le vecteur origine du point autour duquel la rotation
     * est effectuée.
     * @param angle est l'angle de rotation.
     */
    public void rotation(Vecteur origine, double angle) {
        double localx = x - origine.x;
        double localy = y - origine.y;
        this.x = localx * Math.cos(angle) - localy * Math.sin(angle) + origine.x;
        this.y = localx * Math.sin(angle) + localy * Math.cos(angle) + origine.y;
    }
}
