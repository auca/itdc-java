package main.graphics;

import main.Drawable;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    private int x, y;
    private Color color;

    Shape(int x, int y) {
        this.x = x;
        this.y = y;

        color = Color.WHITE;
    }

    public int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }
}