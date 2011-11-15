package content;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;

/**
 * Banque de sons pour le TP2. Les sons sont statiques pour une question 
 * pratique : il n'est pas nécéssaire d'instancier la classe pour y accéder, ce 
 * qui reviendrait à la même chose.
 * Les sons fonctionnent pas threads internes avec l'os.
 * Des méthodes statiques seront crée afin d'utiliser les sons correctement. 
 * @author Guillaume Poirier-Morency
 */
public final class SoundBank {

    public final static AudioInputStream MISSILE = new AudioInputStream(ClassLoader.getSystemResourceAsStream("content/sounds/nuage.png"), new AudioFormat(new Encoding(""), 10.0f, 1, 1, 2, 1.2f, true), 10);
}
