package org.academiadecodigo.collaboration.Sound;

import org.academiadecodigo.collaboration.Logic.Game;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
public class BackgroundMusic {

    private Clip clip;
    private Game game;

    public BackgroundMusic(){}

    public void setMusic() {
        try {
            URL audioUrl = getClass().getResource("/Music/background-music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioUrl);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

        play();
        loop();
    }

    public void setGame(Game game) {
        this.game = game;
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

