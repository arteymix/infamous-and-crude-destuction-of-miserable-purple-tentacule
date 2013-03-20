/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import javax.swing.JComponent;
import util.Dessinable;

/**
 *
 * @author guillaume
 */
public class Surface extends JComponent {

    /**
     * Éléments à dessiner
     */
    private HashSet<Dessinable> mDessinables;

    @Override
    public void paintComponent(Graphics g) {

        for (Dessinable d : mDessinables) {
            d.dessiner((Graphics2D) g);
        }

    }

    public void addDessinable(Dessinable d) {
        mDessinables.add(d);
        this.invalidate();
    }

    public void removeDessinable(Dessinable d) {
        mDessinables.remove(d);
        this.invalidate();
    }
}
