package entity;

import java.awt.*;

public abstract class AbstractEntity implements GameEntity{
    private double posX;
    private double posY;
    private double width;
    private double height;
    private String string;

    protected AbstractEntity(double posX, double posY, double width, double height, String string) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.string = string;
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

    public abstract void paint(Graphics graphics);
}
