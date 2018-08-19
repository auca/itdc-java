package main.graphics;

import java.awt.*;

public class Rectangle extends Shape {
    private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(getColor());
        g2.fillRect(getX(), getY(), width, height);
    }
}
