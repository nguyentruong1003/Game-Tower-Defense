package entity.tile.tower;

import entity.bullet.AbstractBullet;
import entity.enemy.AbstractEnemy;
import entity.tile.AbstractTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractTower extends AbstractTile implements Cloneable {

    protected int cost;
    protected double attackSpeed;
    protected int range;
    protected int damage;
    protected int sell;
    public double attackTime;
    public double attackDelay;
    public double maxAttackTime;
    public double maxAttackDelay;
    public AbstractEnemy target;
    public List<AbstractBullet> listBullet = new ArrayList<>();
    public ArrayDeque<Integer> bulletArrayDeque = new ArrayDeque<>();

    public AbstractTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay) {
        this.cost = cost;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.damage = damage;
        this.sell = sell;
        this.maxAttackTime = maxAttackTime;
        this.maxAttackDelay = maxAttackDelay;
        this.attackTime = 0;
        this.attackDelay = 0;
    }

    public abstract BufferedImage getTexture();

    public int getCost() {
        return cost;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getSell() {
        return sell;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AbstractTower) {
            AbstractTower other = (AbstractTower) object;
            if (this.cost == other.getCost()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AbstractEnemy enemiesAttacked (List<AbstractEnemy> enemies, int x, int y) {
        AbstractEnemy[] enemiesInRange = new AbstractEnemy[enemies.size()];

        int towerPosX = x;
        int towerPosY = y;
        int towerRange = this.range;
        int enemyX;
        int enemyY;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies != null) {
                enemyX = enemies.get(i).getPosX() / 50;
                enemyY = enemies.get(i).getPosY() / 50;

                int dX = enemyX - towerPosX;
                int dY = enemyY - towerPosY;

                if (dX * dX + dY * dY <= towerRange * towerRange) {
                    enemiesInRange[i] = enemies.get(i);
                }
            }
        }

        int totalEnemies = 0;
        for (int i = 0; i < enemiesInRange.length; i++) {
            if (enemiesInRange[i] != null) {
                totalEnemies++;
            }
        }

        if (totalEnemies > 0) {
            int enemy = new Random().nextInt(totalEnemies);
            int enemiesTaken = 0;
            int i = 0;

            while (true) {
                if (enemiesTaken == enemy && enemiesInRange[i] != null) {
                    return enemiesInRange[i];
                }
                if (enemiesInRange[i] != null) {
                    enemiesTaken++;
                }
                i++;
            }
        }
        return null;
    }

}
