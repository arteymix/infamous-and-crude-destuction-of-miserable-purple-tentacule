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

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

/**
 * Classe abstraite des objets Dessinable. Ces objets se dessinent sur le panneau
 * principal. Ce que l'on nomme rendu est la somme du rendu de tous les objets
 * dessinables.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public abstract class Dessinable implements Serializable {

    /**
     * Image de l'objet dessinable. Utilisé avec les références de la banque d'images.
     */
    public Image image0;
    /**
     * Image secondaire pour l'objet dessinable. Il peut servir entre autres au
     * canon qui est composé de deux images.
     */
    public Image image1;
    /**
     * Définit si l'objet est dessinable. Si il ne l'est pas, il doit être déréférencé.
     * En temps normaux, ce paramètre est vérifié chaque fois que les composantes doivent
     * être calculés.
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
