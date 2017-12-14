import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius) {
        this(x, y, radius, Color.WHITE);
    }

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);

        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDiameter() {
        return 2 * radius;
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(
            getX() - radius,
            getY() - radius,
            getDiameter(),
            getDiameter()
        );
    }

    public String toString() {
        return "Circle [x=" + getX() + ", y=" + getY() + ", radius=" + radius + ", color=" + getColor() + "]";
    }

}
