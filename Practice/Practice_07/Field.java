public class Field {
    static final char EMPTY_CELL  = '.';
    static final char TURTLE_CELL = 'T';
    static final char MARKED_CELL = '*';

    static final int DEFAULT_WIDTH = 20;
    static final int DEFAULT_HEIGHT = 10;

    static char[][] field;
    static int width, height;

    public static boolean isInside(int x, int y) {
        int fieldWidth = field.length;
        int fieldHeight = field[0].length;

        return x >= 0 && x < fieldWidth &&
               y >= 0 && y < fieldHeight;
    }

    public static void create(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;

        field = new char[width][height];
        for (int y = 0; y < field[0].length; ++y) {
            for (int x = 0; x < field.length; ++x) {
                field[x][y] = EMPTY_CELL;
            }
        }
    }

    public static void print() {
        for (int y = 0; y < field[0].length; ++y) {
            for (int x = 0; x < field.length; ++x) {
                if (Turtle.x == x && Turtle.y == y) {
                    System.out.print(TURTLE_CELL);
                } else {
                    System.out.print(field[x][y]);
                }
            }

            System.out.println();
        }
    }
}
