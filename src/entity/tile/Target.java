package entity.tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Target extends AbstractTile{
    public Target(double posX, double posY, double width, double height, String string) {
        super(posX, posY, width, height);

        try {
            image = ImageIO.read(new File(string));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
    }

}
