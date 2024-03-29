package entity.bullet;

import entity.enemy.AbstractEnemy;
import entity.tile.tower.AbstractTower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalBullet extends AbstractBullet{

    BufferedImage image;
    String string = "res/bullet/bullet1.png";

    public NormalBullet(AbstractTower tower, AbstractEnemy enemy, int posX, int posY) {
        super(tower, enemy, posX, posY);
        try {
            image = ImageIO.read(new File(string));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
