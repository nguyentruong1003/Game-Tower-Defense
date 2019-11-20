package entity.tile.tower;

import jaco.mp3.player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SniperTower extends AbstractTower{

    public SniperTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay, boolean isUpdated) {
        super(cost, attackSpeed, range, damage, sell, maxAttackTime, maxAttackDelay, isUpdated);
        try {
            image1 = ImageIO.read(new File("res/tower/sniper.png"));
            image2 = ImageIO.read(new File("res/tower/sniper2.png"));
            image3 = ImageIO.read(new File("res/tower/tower4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shootSound = new MP3Player(new File("res/audio/6_t3shot.mp3"));
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
                image2 = ImageIO.read(new File("res/tower/tower9.png"));
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
