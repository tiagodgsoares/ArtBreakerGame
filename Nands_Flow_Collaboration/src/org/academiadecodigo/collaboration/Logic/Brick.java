package org.academiadecodigo.collaboration.Logic;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;

public class Brick extends Rectangle{
    private Rectangle randomBrick;
    private static int initialBrickX = GameScreen.PADDING + 6;
    private int initialFixedX;
    private int finalFixedX;
    private static int initialBrickY = GameScreen.PADDING + 5;  // + 60 for boss view
    private int initialFixedY;
    private int finalFixedY;
    private boolean isDestroyed;
    private static int totalLines = 0;

    public Brick() {
        if (totalLines == 10) {   // SET MAXIMUM LINES HERE
        } else {
            if (totalLines % 2 == 0) {
                this.randomBrick = new Rectangle(initialBrickX, initialBrickY, 116, 35);
                this.initialFixedX = initialBrickX;
                this.finalFixedX = initialBrickX + 116;
                this.initialFixedY = initialBrickY;
                this.finalFixedY = initialBrickY + 35;
                Color color;
                if ((totalLines == 0) || (totalLines == 1)) {
                    color = Color.RED;
                } else if ((totalLines == 2) || (totalLines == 3)) {
                    color = Color.ORANGE;
                } else if ((totalLines == 4) || (totalLines == 5)) {
                    color = Color.YELLOW;
                } else if ((totalLines == 6) || (totalLines == 7)) {
                    color = Color.GREEN;
                } else {
                    color = Color.CYAN;
                }
                this.randomBrick.setColor(color);
                this.randomBrick.draw();
                this.randomBrick.fill();
                initialBrickX = initialBrickX + 3 + (116);
                if (initialBrickX >= GameScreen.width - 100) {
                    initialBrickX = GameScreen.PADDING + 6;
                    initialBrickY = initialBrickY + 35 + 3; // So we know, 30 initial + 3 spaces between
                    totalLines++;
                }
            } else {
                if ((initialBrickX == GameScreen.PADDING + 6) || (initialBrickX >= GameScreen.width - 80)) {
                    this.randomBrick = new Rectangle(initialBrickX, initialBrickY, 57, 35);
                    this.initialFixedX = initialBrickX;
                    this.finalFixedX = initialBrickX + 57;
                    this.initialFixedY = initialBrickY;
                    this.finalFixedY = initialBrickY + 35;
                    Color color;
                    if ((totalLines == 0) || (totalLines == 1)) {
                        color = Color.RED;
                    } else if ((totalLines == 2) || (totalLines == 3)) {
                        color = Color.ORANGE;
                    } else if ((totalLines == 4) || (totalLines == 5)) {
                        color = Color.YELLOW;
                    } else if ((totalLines == 6) || (totalLines == 7)) {
                        color = Color.GREEN;
                    } else {
                        color = Color.CYAN;
                    }
                    this.randomBrick.setColor(color);
                    this.randomBrick.draw();
                    this.randomBrick.fill();
                    initialBrickX = initialBrickX + 3 + (57);
                    if (initialBrickX >= GameScreen.width - 50) {
                        initialBrickX = GameScreen.PADDING + 6;
                        initialBrickY = initialBrickY + 35 + 3; // So we know, 30 initial + 3 spaces between
                        totalLines++;
                    }
                } else {
                    this.randomBrick = new Rectangle(initialBrickX, initialBrickY, 116, 35);
                    this.initialFixedX = initialBrickX;
                    this.finalFixedX = initialBrickX + 116;
                    this.initialFixedY = initialBrickY;
                    this.finalFixedY = initialBrickY + 35;
                    Color color;
                    if ((totalLines == 0) || (totalLines == 1)) {
                        color = Color.RED;
                    } else if ((totalLines == 2) || (totalLines == 3)) {
                        color = Color.ORANGE;
                    } else if ((totalLines == 4) || (totalLines == 5)) {
                        color = Color.YELLOW;
                    } else if ((totalLines == 6) || (totalLines == 7)) {
                        color = Color.GREEN;
                    } else {
                        color = Color.CYAN;
                    }
                    this.randomBrick.setColor(color);
                    this.randomBrick.draw();
                    this.randomBrick.fill();
                    initialBrickX = initialBrickX + 3 + (116);
                    if (initialBrickX >= GameScreen.width - 50) {
                        initialBrickX = GameScreen.PADDING + 6;
                        initialBrickY = initialBrickY + 35 + 3; // So we know, 30 initial + 3 spaces between
                        totalLines++;
                    }

                    this.isDestroyed = false;
                }
            }
    /*
    Class to represent the individual bricks in the game. Each brick should have properties
    like position, size, color, and a flag to indicate if it has been destroyed.
*/
        }
    }

    public double getInitialFixedX() {
        return initialFixedX;
    }

    public double getInitialFixedY() {
        return initialFixedY;
    }

    public double getFinalFixedX() {
        return finalFixedX;
    }

    public double getFinalFixedY() {
        return finalFixedY;
    }

    public boolean getIsDestroyed() {
        return this.isDestroyed;
    }

    public void setIsDestroyed() {
        this.isDestroyed = true;
    }

    public void delete() {
        this.randomBrick.delete();
    }
}