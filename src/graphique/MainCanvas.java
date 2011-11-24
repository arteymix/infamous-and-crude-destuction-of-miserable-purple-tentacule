package graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

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
    /**
     * La variable points contient les points du/des joueur/s.
     */
    public static int points = 0;
    /**
     * Cette variable définit si les highscores doivent être affiché.
     */
    public static int level = 0;
    boolean showHighscores = false;
    /**
     * Ce vecteur est le vecteur dimension du canvas ou les composants et
     * graphics sont dessinés.
     */
    static final Vecteur CANVAS_SIZE = new Vecteur(800, 800);

    /**
     * Constructeur pour le canvas où le rendu est effectué.
     */
    MainCanvas() {
        super();
        
        setPreferredSize(new Dimension((int) CANVAS_SIZE.x, (int) CANVAS_SIZE.y));
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
private final Font FONT = new Font("Comic sans ms",Font.BOLD,15);
    @Override
    public void paintComponent(Graphics g) {
        g.setFont(FONT);
        if (InterfaceGraphique.isDebugEnabled) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
                    // On affiche les variables seulement en mode de débogage...
                    g.drawString(Traductions.get("debug.latence") + " : " + InterfaceGraphique.latency + " ms", 5, 15);
                    g.drawString(Traductions.get("debug.tempsdurendu") + " : " + InterfaceGraphique.tempsDuRendu + " ms", 5, 30);
                    g.drawString(Traductions.get("debug.modedebogage") + " : " + (InterfaceGraphique.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
                    g.drawString("Nombre de composantes dessinable : " + InterfaceGraphique.composantesDessinables.size() + " composantes", 5, 60);
                    g.drawString("Points : " + points + " points", 5, 75);
                    g.drawString("Vies canon 1 : " + canon1.vie + " vies", 5, 90);
                    g.drawString("Vies canon 2 : " + canon2.vie + " vies", 5, 105);
                    g.drawRect(0, 0, (int) CANVAS_SIZE.x - 1, (int) CANVAS_SIZE.y - 1);
                } else {
                    // Le background est dessiné ici.
                    switch (level) {
                        case 0:
                            g.drawImage(InterfaceGraphique.imageBank.BACKGROUND_1, 0, 0, null);
                            break;
                        case 1:
                            g.drawImage(InterfaceGraphique.imageBank.BACKGROUND_1, 0, 0, null);
                    }
                }
        switch (activity) {
            case JEU:
                // Le jeu!
                ArrayList<Canon> listeDeCanonDessinable = new ArrayList<Canon>();                
                // Génération des nuages 
                Nuage.createNuage();
                // Génération des Ovnis    
                Ovni.createOvni();
                // END Génération des Ovnis
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
                break;
            case HIGHSCORES:                
                g.drawString("LES HIGHSCORES AFFICHENT!",400, 400);                
        }
        InterfaceGraphique.paintDone = true;
    }
}
