package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractEntity implements GameEntity{
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;

    protected AbstractEntity(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public AbstractEntity() {}

    @Override
    public final double getPosX() {
        return posX;
    }

    @Override
    public final double getPosY() {
        return posY;
    }

    @Override
    public final double getWidth() {
        return width;
    }

    @Override
    public final double getHeight() {
        return height;
    }

    protected final void setPosX(double posX) {
        this.posX = posX;
    }

    protected final void setPosY(double posY) {
        this.posY = posY;
    }

    protected final void setWidth(double width) {
        this.width = width;
    }

    protected final void setHeight(double height) {
        this.height = height;
    }

    public abstract void draw(Graphics graphics);
}
