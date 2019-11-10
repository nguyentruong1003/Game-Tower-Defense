package entity.bullet;

import entity.enemy.AbstractEnemy;
import entity.tile.tower.AbstractTower;

import java.awt.*;

public class NormalBullet extends AbstractBullet{
    public NormalBullet(AbstractTower tower, AbstractEnemy enemy, int posX, int posY) {
        super(tower, enemy, posX, posY);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillOval(posX+25, posY+25, 10, 10);
    }
}
