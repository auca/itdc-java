package main;

import main.graphics.Shape;

import java.awt.*;
import java.io.Serializable;

public class Room implements Drawable, Serializable {
    private int id;
    private String name;
    private Shape shape;
    private String description;

    Room(String name, Shape shape) {
        this(-1, name, shape);
    }

    Room(int id, String name, Shape shape) {
        this.id = id;
        this.name = name;
        this.shape = shape;
        description = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    Shape getShape() {
        return shape;
    }

    void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void draw(Graphics2D g2) {
        shape.draw(g2);
        g2.setColor(Color.BLACK);
        g2.drawString(name, shape.getX() + 10, shape.getY() + 20);
    }
}
