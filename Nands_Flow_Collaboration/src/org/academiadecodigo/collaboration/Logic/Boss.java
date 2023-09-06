package org.academiadecodigo.collaboration.Logic;
//import org.academiadecodigo.simplegraphics.graphics.Color;

import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss {
    private Rectangle boss;
    private Picture bossPicture;
    private int initialBossX = GameScreen.width/2;
    private int initialBossY = GameScreen.PADDING + 5;
    private double directionX;
    private double speed = 1.2;
    private int isDestroyed;

    public Boss() {
        this.boss = new Rectangle(initialBossX, initialBossY, 110,110);
        //this.boss.setColor(Color.BLACK);
        //this.boss.fill();
        this.bossPicture = new Picture(initialBossX, initialBossY,"Nands_Flow_Collaboration/Resources/Images/boss.png");
        this.bossPicture.draw();
        //this.bossPicture.grow(-100,-150);
        this.isDestroyed = 5;  // BOSS LIFE
        this.directionX = -1;
    }
    public void changeDirectionX() {
        directionX = -directionX;
    }
    public void move() {

        if (this.boss.getX() <= 12 || this.boss.getX() >= GameScreen.gameBorder.getWidth() - 102) {
            changeDirectionX();
        }

        this.boss.translate(directionX * speed, 0);
        this.bossPicture.translate(directionX * speed, 0);

    }

    public void shoot(){
        //Rectangle projectile = new Rectangle(boss.getX()+25, boss.getY()+60, 10, 30);
        Picture projectile = new Picture(boss.getX()+25, boss.getY()+60,"Nands_Flow_Collaboration/Resources/Images/bossweapon.png");


    }

    public double getBossX(){
        return boss.getX();
    }

    public double getBossY(){
        return boss.getY();
    }

    public int getIsDestroyed() {
        return this.isDestroyed;
    }

    public void setIsDestroyed() {
        isDestroyed = isDestroyed - 1;
        if (isDestroyed == 0) {
            this.bossPicture.delete();
            this.boss.delete();
        }
    }
}