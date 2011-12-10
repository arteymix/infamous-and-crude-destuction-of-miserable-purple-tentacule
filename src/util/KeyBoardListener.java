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
package util;

import graphique.component.Canon;

import java.util.ArrayList;
import main.Main;

/**
 * Le KeyBoardListener est un ArrayListe itéré par un Thread interne. Il permet
 * d'ajouter les touches enfoncés et d'enlever les touches relâchés afin de
 * simuler le multitouch du clavier avec le KeyListener de Swing.
 * La référence de la classe Main est fait implicitement, ce qui veut dire que
 * ce code fonctionnera uniquement avec la structure de classe du TP2 et ne peut
 * être adapté à d'autres projets à moins d'altérer ce code. De plus, les accès
 * aux méthodes de l'ArrayList sont synchronisés afin d'éviter les références
 * nulles et les autres exceptions de threads concurrents.
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
     * Itère une ArrayList de touches enfoncés et les envoies aux canons afin
     * qu'ils puissent gérer le multitouch.
     */
    @Override
    public void run() {
        while (Main.isRunning) {
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < enabledKeys.size(); i++) {
                if (!Main.isPaused) {
                    // TODO Il y a un problème occasionnel d'exceptions ici!
                    
                    try {
                        CANON_1.gererEvenementDuClavier(enabledKeys.get(i));
                        CANON_2.gererEvenementDuClavier(enabledKeys.get(i));
                    } catch (IndexOutOfBoundsException iaobe) {
                        System.out.println("La clé du clavier recherché n'a pas été trouvée");
                        iaobe.printStackTrace();
                    }
                }
            }
            try {
                long tempsDuRendu = System.currentTimeMillis() - currentTime;
                if (tempsDuRendu > 2 * (int) Main.latency) {
                    Thread.sleep(0);
                } else {
                    Thread.sleep(2 * (int) Main.latency - tempsDuRendu);
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            while (Main.isPaused) {
                try {
                    Thread.sleep((int) Main.latency);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
