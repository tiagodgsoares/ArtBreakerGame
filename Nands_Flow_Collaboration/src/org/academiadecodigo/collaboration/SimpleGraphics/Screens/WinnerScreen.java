package org.academiadecodigo.collaboration.SimpleGraphics.Screens;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class WinnerScreen implements KeyboardHandler {

    public static Rectangle rect;
    private int width = 1200;
    private int height = 800;
    private int padding = 10;
    public static Picture winner;
    private Keyboard key;
    public WinnerScreen(){

        rect = new Rectangle(padding,padding,width,height);
        rect.draw();

        winner = new Picture(padding,padding,"/Images/winner-screen-final.PNG");
        winner.draw();

        key = new Keyboard(this);
        addKeyboard();
    }

    private void addKeyboard(){
        //Should initialize the game again
        KeyboardEvent restart = new KeyboardEvent();
        restart.setKey(KeyboardEvent.KEY_R);
        restart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(restart);
        //Should terminate the game
        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(quit);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int keyPressed = keyboardEvent.getKey();

        if( keyPressed == KeyboardEvent.KEY_R){
            //delete the picture and rectangle of winner screen
            rect.delete();
            winner.delete();

            //Start a new game
        }
        if (keyPressed == KeyboardEvent.KEY_Q){
            //Terminate the game
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}