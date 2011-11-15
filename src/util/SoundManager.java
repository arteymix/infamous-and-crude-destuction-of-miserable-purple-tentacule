package util;

import java.io.InputStream;
import sun.audio.AudioPlayer;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class SoundManager {

    /**
     * Joue le AudioInputStream en entrée.
     * @param ais 
     */
    public static void play(InputStream ais) {
        AudioPlayer.player.start(ais);
    }
}
