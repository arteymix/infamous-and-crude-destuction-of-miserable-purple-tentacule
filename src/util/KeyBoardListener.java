package util;

import graphique.Canon;
import graphique.InterfaceGraphique;
import java.util.ArrayList;
import main.Main;

/**
 *
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class KeyBoardListener extends Thread {

    private ArrayList<Integer> enabledKeys = new ArrayList<Integer>();
    private InterfaceGraphique interfaceGraphique;
    private Canon canon1, canon2;

    /**
     * 
     * @param canon1
     * @param canon2 
     */
    public KeyBoardListener(Canon canon1, Canon canon2) {
       this.canon1 = canon1;
       this.canon2 = canon2;
    }

    /**
     * 
     * @param i 
     */
    public void add(Integer i) {
        enabledKeys.add(i);
    }

    /**
     * 
     * @param i 
     */
    public void remove(Integer i) {
        try {
        enabledKeys.remove(i);
        
        } catch(IndexOutOfBoundsException iaobe) {
        iaobe.printStackTrace();
        }
    }

    /**
     * 
     * @param i
     * @return 
     */
    public boolean contains(Integer i) {
        return enabledKeys.contains(i);
    }

    @Override
    public void run() {
        while (Main.isRunning) {
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < enabledKeys.size(); i++) {
                if (!Main.isPaused) {
                    canon1.gererEvenementDuClavier(enabledKeys.get(i), 1);
                    canon2.gererEvenementDuClavier(enabledKeys.get(i), 1);
                }
            }
            try {
                long tempsDuRendu = System.currentTimeMillis() - currentTime;
                if (tempsDuRendu > 20) {
                    Thread.sleep(0);
                } else {
                    Thread.sleep(20 - tempsDuRendu);
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            while (Main.isPaused) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
;
}
