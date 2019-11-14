package entity.enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractEnemy implements Cloneable{
    protected int health;
    protected int speed;
    protected int reward;
    protected int posX;
    protected int posY;
    protected int velX;
    protected int velY;
    protected int lastTurn;

    public AbstractEnemy(int health, int speed, int reward, int x, int y) {
        this.health = health;
        this.speed = speed;
        this.reward = reward;
        this.posX = x;
        this.posY = y;
    }

    public abstract BufferedImage getTexture();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
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

    public void move(int [][]enemyMap) {
        int x = posX/50;
        int y = posY/50;

        if (lastTurn == 3) {
            x++;
            if (enemyMap[x][y] != 0 && enemyMap[x][y] != 3) posX = (50*x);
        } else if (lastTurn == 4){
            y++;
            if (enemyMap[x][y] != 0 && enemyMap[x][y] != 4) posY = (50*y);
        }

        switch (enemyMap[x][y]){
            case 1: velX = getSpeed(); velY = 0; break;
            case 3: velX = -getSpeed(); velY = 0; break;
            case 2: velY = getSpeed(); velX = 0; break;
            case 4: velY = -getSpeed(); velX = 0; break;
        }
        if (enemyMap[x][y] != 0) lastTurn = enemyMap[x][y];

        posX += velX;
        posY += velY;
    }

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
