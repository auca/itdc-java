import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	private static final long serialVersionUID = 1L;

	private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        this(x, y, width, height, Color.WHITE);
    }

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), width, height);
    }
	
	public Object clone() {
		return new Rectangle(getX(), getY(), width, height);
	}

    public String toString() {
        return "Rectangle [x=" + getX() + ", y=" + getY() + ", width=" + width + ", height=" + height + ", color=" + getColor() + "]";
    }

}

