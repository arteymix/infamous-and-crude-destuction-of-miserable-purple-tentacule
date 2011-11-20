package util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

/**
 * Interface implément
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public abstract class Dessinable implements Serializable {

    /**
     * Image de l'objet dessinable. Utilisé avec les références de la banque d'images.
     */
    public Image image;
    /**
     * Définit si l'objet est dessinable. Si il ne l'est pas, il doit être déréférencé.
     * En temps normaux, ce paramètre est vérifié chaque fois que les composantes doivent
     * être calculés.     * 
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
