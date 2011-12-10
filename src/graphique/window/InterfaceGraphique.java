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

import graphique.component.Canon;
import graphique.window.MainCanvas.Activity;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class InterfaceGraphique extends JFrame implements Runnable {
    ////////////////////////////////////////////////////////////////////////////
    // Éléments d'interface
    private JMenuBar jmbMenuBar = new JMenuBar();
    private JMenu menuFichier = new JMenu(Traductions.get("menu.fichier")),
            menuLangue = new JMenu(Traductions.get("menu.langue")),
            menuAide = new JMenu(Traductions.get("menu.aide"));
    private JMenuItem mitemQuitter = new JMenuItem(Traductions.get("menu.quitter")),
            mitemNouvellePartie = new JMenuItem(Traductions.get("menu.nouvelle")),
            mitemAPropos = new JMenuItem(Traductions.get("menu.item.apropos"));
    private JCheckBoxMenuItem cbmitemDebug = new JCheckBoxMenuItem(Traductions.get("menu.modedebogage")),
            cbmitemNombreDeCanons = new JCheckBoxMenuItem(Traductions.get("menu.deuxcanons")),
            cbmitemMontrerHighscores = new JCheckBoxMenuItem(Traductions.get("menu.highscores")),
            mitemAide = new JCheckBoxMenuItem(Traductions.get("menu.item.aide"));
    private JRadioButtonMenuItem rbtnEnglish = new JRadioButtonMenuItem(Traductions.get("menu.anglais")),
            rbtnFrancais = new JRadioButtonMenuItem(Traductions.get("menu.francais"));
    private ButtonGroup bgBoutonsLangues = new ButtonGroup();
    /**
     * Canvas où le draw est effectué.
     */
    public MainCanvas mainCanvas = new MainCanvas();
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Listener personalisé pour gérer le multitouch.
     */
    public KeyBoardListener keyBoardListener;

    /**
     * Constructeur pour générer l'interface graphique.
     */
    public InterfaceGraphique() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                Main.close(Main.CODE_DE_SORTIE_FERMETURE_X);
            }
        });        
        /* Le KeyListener du canon est implémenté dans KeyBoardListener afin
         * de rendre possible le multitouch. 
         */
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {
                    case KeySetting.PAUSE:
                        Main.isPaused = !Main.isPaused;
                        break;
                    case KeySetting.SHOW_HIGHSCORES:
                        // On inverse la valeur du show highscores...
                        changeHighscoresState();
                        break;
                    case KeySetting.QUIT:   
                        Main.messageDeFermeture = Traductions.get("message.vousavezquitte");
                        Main.close(0);                       
                        break;
                    case KeySetting.SHOW_DEBUG:
                        changeDebugState(!Main.isDebugEnabled);
                        break;
                    ////////////////////////
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
        ////////////////////////////////////////////////////////////////////////
        // Code pour centrer la fenêtre
        // Source : http://www.java-forums.org/awt-swing/3491-jframe-center-screen.html
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        // Move the window
        this.setLocation(x, y);
        ////////////////////////////////////////////////////////////////////////
        configurerMenus();
        setTitle(Traductions.get("title"));
        
        setVisible(true);   
        setResizable(false);
    }

    /**
     * Méthode run() principale du thread de rendu graphique. Une gestion
     * dynamique de la latence devrait éventuellement se retrouver ici, mais
     * cela prends beaucoup de temps à coder, alors tant que le temps de rendu
     * ne dépasse pas la latence, on devrait être correct.
     */
    @Override
    public void run() {
        while (Main.isRunning) {
            long time = System.currentTimeMillis();
            Main.paintDone = false;
            // On peint l'interface, ce qui oblige les composantes à calculer leurs animations.
            mainCanvas.repaint();
            try {
                while (!Main.paintDone) {
                    Thread.sleep(0, 500);
                }
                Main.tempsDuRendu = (System.currentTimeMillis() - time);
                /* currentTime vaut le temps en millisecondes prit pour faire un rendu.
                 * En quelque sorte, si le rendu est trop long, on attendra moins 
                 * longtemps avant le suivant afin de ne pas causer d'accélération 
                 * subites en cours de jeu. Si le temps de redraw est plus grand que 10 ms,
                 * soit 100 fps, on passe directement au prochain frame.
                 */
                if (Main.tempsDuRendu > Main.latency) {
                    /* Thread.sleep(0);
                     * Équivaut tout simplement à ne pas attendre, on lance quand même
                     * un message d'avertissement en sortie.
                     * Le rendu cumule du retard sur le jeu, il serait intéressant
                     * d'essayer de le compenser.
                     */
                } else {
                    Thread.sleep((int) (Main.latency - Main.tempsDuRendu));
                }
                while (Main.isPaused) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Configure les menus.
     */
    private void configurerMenus() {
        ////////////////////////////////////////////////////////////////////////
        // Configuration des ActionListener
        mitemQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.messageDeFermeture = Traductions.get("message.vousavezquitte");
                Main.close(0);
                
            }
        });
        cbmitemDebug.setState(Main.isDebugEnabled);
        cbmitemDebug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changeDebugState(cbmitemDebug.getState());
            }
        });
        cbmitemNombreDeCanons.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Canon.isCanon2ValidTarget = cbmitemNombreDeCanons.getState();
            }
        });
        mitemNouvellePartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.restart();
            }
        });
        cbmitemMontrerHighscores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changeHighscoresState();
            }
        });
        rbtnFrancais.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {                
                traduire("fr");
            }
        });
        rbtnEnglish.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {                
                traduire("en");
            }
        });
        mitemAide.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changeHelpState();
            }
        });
        mitemAPropos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.isPaused = true;
                // TODO Traduire ce JOptionPane!
                JOptionPane.showMessageDialog(null,
                        "La cruelle et infâme destruction du misérable tentacule mauve (suite et fin... s'il y en a une)"
                        + "\n\nRemis le vendredi 9 décembre 2011"
                        + "\n\nDéveloppeurs et programmeurs :"
                        + "\nGuillaume Poirier-Morency"
                        + "\nNafie Hamrani"
                        + "\n\nTesteurs :"
                        + "\nAomar"
                        + "\n\nVous trouverez toutes l'information nécéssaire sur le dépôt svn à cette addresse :"
                        + "\nhttp://code.google.com/p/tp2-guillaume-poirier-nafie-harmani/", "À propos", JOptionPane.INFORMATION_MESSAGE);
                Main.isPaused = false;
            }
        });
        ////////////////////////////////////////////////////////////////////////
        // ADD TIME!
        menuFichier.add(mitemNouvellePartie);
        menuFichier.addSeparator();
        menuFichier.add(cbmitemDebug);
        menuFichier.add(cbmitemNombreDeCanons);
        menuFichier.addSeparator();
        menuFichier.add(cbmitemMontrerHighscores);
        menuFichier.add(menuLangue);
        menuFichier.addSeparator();
        menuFichier.add(mitemQuitter);
        rbtnFrancais.setSelected(true);
        bgBoutonsLangues.add(rbtnEnglish);
        bgBoutonsLangues.add(rbtnFrancais);
        menuLangue.add(rbtnFrancais);
        menuLangue.add(rbtnEnglish);
        menuAide.add(mitemAide);
        menuAide.addSeparator();
        menuAide.add(mitemAPropos);
        jmbMenuBar.add(menuFichier);
        jmbMenuBar.add(menuAide);
        ////////////////////////////////////////////////////////////////////////
        setJMenuBar(jmbMenuBar);
    }

    /**
     * Traduit le contenu du menu avec des setText().
     */
    private void traduire(String langue) {
        Traductions.setLangue(langue);
        cbmitemDebug.setText(Traductions.get("menu.modedebogage"));
        menuFichier.setText(Traductions.get("menu.fichier"));
        cbmitemNombreDeCanons.setText(Traductions.get("menu.deuxcanons"));
        cbmitemMontrerHighscores.setText(Traductions.get("menu.highscores"));
        mitemQuitter.setText(Traductions.get("menu.quitter"));
        rbtnEnglish.setText(Traductions.get("menu.anglais"));
        rbtnFrancais.setText(Traductions.get("menu.francais"));
        mitemAide.setText(Traductions.get("menu.item.aide"));
        menuAide.setText(Traductions.get("menu.aide"));
        mitemAPropos.setText(Traductions.get("menu.item.apropos"));
        menuLangue.setText(Traductions.get("menu.langue"));
        mitemNouvellePartie.setText(Traductions.get("menu.nouvelle"));
    }

    /**
     * Change l'état de l'aide pour true ou false. L'aide est affichée à 
     * l'instar du jeu.
     */
    private void changeHelpState() {
        if (!Main.isGameOver) {
            if (mainCanvas.activity.equals(Activity.HELP)) {
                mainCanvas.activity = Activity.JEU;
            } else {
                mainCanvas.activity = Activity.HELP;
            }
        }
    }

    /**
     * Change le statut du mode de déboguage.
     * @param b est true si le mode de déboguage doit être activé, false autrement.
     */
    private void changeDebugState(boolean b) {
        Main.isDebugEnabled = b;
        cbmitemDebug.setState(b);
    }

    /**
     * TODO Javadoc ici
     */
    public void changeHighscoresState() {
        if (!Main.isGameOver) {
            if (mainCanvas.activity.equals(Activity.JEU)) {
                mainCanvas.activity = Activity.HIGHSCORES;
                Main.showHighscores = true;
                this.cbmitemMontrerHighscores.setState(true);
            } else {
                mainCanvas.activity = Activity.JEU;
                Main.showHighscores = false;
                this.cbmitemMontrerHighscores.setState(false);
            }
        }
    }
}
