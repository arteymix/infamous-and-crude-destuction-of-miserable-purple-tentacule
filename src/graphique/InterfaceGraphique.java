package graphique;

import content.KeySetting;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import util.Vecteur;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.Serializable;
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
import util.KeyBoardListener;
import util.Traductions;

/**
 * Fichier de classe pour l'interface graphique.
 * @author Guillaume Poirier-Morency
 */
public final class InterfaceGraphique extends JFrame implements Serializable {

    /**
     * ArrayList des composantes dessinables.
     */
    static boolean isDebugEnabled = true;
    static ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    private Canon canon1 = new Canon(0);
    private Canon canon2 = new Canon(1);
    private JMenuBar jmb = new JMenuBar();
    private JMenu menuFichier = new JMenu(Traductions.get("menu.fichier")),
            menuLangue = new JMenu(Traductions.get("menu.langue")),
            menuAide = new JMenu(Traductions.get("menu.aide"));
    private JMenuItem mitemQuitter = new JMenuItem(Traductions.get("menu.quitter")),
            mitemNouvellePartie = new JMenuItem(Traductions.get("menu.nouvelle"));
    private JCheckBoxMenuItem cbmitemDebug = new JCheckBoxMenuItem(Traductions.get("menu.modedebogage"));
    private JCheckBoxMenuItem cbmitemNombreDeCanons = new JCheckBoxMenuItem(Traductions.get("menu.deuxcanons"));
    public MainCanvas mainCanvas = new MainCanvas();
    private KeyBoardListener keyBoardListener = new KeyBoardListener(canon1, canon2);
    private ButtonGroup bg = new ButtonGroup();

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
}
