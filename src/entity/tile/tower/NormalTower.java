package entity.tile.tower;

import entity.bullet.NormalBullet;
import jaco.mp3.player.MP3Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalTower extends AbstractTower{

    public NormalTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay, boolean isUpdated) {
        super(cost, attackSpeed, range, damage, sell, maxAttackTime, maxAttackDelay, isUpdated);
        try {
            image1 = ImageIO.read(new File("res/tower/normal.png"));
            image2= ImageIO.read(new File("res/tower/normal2.png"));
            image3= ImageIO.read(new File("res/tower/tower1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shootSound = new MP3Player(new File("res/audio/5_t4shot.mp3"));
    }

    @Override
    public BufferedImage getImage1() {
        return image1;
    }

    @Override
    public BufferedImage getImage2() {

        if (!isUpdated) return image2;
        else {
            try {
                image2 = ImageIO.read(new File("res/tower/tower8.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image2;
        }

    }

    @Override
    public BufferedImage getImage3() {
        return image3;
    }

}
