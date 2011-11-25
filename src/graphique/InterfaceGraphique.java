package graphique;

import content.KeySetting;
import graphique.MainCanvas.Activity;
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
    private KeyBoardListener keyBoardListener;

    private void configurerMenus() {
        mitemQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.close(0);
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
                mainCanvas.canon2.isCanon2ValidTarget = cbmitemNombreDeCanons.getState();

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
        menuAide.add(new JMenuItem("Aide..."));
        menuAide.addSeparator();
        menuAide.add(new JMenuItem("Tableau de pointage..."));
        menuAide.add(new JMenuItem("Trophées..."));
        menuAide.addSeparator();
        menuAide.add(new JMenuItem("À propos..."));
        jmb.add(menuFichier);
        jmb.add(menuAide);
        setJMenuBar(jmb);
    }

    /**
     * Constructeur pour générer l'interface graphique.
     */
    public InterfaceGraphique() {
        mainCanvas.canon1 = new Canon(0);
        mainCanvas.canon2 = new Canon(1);
        keyBoardListener = new KeyBoardListener(mainCanvas.canon1, mainCanvas.canon2);
        configurerMenus();
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {

                    case KeySetting.QUIT:
                        Main.gameValues.isPaused = !Main.gameValues.isPaused;
                        // TODO Quitter la partie ici... Ou demander une confirmation?
                        break;
                    case KeySetting.SHOW_HIGHSCORES:
                        // On inverse la valeur du show highscores...
                        if (mainCanvas.activity.equals(Activity.JEU)) {
                            mainCanvas.activity = Activity.HIGHSCORES;
                        } else {
                            mainCanvas.activity = Activity.JEU;
                        }

                        break;
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
        Main.gameValues.composantesDessinables.add(mainCanvas.canon1);
        Main.gameValues.composantesDessinables.add(mainCanvas.canon2);
        add(mainCanvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("La cruelle et infâme destruction du misérable tentacule mauve (suite et fin... si il y en a une)");
        keyBoardListener.start();
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
