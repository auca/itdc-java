public class Field {

    private static final char EMPTY_CELL_SYMBOL  = '.',
                              MARKED_CELL_SYMBOL = '*',
                              TURTLE_SYMBOL      = 'T';

    private char[][] field;

    public Field(int width, int height) {
        field = new char[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                field[i][j] = EMPTY_CELL_SYMBOL;
            }
        }
    }

    public String toString() {
        return toString(new Turtle[] { });
    }

    public String toString(Turtle[] turtles) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < field.length; ++i) {
            outer:
            for (int j = 0; j < field[0].length; ++j) {
                for (int k = 0; k < turtles.length; ++k) {
                    if (turtles[k].getY() == i &&
                        turtles[k].getX() == j) {
                        result.append(TURTLE_SYMBOL);
                        continue outer;
                    }
                }

                result.append(field[i][j]);
            }
            result.append("\n");
        }
        result.append("\n");

        return result.toString();
    }

    public void markCell(int x, int y) {
        if (isInside(x, y)) {
            field[y][x] = MARKED_CELL_SYMBOL;
        }
    }

    public boolean isInside(int x, int y) {
        return (x >= 0 && x < field[0].length) &&
               (y >= 0 && y < field.length);
    }

}
