package org.academiadecodigo.collaboration.SimpleGraphics.Screens;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameOverScreen {

    /*
    Design a game over screen that appears when the game ends.
    You can use the Simple Graphics library to display a message and provide an option to restart the game.
     */
    private Rectangle gameOverScreenBorder;
    private Picture gameOverScreenBackground;
    public static final int GAME_OVER_SCREEN_WIDTH = 1200;
    public static final int GAME_OVER_SCREEN_HEIGHT = 800;
    public static final int GAME_OVER_SCREEN_PADDING = 10;


    public GameOverScreen(){
        //Insert background picture
        gameOverScreenBackground = new Picture(GAME_OVER_SCREEN_PADDING,GAME_OVER_SCREEN_PADDING,"/Images/Game-over-screen-final.png");
        gameOverScreenBackground.draw();

        gameOverScreenBorder = new Rectangle(GAME_OVER_SCREEN_PADDING,GAME_OVER_SCREEN_PADDING,GAME_OVER_SCREEN_WIDTH,GAME_OVER_SCREEN_HEIGHT);
        gameOverScreenBorder.draw();
    }

    public Rectangle getGameOverScreenBorder(){
        return gameOverScreenBorder;
    }

    public void delete() {
        this.gameOverScreenBackground.delete();
        this.gameOverScreenBorder.delete();
    }

}
