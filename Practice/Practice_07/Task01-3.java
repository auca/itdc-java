import java.util.Scanner;

public class Main {
    // Commands

    static final int COMMAND_PUT_PEN_DOWN = 1;
    static final int COMMAND_PUT_PEN_UP = 2;
    static final int COMMAND_TURN_RIGHT = 3;
    static final int COMMAND_TURN_LEFT  = 4;
    static final int COMMAND_MOVE  = 5;
    static final int COMMAND_PRINT = 6;
    static final int COMMAND_EXIT  = 9;

    // Field's Constants

    static final char FIELD_EMPTY_CELL  = '.';
    static final char FIELD_TURTLE_CELL = 'T';
    static final char FIELD_MARKED_CELL = '*';

    static final int FIELD_DEFAULT_WIDTH = 20;
    static final int FIELD_DEFAULT_HEIGHT = 20;

    // Turtle's Data

    static int turtleX = 0, turtleY = 0;
    static int turtleDX = 1, turtleDY = 0;
    static boolean turtleIsPenDown = false;

    // Field's Data
    static char[][] field;

    // Turtle's Methods

    public static void turtlePutPenDown() {
        turtleIsPenDown = true;
    }

    public static void turtlePutPenUp() {
        turtleIsPenDown = false;
    }

    public static void turtleTurnRight() {
        int temp = turtleDX;
        turtleDX = turtleDY;
        turtleDY = temp;

        turtleDX = -turtleDX;
    }

    public static void turtleTurnLeft() {
        int temp = turtleDX;
        turtleDX = turtleDY;
        turtleDY = temp;

        turtleDY = -turtleDY;
    }

    public static void turtleMove(int stepCount) {
        for (int i = 0; i < stepCount; ++i) {
            int nextTurtleX = turtleX + turtleDX;
            int nextTurtleY = turtleY + turtleDY;

            if (!isInsideField(nextTurtleX, nextTurtleY)) {
                break;
            }

            if (turtleIsPenDown) {
                field[turtleX][turtleY] = FIELD_MARKED_CELL;
            }

            turtleX = nextTurtleX;
            turtleY = nextTurtleY;
        }
    }

    public static boolean isInsideField(int x, int y) {
        int fieldWidth = field.length;
        int fieldHeight = field[0].length;

        return x >= 0 && x < fieldWidth &&
               y >= 0 && y < fieldHeight;
    }

    // Field's Methods

    public static void fieldCreate(int width, int height) {
        field = new char[width][height];
        for (int y = 0; y < field[0].length; ++y) {
            for (int x = 0; x < field.length; ++x) {
                field[x][y] = FIELD_EMPTY_CELL;
            }
        }
    }

    public static void fieldPrint() {
        for (int y = 0; y < field[0].length; ++y) {
            for (int x = 0; x < field.length; ++x) {
                if (turtleX == x && turtleY == y) {
                    System.out.print(FIELD_TURTLE_CELL);
                } else {
                    System.out.print(field[x][y]);
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        fieldCreate(FIELD_DEFAULT_WIDTH, FIELD_DEFAULT_HEIGHT);

        Scanner scanner = new Scanner(System.in);
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case COMMAND_PUT_PEN_DOWN:
                    turtlePutPenDown();
                    break;
                case COMMAND_PUT_PEN_UP:
                    turtlePutPenUp();
                    break;
                case COMMAND_TURN_RIGHT:
                    turtleTurnRight();
                    break;
                case COMMAND_TURN_LEFT:
                    turtleTurnLeft();
                    break;
                case COMMAND_MOVE:
                    int stepCount = scanner.nextInt();
                    turtleMove(stepCount);
                    break;
                case COMMAND_PRINT:
                    fieldPrint();
                    break;
            }
        } while(command != COMMAND_EXIT);
    }
}
