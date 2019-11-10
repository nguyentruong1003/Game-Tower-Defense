package entity.enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TankerEnemy extends AbstractEnemy{
    public TankerEnemy(int health, int speed, int armor, int reward, int x, int y) {
        super(health, speed, armor, reward, x, y);
    }

    @Override
    public BufferedImage getTexture() {
        return null;
    }
}
