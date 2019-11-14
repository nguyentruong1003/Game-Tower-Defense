package entity.enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BossEnemy extends AbstractEnemy{

    BufferedImage image;

    public BossEnemy(int health, int speed, int reward, int x, int y) {
        super(health, speed, reward, x, y);
        try {
            image = ImageIO.read(new File("res/enemy/enemy11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
