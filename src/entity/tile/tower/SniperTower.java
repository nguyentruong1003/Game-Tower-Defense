package entity.tile.tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SniperTower extends AbstractTower{

    private StringBuffer string = new StringBuffer();
    private BufferedImage image;

    public SniperTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay, String str) {
        super(cost, attackSpeed, range, damage, sell, maxAttackTime, maxAttackDelay);
        string.append(str);
        try {
            image = ImageIO.read(new File(String.valueOf(this.string)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTexture() {
        return image;
    }
}
