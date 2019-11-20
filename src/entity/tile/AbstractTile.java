package entity.tile;

import entity.AbstractEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class AbstractTile extends AbstractEntity {

    protected BufferedImage image;

    protected AbstractTile(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }

//   public void draw(Graphics graphics) {
//        graphics.drawImage(image, (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
//   }
}
