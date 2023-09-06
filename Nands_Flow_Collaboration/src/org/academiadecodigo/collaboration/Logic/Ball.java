package org.academiadecodigo.collaboration.Logic;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;

public class Ball extends Ellipse {
    /*
    Class to represent the ball that bounces around the screen.
    The ball should have properties like position, size, movement direction, and speed.
     */
    private double directionX;
    private double directionY;
    private double speed = 0.55;
    public static final double BALL_SIZE = 16;
    public Ball() {                                                //random start 500 to 700 width
        super((double) GameScreen.getGameBorder().getWidth() / ((Math.random()*0.6) + 1.75), GameScreen.getGameBorder().getHeight() - Paddle.PADDLE_HEIGHT - BALL_SIZE, BALL_SIZE, BALL_SIZE);
        this.setColor(Color.YELLOW);
        this.fill();
        this.directionY = - ((Math.random()*0.4) + 0.8);  //random -0.8 to 1.2
        this.directionX = (Math.random()*0.4) + 0.8;   //random 0.8 to 1.2
        // TODO check difference between this and super
    }
    public void setSpeed(double increase) {
        this.speed = speed + increase;
    }
    public void move() {
        if (this.getX() < GameScreen.PADDING || this.getX() + BALL_SIZE > GameScreen.getGameBorder().getWidth() + GameScreen.PADDING) {
            changeDirectionX();
        }
        if (this.getY() < GameScreen.PADDING || this.getY() > GameScreen.getGameBorder().getHeight()) {
            changeDirectionY();
        }
        this.translate(directionX * speed, directionY * speed);
    }
    public void changeDirectionX() {
        directionX = -directionX;
    }
    public void changeDirectionY() {
        directionY = -directionY;
    }
    public void setDirectionNorth() {
        this.directionY = -1;
    }
}