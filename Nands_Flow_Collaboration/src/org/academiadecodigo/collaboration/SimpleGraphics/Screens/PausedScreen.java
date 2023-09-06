package org.academiadecodigo.collaboration.SimpleGraphics.Screens;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PausedScreen implements KeyboardHandler {
    public static Rectangle pausedScreenBorder;
    public static final int PAUSED_SCREEN_WIDTH = 450;
    public static final int PAUSED_SCREEN_HEIGHT = 600;

    public static Rectangle resumeGame;

    private Picture pausedScreenBackground;

    private Keyboard keyboard;

    private boolean isPaused = false;

    public PausedScreen(){

        keyboard = new Keyboard(this);
        addKeyboard();

        pausedScreenBorder = new Rectangle(10,10,1200,800);
        pausedScreenBorder.setColor(Color.BLACK);
        pausedScreenBorder.draw();
        pausedScreenBorder.fill();

        pausedScreenBackground = new Picture(10,10,"/Images/pause-screen-final.png");
        pausedScreenBackground.draw();
        //pausedScreenBackground.grow(75,100);
    }

    private void addKeyboard() {

        KeyboardEvent keepPlay = new KeyboardEvent();
        keepPlay.setKey(KeyboardEvent.KEY_C);
        keepPlay.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keepPlay);

        KeyboardEvent leave = new KeyboardEvent();
        leave.setKey(KeyboardEvent.KEY_Q);
        leave.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leave);


        KeyboardEvent retry = new KeyboardEvent();
        retry.setKey(KeyboardEvent.KEY_R);
        retry.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(retry);



    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int keyPressed = keyboardEvent.getKey();

        if (keyPressed == KeyboardEvent.KEY_Q) {
            //Terminate game
            //Maybe its not necessary to terminates graphics if
            //we can just terminate the program

            //Graphical part termination
            //Pause screen
            System.exit(0);
            isPaused = false;
            //GameScreen
        }
        if (keyPressed == KeyboardEvent.KEY_R) {
            //Continue game
            pausedScreenBorder.delete();
            pausedScreenBackground.delete();
            isPaused = false;
        }
        if (keyPressed == KeyboardEvent.KEY_C) {
            //Continue game
            pausedScreenBorder.delete();
            pausedScreenBackground.delete();
            isPaused = false;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public boolean getPaused() {
        return isPaused;
    }
    public void setPaused(){
        isPaused = true;
    }

}
