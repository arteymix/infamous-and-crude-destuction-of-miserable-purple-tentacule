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

import content.KeySetting;
import graphique.window.MainCanvas.Activity;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.Serializable;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import main.Main;
import util.KeyBoardListener;
import util.Traductions;

/**
 * Fichier de classe pour l'interface graphique.
 * @author Guillaume Poirier-Morency
 */
public final class InterfaceGraphique extends JFrame implements Serializable, Runnable {

    private JMenuBar jmb = new JMenuBar();
    private JMenu menuFichier = new JMenu(Traductions.get("menu.fichier")),
            menuLangue = new JMenu(Traductions.get("menu.langue")),
            menuAide = new JMenu(Traductions.get("menu.aide"));
    private JMenuItem mitemQuitter = new JMenuItem(Traductions.get("menu.quitter")),
            mitemNouvellePartie = new JMenuItem(Traductions.get("menu.nouvelle"));
    private JCheckBoxMenuItem cbmitemDebug = new JCheckBoxMenuItem(Traductions.get("menu.modedebogage"));
    private JCheckBoxMenuItem cbmitemNombreDeCanons = new JCheckBoxMenuItem(Traductions.get("menu.deuxcanons"));
    private ButtonGroup bg = new ButtonGroup();
    /**
     * 
     */
    public MainCanvas mainCanvas = new MainCanvas();
    public KeyBoardListener keyBoardListener;

    private void configurerMenus() {
        mitemQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir quitter?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    Main.close(Main.CODE_DE_SORTIE_OK);
                }

            }
        });
        cbmitemDebug.setState(Main.gameValues.isDebugEnabled);
        cbmitemDebug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.gameValues.isDebugEnabled = cbmitemDebug.getState();
            }
        });
        cbmitemNombreDeCanons.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.gameValues.canon.isCanon2ValidTarget = cbmitemNombreDeCanons.getState();

            }
        });
        mitemNouvellePartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.restart();
            }
        });

        menuFichier.add(mitemNouvellePartie);
        menuFichier.addSeparator();
        menuFichier.add(cbmitemDebug);
        menuFichier.add(cbmitemNombreDeCanons);
        menuFichier.add(menuLangue);
        menuFichier.addSeparator();
        menuFichier.add(mitemQuitter);
        JRadioButtonMenuItem fr = new JRadioButtonMenuItem(Traductions.get("menu.francais"));
        fr.setSelected(true);
        JRadioButtonMenuItem en = new JRadioButtonMenuItem(Traductions.get("menu.anglais"));
        fr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Traductions.setLangue("fr");
            }
        });
        en.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Traductions.setLangue("en");

            }
        });
        bg.add(en);
        bg.add(fr);
        menuLangue.add(fr);
        menuLangue.add(en);
        menuAide.add(new JMenuItem(Traductions.get("menu.item.aide")));
        menuAide.addSeparator();
        menuAide.add(new JMenuItem(Traductions.get("menu.item.tableau")));
        menuAide.add(new JMenuItem(Traductions.get("menu.item.trophe")));
        menuAide.addSeparator();
        menuAide.add(new JMenuItem(Traductions.get("menu.item.apropos")));
        jmb.add(menuFichier);
        jmb.add(menuAide);
        setJMenuBar(jmb);
    }

    /**
     * Constructeur pour générer l'interface graphique.
     */
    public InterfaceGraphique() {

        configurerMenus();
        /* Le KeyListener du canon est implémenté dans KeyBoardListener afin
         * de rendre possible le multitouch. 
         */
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {

                    case KeySetting.PAUSE:
                        Main.gameValues.isPaused = !Main.gameValues.isPaused;

                        break;
                    case KeySetting.SHOW_HIGHSCORES:
                        // On inverse la valeur du show highscores...
                        if (mainCanvas.activity.equals(Activity.JEU)) {
                            mainCanvas.activity = Activity.HIGHSCORES;
                            Main.gameValues.showHighscores = true;
                        } else {
                            mainCanvas.activity = Activity.JEU;
                            Main.gameValues.showHighscores = false;
                        }

                        break;
                    case KeySetting.QUIT:

                        if (JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir quitter?", "", JOptionPane.YES_NO_OPTION) == 0) {
                            Main.close(0);
                        }
                    default:
                        if (!keyBoardListener.contains(arg0.getKeyCode())) {
                            keyBoardListener.add(arg0.getKeyCode());
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                keyBoardListener.remove((Integer) arg0.getKeyCode());
            }
        });
        // On rajoute le canon 1 par défaut.
        setIconImage(Main.imageBank.icon);
        add(mainCanvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle(Traductions.get("title"));
    }

    /**
     * 
     */
    @Override
    public void run() {
        Main.gameValues.timerSeconds++;
        while (Main.gameValues.isRunning) {

            Main.gameValues.time = System.currentTimeMillis();
            Main.gameValues.paintDone = false;
            // On peint l'interface, ce qui oblige les composantes à calculer leurs animations.
            mainCanvas.repaint();
            try {
                while (!Main.gameValues.paintDone) {
                    Thread.sleep(0, 1);
                }
                Main.gameValues.tempsDuRendu = (System.currentTimeMillis() - Main.gameValues.time);
                /* currentTime vaut le temps en millisecondes prit pour faire un rendu.
                 * En quelque sorte, si le rendu est trop long, on attendra moins 
                 * longtemps avant le suivant afin de ne pas causer d'accélération 
                 * subites en cours de jeu. Si le temps de redraw est plus grand que 10 ms,
                 * soit 100 fps, on passe directement au prochain frame.
                 */
                if (Main.gameValues.tempsDuRendu > Main.gameValues.latency) {
                    /* Thread.sleep(0);
                     * Équivaut tout simplement à ne pas attendre, on lance quand même
                     * un message d'avertissement en sortie.
                     * Le rendu cumule du retard sur le jeu, il serait intéressant
                     * d'essayer de le compenser.
                     */
                } else {
                    Thread.sleep((int) (Main.gameValues.latency - Main.gameValues.tempsDuRendu));
                }
                while (Main.gameValues.isPaused) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
