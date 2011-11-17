package graphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import main.Main;

import util.Collisionable;
import util.Dessinable;
import util.Traductions;
import util.Vecteur;

/**
 * Classe contenant le canvas principal oû les dessins seront effectués.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class MainCanvas extends JComponent implements Serializable {

    public int points = 0;
    boolean showHighscores = false;
    static final Vecteur canvasSize = new Vecteur(800, 800);

    public MainCanvas() {
        super();
        setPreferredSize(new Dimension((int) canvasSize.x, (int) canvasSize.y));

    }

    @Override
    public void paint(Graphics g) {
        // Le jeu!
        ArrayList<Canon> listeDeCanonDessinable = new ArrayList<Canon>();
        if (InterfaceGraphique.isDebugEnabled) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.latency + " ms", 5, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.tempsDuRendu + " ms", 5, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (InterfaceGraphique.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
            g.drawString("Nombre de composantes dessinable : " + InterfaceGraphique.composantesDessinables.size() + " composantes", 5, 60);
            g.drawString("Points : " + points + " points", 5, 75);
            g.drawRect(0, 0, (int) canvasSize.x - 1, (int) canvasSize.y - 1);
        } else {
            g.drawImage(Main.imageBank.BACKGROUND_1, 0, 0, null);
        }
        // Génération des nuages            
        if ((new Random()).nextInt(Nuage.PROBABILITE_APPARITION_NUAGE) == 1) {
            InterfaceGraphique.composantesDessinables.add(new Nuage());
        }
        for (int i = 0; i < InterfaceGraphique.composantesDessinables.size(); i++) {
            Dessinable d = InterfaceGraphique.composantesDessinables.get(i);
            // TODO Gestion des collisions ici
            if (d instanceof Collisionable) {
                for (int j = 0; j < InterfaceGraphique.composantesDessinables.size(); j++) {
                    Dessinable e;
                    // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                    if (((e = InterfaceGraphique.composantesDessinables.get(j)) instanceof Collisionable) && !d.equals(e)) {
                        if (((Collisionable) d).getRectangle().intersects(((Collisionable) e).getRectangle())) {
                            // On provoque une collision entre chacun.
                            ((Collisionable) d).collision((Collisionable) e);
                            ((Collisionable) e).collision((Collisionable) d);
                        }
                    }
                }
            }
            if (d.isDessinable) {
                if (!(d instanceof Canon)) {
                    if (InterfaceGraphique.isDebugEnabled) {
                        d.dessinerDeboguage(g);
                    } else {
                        d.dessiner(g);
                    }
                } else {
                    listeDeCanonDessinable.add((Canon) d);
                }
            } else {
                InterfaceGraphique.composantesDessinables.remove(d);
            }
        }
        for (Canon c : listeDeCanonDessinable) {
            if (InterfaceGraphique.isDebugEnabled) {
                c.dessinerDeboguage(g);
            } else {
                c.dessiner(g);
            }

        }

        // On montre les highscores on top of everything!
        if (showHighscores) {
            g.drawString("LES HIGHSCORES AFFICHENT ICI!", 400, 400);
        }
        Main.paintDone = true;
    }
}
