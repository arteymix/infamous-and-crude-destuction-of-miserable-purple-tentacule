package graphique;

import content.ImageBank;
import content.KeySetting;
import graphique.MainCanvas.Activity;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import main.Main;
import util.Dessinable;
import util.KeyBoardListener;
import util.SoundManager;
import util.Traductions;

/**
 * Fichier de classe pour l'interface graphique.
 * @author Guillaume Poirier-Morency
 */
public final class InterfaceGraphique extends JFrame implements Serializable, Runnable {

    /**
     * ArrayList des composantes dessinables.
     */
    static boolean isDebugEnabled = true;
    static ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    Canon canon1;
    Canon canon2;
    private JMenuBar jmb = new JMenuBar();
    private JMenu menuFichier = new JMenu(Traductions.get("menu.fichier")),
            menuLangue = new JMenu(Traductions.get("menu.langue")),
            menuAide = new JMenu(Traductions.get("menu.aide"));
    private JMenuItem mitemQuitter = new JMenuItem(Traductions.get("menu.quitter")),
            mitemNouvellePartie = new JMenuItem(Traductions.get("menu.nouvelle"));
    private JCheckBoxMenuItem cbmitemDebug = new JCheckBoxMenuItem(Traductions.get("menu.modedebogage"));
    private JCheckBoxMenuItem cbmitemNombreDeCanons = new JCheckBoxMenuItem(Traductions.get("menu.deuxcanons"));
    /**
     * 
     */
    public MainCanvas mainCanvas = new MainCanvas();
    private KeyBoardListener keyBoardListener;
    private ButtonGroup bg = new ButtonGroup();
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public static boolean isRunning = true;
    /**
     * Système de gestion du son pour agrémenter l'expérience de l'utilisateur
     * avec un serveur de sons.
     */
    public static SoundManager son = new SoundManager();

    private void configurerMenus() {
        mitemQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.close();
            }
        });
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
                canon2.isCanon2ValidTarget = cbmitemNombreDeCanons.getState();

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

        try {
            System.out.println("La banque d'images n'a pas pu être instancié!");
            imageBank = new ImageBank();
        } catch (IOException ex) {
            System.out.println("La banque d'images n'a pas pu être instancié!");
        }
        canon1 = new Canon(0);
        canon2 = new Canon(1);
        keyBoardListener = new KeyBoardListener(canon1, canon2);
        configurerMenus();
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {

                    case KeySetting.QUIT:
                        isPaused = !isPaused;
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
        composantesDessinables.add(canon1);
        composantesDessinables.add(canon2);
        add(mainCanvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("La cruelle et infâme destruction du misérable tentacule mauve (suite et fin... si il y en a une)");
        keyBoardListener.start();
    }

    @Override
    public void run() {
        timerSeconds++;
        while (isRunning) {

            time = System.currentTimeMillis();
            paintDone = false;
            // On peint l'interface, ce qui oblige les composantes à calculer leurs animations.
            mainCanvas.repaint();
            try {
                while (!paintDone) {
                    Thread.sleep(0, 1);
                }
                tempsDuRendu = (System.currentTimeMillis() - time);
                /* currentTime vaut le temps en millisecondes prit pour faire un rendu.
                 * En quelque sorte, si le rendu est trop long, on attendra moins 
                 * longtemps avant le suivant afin de ne pas causer d'accélération 
                 * subites en cours de jeu. Si le temps de redraw est plus grand que 10 ms,
                 * soit 100 fps, on passe directement au prochain frame.
                 */
                if (tempsDuRendu > latency) {
                    Thread.sleep(0);
                } else {
                    Thread.sleep((int) (latency - tempsDuRendu));
                }
                while (isPaused) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();

            }
        }
    }
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public static double latency = 10;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public static long tempsDuRendu = 0;
    /**
     * Objet pour la banque d'images qui contient des images pour le rendu.
     */
    public static ImageBank imageBank;
    /**
     * 
     */
    public static boolean isPaused = false;
    /**
     * Est true quand le rendu est fini, false quand le rendu est en cours.
     */
    public static boolean paintDone = false;
    /**
     * Variable qui contient le temps de jeu.
     */
    public static long time;
    /**
     * Timer qui donne le temps depuis le début du jeu.
     */
    public static long timerSeconds = 0;
}
