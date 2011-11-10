package util;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public abstract class Dessinable {

    /**
     * Image de l'objet dessinable. Utilisé avec les références de la banque d'images.
     */
    public Image image;
    /**
     *
     */
    public boolean mustBeDestroyed = false;
    /**
     * Définit si l'objet est dessinable. Si il ne l'est pas, il doit être déréférencé.
     * En temps normaux, ce paramètre est vérifié chaque fois que les composantes doivent
     * être calculés.
     * @return true si il est dessinable, false si il doit être détruit.
     */
    public boolean isDessinable = true;

    /**
     * Dessiner le composant sur un Graphics.
     * @param g est le Graphics à utiliser pour effectuer le dessin.
     */
    public abstract void dessiner(Graphics g);

    /**
     * Dessiner le composant sur un Graphics en mode de déboguage.
     * @param g est le Graphics à utiliser pour effectuer le dessin.
     */
    public abstract void dessinerDeboguage(Graphics g);
}
