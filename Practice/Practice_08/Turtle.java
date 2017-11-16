public class Turtle {

    private int x, y,
                dx = 1, dy = 0;

    private boolean isPenDown = false;

    private Field field;

    public Turtle(Field field) {
        this.field = field;
        x = 0;
        y = 0;
    }

    public Turtle(Field field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void putPenDown() {
        isPenDown = true;
    }

    public void putPenUp() {
        isPenDown = false;
    }

    public void turnLeft() {
        int temp = dx;
        dx = -dy;
        dy = temp;
    }

    public void turnRight() {
        int temp = dx;
        dx = dy;
        dy = -temp;
    }

    public void move(int steps) {
        for (int i = 0; i < steps; ++i) {
            if (isPenDown) {
                field.markCell(x, y);
            }

            int nextX = x + dx;
            int nextY = y + dy;

            if (!field.isInside(nextX, nextY)) {
                break;
            }

            x = nextX;
            y = nextY;
        }
    }

}
