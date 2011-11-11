package graphique;

import content.KeySetting;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Vecteur;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import main.Main;
import util.Dessinable;
import util.Traductions;

/**
 * Fichier de classe pour l'interface graphique.
 * @author Guillaume Poirier-Morency
 */
public final class InterfaceGraphique extends JFrame {

    /**
     * ArrayList des composantes dessinables.
     */
    public static boolean isDebugEnabled = true;
    /**
     * Variable indiquant le pointage du joueur.
     */
    public static ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    public static Vecteur canvasSize = new Vecteur(800, 800);
    private static Canon canon1 = new Canon(new Vecteur(0, 648), 0, null);
    private static Canon canon2 = new Canon(new Vecteur(689, 648), 1, null);
    private JMenuBar jmb = new JMenuBar();
    private JMenu menuFichier = new JMenu(Traductions.get("menu.fichier")),
            menuEditer = new JMenu(Traductions.get("menu.editer")),
            menuLangue = new JMenu(Traductions.get("menu.langue")),
            menuAide = new JMenu(Traductions.get("menu.aide"));
    private JMenuItem mitemQuitter = new JMenuItem(Traductions.get("menu.quitter"));
    private JCheckBoxMenuItem cbmitemDebug = new JCheckBoxMenuItem(Traductions.get("menu.modedebogage"));
    private JCheckBoxMenuItem cbmitemNombreDeCanons = new JCheckBoxMenuItem(Traductions.get("menu.deuxcanons"));
    public MainCanvas mainCanvas = new MainCanvas();

    private void configurerMenus() {

        cbmitemDebug.setState(isDebugEnabled);
        cbmitemDebug.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                isDebugEnabled = cbmitemDebug.getState();
            }
        });
        cbmitemNombreDeCanons.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                canon2.isValidTarget = cbmitemNombreDeCanons.getState();

            }
        });
        menuFichier.add(mitemQuitter);
        menuEditer.add(cbmitemDebug);
        menuEditer.add(cbmitemNombreDeCanons);
        ButtonGroup bg = new ButtonGroup();
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
        jmb.add(menuFichier);
        jmb.add(menuEditer);
        jmb.add(menuLangue);
        jmb.add(menuAide);
        setJMenuBar(jmb);
    }
    private ArrayList<Integer> enabledKeys = new ArrayList<Integer>();
    public Thread keyBoardListener = new Thread() {

        @Override
        public void run() {
            while (Main.isRunning) {
                long currentTime = System.currentTimeMillis();
                for (int i = 0; i < enabledKeys.size(); i++) {
                    switch (enabledKeys.get(i)) {

                        default:
                            if (!Main.isPaused) {
                                canon1.gererEvenementDuClavier(enabledKeys.get(i), 1);
                                canon2.gererEvenementDuClavier(enabledKeys.get(i), 1);
                            }
                            break;
                    }
                }
                try {
                    long tempsDuRendu = System.currentTimeMillis() - currentTime;
                    if (tempsDuRendu > 20) {
                        Thread.sleep(0);
                    }
                    else {
                        Thread.sleep(20 - tempsDuRendu);
                    }

                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                while (Main.isPaused) {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * Constructeur pour générer l'interface graphique.
     */
    public InterfaceGraphique() {
        configurerMenus();
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {

                    case KeySetting.QUIT:
                        Main.isPaused = !Main.isPaused;
                        // TODO Quitter la partie ici... Ou demander une confirmation?
                        break;
                    case KeySetting.SHOW_HIGHSCORES:
                        // On inverse la valeur du show highscores...
                        mainCanvas.showHighscores = !mainCanvas.showHighscores;
                        break;
                    default:
                        if (!enabledKeys.contains(arg0.getKeyCode())) {
                            enabledKeys.add(arg0.getKeyCode());
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                enabledKeys.remove((Integer) arg0.getKeyCode());
            }
        });
        // On rajoute le canon 1 par défaut.
        composantesDessinables.add(canon1);
        composantesDessinables.add(canon2);
        this.add(mainCanvas);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension((int) canvasSize.x, (int) canvasSize.y));
        this.setVisible(true);
        this.pack();
        this.keyBoardListener.start();
    }
}
