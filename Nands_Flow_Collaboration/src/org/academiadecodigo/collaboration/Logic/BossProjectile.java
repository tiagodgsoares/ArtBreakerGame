package org.academiadecodigo.collaboration.Logic;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class BossProjectile {

    private Rectangle projectile;
    private double speed = 0.8;
    private double directionY;
    private boolean isDestroyed;


    public BossProjectile(Boss boss) {
        this.projectile = new Rectangle(boss.getBossX()+55, boss.getBossY()+80, 10, 30);
        this.isDestroyed = false;
        this.directionY = 1;
        this.projectile.setColor(Color.MAGENTA);
        this.projectile.draw();
        this.projectile.fill();
    }

    public boolean getIsDestroyed() {
        return this.isDestroyed;
    }

    public void setIsDestroyed(){
        this.isDestroyed = true;
    }


    public void move() {
        if (getIsDestroyed() == false) {

            this.projectile.translate(0, directionY * speed);

        }

    }

    public void delete() {
        this.projectile.delete();
    }

    public double getY() {
        return this.projectile.getY();
    }

    public double getX() {
        return this.projectile.getX();
    }
}
