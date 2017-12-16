import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private int x, y;

    private Color color;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;

        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(Graphics g);

    public abstract Object clone();

}
