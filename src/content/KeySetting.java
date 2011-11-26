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
    public static final int CANON_2_AIM_LEFT = KeyEvent.VK_O;
    /**
     * 
     */
    public static final int CANON_2_AIM_RIGHT = KeyEvent.VK_P;
    // Other settings
    /**
     * 
     */
    public static final int SHOW_HIGHSCORES = KeyEvent.VK_H;
    /**
     * 
     */
    public static final int PAUSE = KeyEvent.VK_P;
    public static final int QUIT = KeyEvent.VK_ESCAPE;
}
