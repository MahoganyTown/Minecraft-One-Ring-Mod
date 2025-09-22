package org.lgls9191.oneringmod.sounds;

import org.lgls9191.oneringmod.Oneringmod;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class SoundClipPlayer {
    private Clip clip;
    private AudioInputStream audio;

    public SoundClipPlayer(final String filePath) {
        File ambient;
        try {
            ambient = new File(filePath);
            audio = AudioSystem.getAudioInputStream(ambient);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            Oneringmod.logger.error("Error at audio clip creating.");
            e.printStackTrace();
        }
    }

    public void start() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void clean() {
        clip.close();
        try {
            audio.close();
        } catch (IOException e) {
            Oneringmod.logger.error("Could not close properly the audio file.");
        }
    }
}