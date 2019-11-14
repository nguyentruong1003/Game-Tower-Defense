package entity.enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmallerEnemy extends AbstractEnemy{

    BufferedImage image;

    public SmallerEnemy(int health, int speed, int reward, int x, int y) {
        super(health, speed, reward, x, y);
        try {
            image = ImageIO.read(new File("res/enemy/enemy10.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
