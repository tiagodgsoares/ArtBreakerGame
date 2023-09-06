package org.academiadecodigo.collaboration.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WinnerSound {

    private Clip clip;
    public void setMusic() {
        try {
            URL audioUrl = getClass().getResource("/Music/winner-sound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Nands_Flow_Collaboration/Resources/Music/boss-sound.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        play();
    }
    public void play(){
        if (clip != null) {
            clip.start();
        }

    }
    public  void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}