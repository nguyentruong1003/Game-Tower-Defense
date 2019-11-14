package entity.enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalEnemy extends AbstractEnemy{

    private BufferedImage image;
//    int frame = 0;
//    int numberFrames = 12;
//    int delay = 10;

    public NormalEnemy(int health, int speed, int reward, int x, int y) {
        super(health, speed, reward, x, y);
        try {
//            image = ImageIO.read(new File("res/enemy/enemy-sprite.png"));
            image = ImageIO.read(new File("res/enemy/enemy1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
//        BufferedImage res = image.getSubimage(128*frame, 0, 128, 187);
//        delay--;
//        if (delay == 0) {
//            frame++;
//            frame %= numberFrames;
//            delay = 10;
//        }
//        return res;
        return image;
    }
}
