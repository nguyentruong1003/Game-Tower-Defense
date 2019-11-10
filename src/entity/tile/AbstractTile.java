package entity.tile;

import entity.AbstractEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class AbstractTile extends AbstractEntity {

    private BufferedImage image;

    protected AbstractTile(double posX, double posY, double width, double height, String string) {
        super(posX, posY, width, height, string);
        try {
            image = ImageIO.read(new File(string));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected AbstractTile() {
    }

   public void paint(Graphics graphics) {
        graphics.drawImage(image, (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
   }
}
