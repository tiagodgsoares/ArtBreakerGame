package org.academiadecodigo.collaboration.Logic;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;

public class Paddle extends Rectangle {
    /*
    Class to represent the player-controlled paddle at the bottom of the screen.
    The paddle should have properties like position, size, and movement speed.
     */
    public static final double PADDLE_HEIGHT = 20;
    public static final double PADDLE_WIDTH = 150;
    public Paddle() {
        super ((double) ((GameScreen.PADDING + (GameScreen.getGameBorder().getWidth() / 2) - PADDLE_WIDTH / 3)), GameScreen.getGameBorder().getHeight() - GameScreen.PADDING, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}