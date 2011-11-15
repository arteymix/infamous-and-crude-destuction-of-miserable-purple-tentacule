package content;

import java.io.InputStream;

/**
 * Banque de sons pour le TP2. Les sons sont statiques pour une question 
 * pratique : il n'est pas nécéssaire d'instancier la classe pour y accéder, ce 
 * qui reviendrait à la même chose.
 * Les sons fonctionnent pas threads internes avec l'os.
 * Des méthodes statiques seront crée afin d'utiliser les sons correctement. 
 * @author Guillaume Poirier-Morency
 */
public final class SoundBank {

    public final static InputStream MISSILE = ClassLoader.getSystemResourceAsStream("content/sounds/nuage.png");
}
