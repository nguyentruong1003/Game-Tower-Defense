package entity.bullet;

import entity.enemy.AbstractEnemy;
import entity.tile.tower.AbstractTower;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBullet {
    protected AbstractTower tower;
    protected AbstractEnemy enemy;
    protected int posX;
    protected int posY;
    protected int velX;
    protected int velY;

    public AbstractBullet(AbstractTower tower, AbstractEnemy enemy, int posX, int posY) {
        this.tower = tower;
        this.enemy = enemy;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public abstract void paint(Graphics graphics);

    public void fly(int x, int y) {
        int towerPosX = x;
        int towerPosY = y;
        velX = towerPosX - enemy.getPosX()/50;
        velY = towerPosY - enemy.getPosY()/50;

        posX -= velX*2;
        posY -= velY*2;
    }

}
