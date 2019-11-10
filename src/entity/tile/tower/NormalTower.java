package entity.tile.tower;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalTower extends AbstractTower{
    //"res/tower/tower1.png"
    private StringBuffer string = new StringBuffer();
    BufferedImage image;
    public NormalTower(int cost, double attackSpeed, int range, int damage, int sell, double maxAttackTime, double maxAttackDelay, String str) {
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

    @Override
    public void updateTower() {
        numberUpdate++;
    }
}
