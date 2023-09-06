package org.academiadecodigo.collaboration.SimpleGraphics.Screens;

import org.academiadecodigo.collaboration.Logic.Game;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameScreen implements KeyboardHandler {

    /*
    Design a game over screen that appears when the game is on.

     */

    public static Rectangle gameBorder;
    public static final int width = 1200;
    public static final int height = 800;
    public static final int PADDING = 10;
    public static Picture gameScreenBackground;
    private PausedScreen pausedScreen;
    private Keyboard keyboard;

    public GameScreen(){
        gameBorder = new Rectangle(PADDING,PADDING, width, height);
        gameBorder.draw();

        gameScreenBackground = new Picture(10,10,"/Images/BACKGROUNDJOGO1200x800.png");
        gameScreenBackground.draw();

        keyboard = new Keyboard(this);
        addKeyboard();
    }

    public static Rectangle getGameBorder() {
        return gameBorder;
    }

    public static Picture getGameScreenBackground(){
        return gameScreenBackground;
    }

    private void addKeyboard() {

        KeyboardEvent pause = new KeyboardEvent();
        pause.setKey(KeyboardEvent.KEY_P);
        pause.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pause);

    }

    public boolean isPaused() {
        if (this.pausedScreen != null) {
            return this.pausedScreen.getPaused();
        }
        return false;
    }

    public void delete() {
        gameBorder.delete();
        gameScreenBackground.delete();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int keyPressed = keyboardEvent.getKey();

        if (keyPressed == KeyboardEvent.KEY_P) {
            pausedScreen = new PausedScreen();
            pausedScreen.setPaused();
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
