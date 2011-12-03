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
package content;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Classe de configuration pour les touches. Sera objet et sérialisable dans un
 * avenir proche.
 * @author Guillaume Poirier-Morency
 */
public final class KeySetting {

    // Canon 1
    /**
     * 
     */
    public static final int CANON_1_SHOOT = KeyEvent.VK_UP;
    /**
     * 
     */
    public static final int CANON_1_LEFT = KeyEvent.VK_LEFT;
    /**
     * 
     */
    public static final int CANON_1_RIGHT = KeyEvent.VK_RIGHT;
    /**
     * 
     */
    public static final int CANON_1_AIM_LEFT = KeyEvent.VK_NUMPAD2;
    /**
     * 
     */
    public static final int CANON_1_AIM_RIGHT = KeyEvent.VK_NUMPAD3;
    // Canon 2
    /**
     * 
     */
    public static final int CANON_2_SHOOT = KeyEvent.VK_W;
    /**
     * 
     */
    public static final int CANON_2_LEFT = KeyEvent.VK_A;
    /**
     * 
     */
    public static final int CANON_2_RIGHT = KeyEvent.VK_D;
    /**
     * 
     */
    public static final int CANON_2_AIM_LEFT = KeyEvent.VK_K;
    /**
     * 
     */
    public static final int CANON_2_AIM_RIGHT = KeyEvent.VK_L;
    // Other settings
    /**
     * 
     */
    public static final int SHOW_HIGHSCORES = KeyEvent.VK_H;
    /**
     * 
     */
    public static final int PAUSE = KeyEvent.VK_P;
    /**
     * 
     */
    public static final int QUIT = KeyEvent.VK_ESCAPE;
    public static final int SHOW_DEBUG = KeyEvent.VK_F6;

    public static void drawKeySettingHelp(Graphics g) {

        // TODO HERE!
        g.setColor(Color.WHITE);
        int i = 440;
        g.drawString("Agencement du clavier", 15, i += 15);

        g.drawString("Canon 1 (celui de gauche) :", 15, i += 30);
        g.drawString("W Tirer", 15, i += 15);
        g.drawString("A Aller à gauche", 15, i += 15);
        g.drawString("D Aller à droite", 15, i += 15);
        g.drawString("K Bouger la tête de canon vers la gauche", 15, i += 15);
        g.drawString("L Bouger la tête de canon vers la droite", 15, i += 15);

        g.drawString("Canon 2 (celui de droite) :", 15, i += 30);
        g.drawString("UP Tirer", 15, i += 15);
        g.drawString("LEFT Aller à gauche", 15, i += 15);
        g.drawString("RIGHT Aller à droite", 15, i += 15);
        g.drawString("NUMPAD_2 Bouger la tête de canon vers la gauche", 15, i += 15);
        g.drawString("NUMPAD_3 Bouger la tête de canon vers la droite", 15, i += 15);

        g.drawString("Autres fonctions :", 15, i += 30);
        g.drawString("ESC Fermer le jeu", 15, i += 15);
        g.drawString("F6 Entrer en mode débogage", 15, i += 15);
        g.drawString("P Mettre le jeu en pause", 15, i += 15);
        g.drawString("H Afficher les highscores", 15, i += 15);
        g.setColor(Color.BLACK);
        // Canon 1 tirer vers le haut
        //        g.drawRect(100, 100, 30, 30);
        //        g.drawString("W", 107, 120);
        //        
        //        // Canon 1 Déplacement à gauche
        //        g.drawRect(130, 130, 30, 30);
        //        g.drawString("D", 107, 120);
        //        
        //        g.drawRect(70, 130, 30, 30);
        //        g.drawRect(100, 130, 30, 30);
        //        
        //        
        //        g.drawRect(200, 100, 30, 30);
        //        g.drawRect(230, 130, 30, 30);
        //        g.drawRect(170, 130, 30, 30);
        //        g.drawRect(200, 130, 30, 30);


    }
}
