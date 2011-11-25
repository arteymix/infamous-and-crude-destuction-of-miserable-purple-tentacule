package graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

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

    Canon canon1, canon2;
    boolean showHighscores = false;

    /**
     * Constructeur pour le canvas où le rendu est effectué.
     */
    MainCanvas() {
        super();

        setPreferredSize(new Dimension((int) Main.gameValues.canvasSize.x, (int) Main.gameValues.canvasSize.y));
    }
    /**
     * 
     */
    Activity activity = Activity.JEU;

    /**
     * 
     */
    enum Activity {

        JEU,
        HIGHSCORES;
    }
    private final Font FONT = new Font("Comic sans ms", Font.BOLD, 15);

    /**
     * 
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setFont(FONT);
        if (Main.gameValues.isDebugEnabled) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.gameValues.latency + " ms", 5, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.gameValues.tempsDuRendu + " ms", 5, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (Main.gameValues.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
            g.drawString("Nombre de composantes dessinable : " + Main.gameValues.composantesDessinables.size() + " composantes", 5, 60);
            g.drawString("Points : " + Main.gameValues.points + " points", 5, 75);
            g.drawString("Vies canon 1 : " + canon1.vie + " vies", 5, 90);
            g.drawString("Vies canon 2 : " + canon2.vie + " vies", 5, 105);
            g.drawRect(0, 0, (int) Main.gameValues.canvasSize.x - 1, (int) Main.gameValues.canvasSize.y - 1);
        } else {
            // Le background est dessiné ici.
            switch (Main.gameValues.level) {
                case 0:
                    g.drawImage(Main.imageBank.BACKGROUND_1, 0, 0, null);
                    break;
                case 1:
                    g.drawImage(Main.imageBank.BACKGROUND_1, 0, 0, null);
            }
        }
        switch (activity) {
            case JEU:
                // Le jeu!
                ArrayList<Canon> listeDeCanonDessinable = new ArrayList<Canon>();
                ArrayList<Dessinable> aEnlever = new ArrayList<Dessinable>();
                // Génération des nuages 
                Nuage.createNuage();
                // Génération des Ovnis    
                Ovni.createOvni();
                // END Génération des Ovnis
                for (int i = 0; i < Main.gameValues.composantesDessinables.size(); i++) {
                    Dessinable d = Main.gameValues.composantesDessinables.get(i);
                    // TODO Gestion des collisions ici
                    if (d instanceof Collisionable) {
                        for (int j = 0; j < Main.gameValues.composantesDessinables.size(); j++) {
                            Dessinable e;
                            // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                            if (((e = Main.gameValues.composantesDessinables.get(j)) instanceof Collisionable) && !d.equals(e)) {
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
                            if (Main.gameValues.isDebugEnabled) {
                                d.dessinerDeboguage(g);
                            } else {
                                d.dessiner(g);
                            }
                        } else {
                            listeDeCanonDessinable.add((Canon) d);
                        }
                    } else {
                        aEnlever.add(d);

                    }
                }
                for (Dessinable d : aEnlever) {
                    Main.gameValues.composantesDessinables.remove(d);
                }
                for (Canon c : listeDeCanonDessinable) {
                    if (Main.gameValues.isDebugEnabled) {
                        c.dessinerDeboguage(g);
                    } else {
                        c.dessiner(g);
                    }
                }
                break;
            case HIGHSCORES:
                g.drawString("LES HIGHSCORES AFFICHENT!", 400, 400);
        }
        Main.gameValues.paintDone = true;
    }
}
