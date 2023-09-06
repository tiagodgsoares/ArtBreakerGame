package org.academiadecodigo.collaboration.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
public class BallTapBrickSound {
    private Clip clip;

    public BallTapBrickSound(){}

    public void setMusic() {
        try {
            URL audioUrl = getClass().getResource("/Music/ball-tap-brick.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioUrl);

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
    public  void loop(){
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

