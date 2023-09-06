package org.academiadecodigo.collaboration.SimpleGraphics;

import org.academiadecodigo.collaboration.Logic.Game;
import org.academiadecodigo.collaboration.Logic.Paddle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;

public class PaddleDraw implements KeyboardHandler {
    /*
     Use the Simple Graphics library to draw the game objects on the screen.
     Write methods in the Game class to draw bricks,
     the paddle, the ball, and any other visual elements you want to include.
     */
    public Paddle paddle;
    private final int CELL_SIZE = 10;
    private final int speed = 5;
    private Keyboard keyboard;
    private boolean isDestroyed;
    public PaddleDraw() {
        keyboard = new Keyboard(this);
        addKeyboard();
        this.paddle = new Paddle();
        this.paddle.setColor(Color.RED);
        this.paddle.draw();
        this.paddle.fill();
        this.isDestroyed = false;
    }
    private void addKeyboard() {
        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(37);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(39);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);
    }
    public void moveRight() {
        paddle.translate(CELL_SIZE * speed, 0);
    }
    public void moveLeft() {
        paddle.translate(-CELL_SIZE * speed, 0);
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int keyPressed = keyboardEvent.getKey();
        if (keyPressed == 39) {
            if (paddle.getX() < GameScreen.gameBorder.getWidth() - paddle.getWidth() + GameScreen.PADDING) {
                moveRight();
                if (paddle.getX() + paddle.getWidth() > GameScreen.gameBorder.getWidth()) { //if moves to outside border
                    paddle.translate(-(paddle.getX() + paddle.getWidth()) + GameScreen.gameBorder.getWidth() + GameScreen.PADDING, 0); //reverse direction just how much it is outside
                }
            }
        }
        if (keyPressed == 37) {
            if (paddle.getX() > GameScreen.PADDING) {
                moveLeft();
                if (paddle.getX() < GameScreen.PADDING) { //if moves to outside border
                    paddle.translate(GameScreen.PADDING - paddle.getX(), 0); //reverse direction just how much it is outside
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
    public boolean getIsDestroyed() {
        return isDestroyed;
    }
    public void setDestroyed() {
        isDestroyed = true;
    }
}