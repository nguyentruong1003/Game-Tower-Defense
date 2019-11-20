package entity.enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BossEnemy extends AbstractEnemy{

    int frame = 0;
    int numberFrames = 6;
    int delay = 15;

    public BossEnemy(int health, int speed, int reward, int x, int y) {
        super(health, speed, reward, x, y);
        try {
            image = ImageIO.read(new File("res/enemy/boss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {

        res = image.getSubimage(47, 3+frame*30, 22, 24);
        delay--;
        if (delay == 0) {
            frame++;
            frame %= numberFrames;
            delay = 15;
        }
        if (direction == 1) return res;
        if (direction == 2) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-res.getWidth(null), 0);

            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            res = op.filter(res, null);

            return res;
        }
        return null;

    }
}
