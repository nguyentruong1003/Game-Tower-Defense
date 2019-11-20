package entity;

import java.awt.*;

public interface GameEntity {
    double getPosX();

    double getPosY();

    double getWidth();

    double getHeight();

    void draw(Graphics graphics);
}
