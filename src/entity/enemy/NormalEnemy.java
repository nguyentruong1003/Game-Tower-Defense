package entity.enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalEnemy extends AbstractEnemy{

    private BufferedImage image;

    public NormalEnemy(int health, int speed, int armor, int reward, int x, int y) {
        super(health, speed, armor, reward, x, y);
        try {
            image = ImageIO.read(new File("res/enemy/enemy1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
