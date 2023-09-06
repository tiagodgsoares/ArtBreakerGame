package org.academiadecodigo.collaboration.SimpleGraphics.Screens;

import org.academiadecodigo.collaboration.Logic.Ball;
import org.academiadecodigo.collaboration.Logic.Boss;
import org.academiadecodigo.collaboration.Logic.Brick;
import org.academiadecodigo.collaboration.Logic.Game;
import org.academiadecodigo.collaboration.SimpleGraphics.PaddleDraw;
import org.academiadecodigo.collaboration.Sound.BackgroundMusic;
import org.academiadecodigo.collaboration.Sound.BallTapBrickSound;
import org.academiadecodigo.collaboration.Sound.BallTapPaddleSound;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class StartMenuScreen {
    /*
    Design a new game screen that appears when the game starts.
    You can use the Simple Graphics library to display a message and provide an option to start the game.
     */
    //Frame for the text boxes
    private Rectangle startMenu;
    private Picture startMenuImage;
    private final int width = 1200;
    private final int height = 800;
    private final int PADDING = 10;


    public StartMenuScreen(){
        startMenu = new Rectangle(PADDING,PADDING,width,height);
        startMenu.draw();

        startMenuImage = new Picture(startMenu.getX(), startMenu.getY(),"/Images/start-screen-final.png");
        startMenuImage.draw();
    }

    public void deleteMenu() {
        this.startMenu.delete();
        this.startMenuImage.delete();
    }
}
