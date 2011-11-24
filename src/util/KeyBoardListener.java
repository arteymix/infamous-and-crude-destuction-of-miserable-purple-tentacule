package util;

import graphique.Canon;

import graphique.InterfaceGraphique;
import java.util.ArrayList;


/**
 * Le KeyBoardListener est un ArrayListe itéré par un Thread interne. Il permet
 * d'ajouter les touches enfoncés et d'enlever les touches relâchés afin de
 * simuler le multitouch du clavier avec le KeyListener de Swing.
 * La référence de la classe Main est fait implicitement, ce qui veut dire que
 * ce code fonctionnera uniquement avec la structure de classe du TP2 et ne peut
 * être adapté à d'autres projets à moins d'altérer ce code.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class KeyBoardListener extends Thread {

    /**
     * enabledKeys est private pour gérer les exeptions à l'interne. En effet il
     * arrive que des exeptions de NullPointer ou ArrayOutOfBound soient lancées,
     * car le code interagit avec le clavier.
     */
    private ArrayList<Integer> enabledKeys = new ArrayList<Integer>();
    private final Canon CANON_1, CANON_2;

    /**
     * Constructeur pour le KeyBoardListener.
     * @param canon1 est le premier canon dans le jeu.
     * @param canon2 est le second canon dans le jeu.
     */
    public KeyBoardListener(Canon canon1, Canon canon2) {
        super("Thread pour le multitouch du clavier");
        CANON_1 = canon1;
        CANON_2 = canon2;
    }

    /**
     * Rajoute la touche i dans la liste. L'utilisateur enfonce une touche du
     * clavier.
     * @param i est la touche à ajouter.
     */
    public void add(Integer i) {
        enabledKeys.add(i);
    }

    /**
     * Retire la touche i de la liste. L'utilisateur relâche une touche du
     * clavier.
     * @param i est la touche à enlever.
     */
    public void remove(Integer i) {
        enabledKeys.remove(i);
    }

    /**
     * Vérifie si i fait partie de la liste.
     * @param i vérifie si la liste contient la clé i.
     * @return true si la clé est contenue, false autrement.
     */
    public boolean contains(Integer i) {
        return enabledKeys.contains(i);
    }

    /**
     * 
     */
    @Override
    public void run() {
        while (InterfaceGraphique.isRunning) {
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < enabledKeys.size(); i++) {
                if (!InterfaceGraphique.isPaused) {

                    try {
                        CANON_1.gererEvenementDuClavier(enabledKeys.get(i));
                        CANON_2.gererEvenementDuClavier(enabledKeys.get(i));
                    } catch (IndexOutOfBoundsException iaobe) {
                        iaobe.printStackTrace();
                    }
                }
            }
            try {
                long tempsDuRendu = System.currentTimeMillis() - currentTime;
                if (tempsDuRendu > 2 * (int) InterfaceGraphique.latency) {
                    Thread.sleep(0);
                } else {
                    Thread.sleep(2 * (int) InterfaceGraphique.latency - tempsDuRendu);
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            while (InterfaceGraphique.isPaused) {
                try {
                    Thread.sleep((int) InterfaceGraphique.latency);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    ;
}
