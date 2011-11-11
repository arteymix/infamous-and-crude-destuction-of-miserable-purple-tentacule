package graphique;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import javax.swing.JComponent;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Traductions;

/**
 * Classe contenant le canvas principal oû les dessins seront effectués.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class MainCanvas extends JComponent implements Serializable {

    public int points = 0;
    public boolean showHighscores = false;

    @Override
    public void paint(Graphics g) {
        // Le jeu!
        if (InterfaceGraphique.isDebugEnabled | true) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.latency + " ms", 0, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.tempsDuRendu + " ms", 0, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (InterfaceGraphique.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 0, 45);
            g.drawString("Nombre de composantes dessinable : " + InterfaceGraphique.composantesDessinables.size() + " composantes", 0, 60);
            g.drawString("Points : " + points + " points", 0, 75);
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
                if (InterfaceGraphique.isDebugEnabled) {
                    d.dessinerDeboguage(g);
                } else {
                    d.dessiner(g);
                }
            } else {
                InterfaceGraphique.composantesDessinables.remove(d);
            }
        }
        // On montre les highscores on top of everything!
        if (showHighscores) {
            g.drawString("LES HIGHSCORES AFFICHENT ICI!", 400, 400);
        }
    }
}
