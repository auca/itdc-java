package main.graphics;

import java.awt.*;

public class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(getColor());
        int diameter = radius * 2;
        g2.fillOval(getX(), getY(), diameter, diameter);
    }
}