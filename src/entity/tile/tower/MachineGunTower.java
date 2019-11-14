package entity.tile.tower;

import jaco.mp3.player.MP3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MachineGunTower extends AbstractTower{

    private StringBuffer string = new StringBuffer();
    private BufferedImage image;

    public MachineGunTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay, boolean isUpdated, String str) {
        super(cost, attackSpeed, range, damage, sell, maxAttackTime, maxAttackDelay, isUpdated);
        string.append(str);
        try {
            image = ImageIO.read(new File(String.valueOf(this.string)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shootSound = new MP3Player(new File("res/audio/2_t5shot.mp3"));
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
