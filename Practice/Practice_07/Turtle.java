public class Turtle {
    static int x = 0, y = 0;
    static int dx = 1, dy = 0;
    static boolean isPenDown = false;

    public static void putPenDown() {
        isPenDown = true;
    }

    public static void putPenUp() {
        isPenDown = false;
    }

    public static void turnRight() {
        int temp = dx;
        dx = dy;
        dy = temp;

        dx = -dx;
    }

    public static void turnLeft() {
        int temp = dx;
        dx = dy;
        dy = temp;

        dy = -dy;
    }

    public static void move(int stepCount) {
        for (int i = 0; i < stepCount; ++i) {
            int nextTurtleX = x + dx;
            int nextTurtleY = y + dy;

            if (!Field.isInside(nextTurtleX, nextTurtleY)) {
                break;
            }

            if (isPenDown) {
                Field.field[x][y] = Field.MARKED_CELL;
            }

            x = nextTurtleX;
            y = nextTurtleY;
        }
    }
}
