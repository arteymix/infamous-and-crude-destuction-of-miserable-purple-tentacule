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

import java.net.URL;

/**
 * Banque de sons pour le TP2. Les sons sont statiques pour une question 
 * pratique : il n'est pas nécéssaire d'instancier la classe pour y accéder, ce 
 * qui reviendrait à la même chose.
 * Les sons fonctionnent pas threads internes avec l'os.
 * Des méthodes statiques seront crée afin d'utiliser les sons correctement. 
 * @author Guillaume Poirier-Morency
 */
public final class SoundBank {

    /**
     * 
     */
    public URL explosion = ClassLoader.getSystemResource("content/sounds/explosion.wav");

    
   
}
