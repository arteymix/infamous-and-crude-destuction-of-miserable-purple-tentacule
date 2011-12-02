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
package graphique.window;

import graphique.event.DecorFlottant;
import graphique.component.Ovni;
import graphique.component.Canon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import main.Main;

import util.Collisionable;
import util.Dessinable;
import util.Traductions;

/**
 * Classe contenant le canvas principal où les dessins seront effectués.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class MainCanvas extends JComponent {

    /**
     * Constructeur pour le canvas où le rendu est effectué.
     */
    MainCanvas() {
        super();
        setPreferredSize(new Dimension((int) Main.canvasSize.x, (int) Main.canvasSize.y));
    }
    /**
     * Objet contenant l'activité en cours.
     */
    Activity activity = Activity.JEU;

    /**
     * Enum contenant les activités possibles (faire le rendu du jeu, afficher
     * les highscores, probablement un menu, etc...).
     */
    enum Activity {

        JEU,
        HIGHSCORES;
    }
    private final Font FONT = new Font("Comic sans ms", Font.BOLD, 15);

    /**
     * Peint le JPanel avec le rendu du jeu.
     * @param g est l'objet Graphics du JPanel.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setFont(FONT);
        if (Main.isDebugEnabled) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.latency + " ms", 5, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.tempsDuRendu + " ms", 5, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (Main.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
            g.drawString("Nombre de composantes dessinable" + " : " + Main.composantesDessinables.size() + " composantes", 5, 60);
            g.drawString("Points : " + Main.points + " points", 5, 75);
            g.drawString("Vies canon 1 : " + Main.canon1.getVie() + " vies", 5, 90);
            g.drawString("Vies canon 2 : " + Main.canon2.getVie() + " vies", 5, 105);
            g.drawString("Temps joué : " + Main.timerSeconds, 5, 120);
            g.drawRect(0, 0, (int) Main.canvasSize.x - 1, (int) Main.canvasSize.y - 1);

        } else {
            // Le background est dessiné ici.
            g.drawImage(Main.imageBank.background, 0, 0, 1024, 768, null);

        }
        switch (activity) {
            case JEU:
                /////////////////////////////////////////////////////////////////
                // On incrémente le timer de Main.gameValues.latency millisecondes.
                Main.timerSeconds += Main.latency;
                
                ///////////////////////////////////////////////////////

                // Le jeu!
                ArrayList<Canon> listeDeCanonDessinable = new ArrayList<Canon>();
                ArrayList<Dessinable> aEnlever = new ArrayList<Dessinable>();
                // Génération des nuages 
                DecorFlottant.createNuage();
                // Génération des Ovnis    
                Ovni.createOvni();
                // END Génération des Ovnis
                for (int i = 0; i < Main.composantesDessinables.size(); i++) {
                    Dessinable d = Main.composantesDessinables.get(i);
                    /* Sous-boucle pour les collisions, question de tenter toutes
                     * les collisions possibles. 
                     */
                    if (d instanceof Collisionable) {
                        for (int j = 0; j < Main.composantesDessinables.size(); j++) {
                            Dessinable e;
                            // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                            if (((e = Main.composantesDessinables.get(j)) instanceof Collisionable) && d != e) {
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
                            if (Main.isDebugEnabled) {
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
                //<editor-fold defaultstate="collapsed" desc="Boucle pour supprimer les objets non dessinable. Autrement, il arrive que certains objets ne soient pas dessinés, car la liste de composantes dessinables est dynamique.">
                for (Dessinable d : aEnlever) {
                    Main.composantesDessinables.remove(d);
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Les canons sont dessinés à la toute fin (ici), afin de s'assurer qu'ils apparaissent par dessus les missiles.">
                for (Canon c : listeDeCanonDessinable) {
                    if (Main.isDebugEnabled) {
                        c.dessinerDeboguage(g);
                    } else {
                        c.dessiner(g);
                    }
                }
                //</editor-fold>

                /////////
                // L'interface utilisateur est dessnié ici
                if (!Main.isDebugEnabled) {
                    drawUserInterface(g);
                }
                /////////////
                break;
            case HIGHSCORES:
                /* En cas où c'est le rendu des meilleurs scores qui est activé,
                 * on itère le dictionnaire dans Main.highscores.
                 */
                int x = 100;
                if (Main.isDebugEnabled) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                int positionInit = 385;
                for (String s : Main.highscore.getScores()) {
                    g.drawString(s, x, positionInit += 15);
                }

                g.drawString("Noob " + (Main.highscore.NOOB_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("Own " + (Main.highscore.OWN_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("Pwn " + (Main.highscore.PWN_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("Nuke " + (Main.highscore.NUKE_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("Pro " + (Main.highscore.PRO_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("1337 " + (Main.highscore.LEET_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                g.drawString("Bazinga! " + (Main.highscore.BAZINGA_OBTAINED ? "complété!" : "en cours..."), x, positionInit += 15);
                if (Main.isDebugEnabled) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
        }
        Main.paintDone = true;
    }
    /**
     * Pourcentage de vie pour que les vies soient affichés en rouge.
     */
    /**
     * Pourcentage de vie pour que les vies soient affichés en jaune.
     */
    private static final double PERCENTAGE_RED_LIFE = 0.25,
            PERCENTAGE_YELLOW_LIFE = 0.5;

    private void drawUserInterface(Graphics g) {
        // Vie du canon 1
        if (Canon.isCanon2ValidTarget) {
            g.setColor(Color.GREEN);
            if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_RED_LIFE) {
                g.setColor(Color.RED);
            } else if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_YELLOW_LIFE) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(0, (int) Main.canvasSize.y - 15, (int) (((double) Main.canon1.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.canvasSize.x), 15);
            if ((double) Main.canon2.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_RED_LIFE) {
                g.setColor(Color.RED);
            } else if ((double) Main.canon2.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_YELLOW_LIFE) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect((int) (Main.canvasSize.x) - (int) (((double) Main.canon2.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.canvasSize.x), (int) Main.canvasSize.y - 15, (int) (((double) Main.canon2.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.canvasSize.x), 15);
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(0, (int) Main.canvasSize.y - 15, (int) (((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON) * Main.canvasSize.x), 15);
            g.setColor(Color.BLACK);
        }
    }
}
