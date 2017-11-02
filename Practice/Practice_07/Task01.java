import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Task01 {

    /* Commands */

    private static final int COMMAND_PUT_PEN_DOWN = 1;
    private static final int COMMAND_PUT_PEN_UP   = 2;
    private static final int COMMAND_TURN_RIGHT   = 3;
    private static final int COMMAND_TURN_LEFT    = 4;
    private static final int COMMAND_MOVE         = 5;
    private static final int COMMAND_PRINT_FIELD  = 6;
    private static final int COMMAND_EXIT         = 9;

    /* Field */

    private static final char EMPTY_CELL_SYMBOL  = '.';
    private static final char MARKED_CELL_SYMBOL = '*';
    private static final char TURTLE_SYMBOL      = 'T';

    private static final int FIELD_WIDTH  = 20;
    private static final int FIELD_HEIGHT = 20;

    private static char[][] field = new char[FIELD_HEIGHT][FIELD_WIDTH];

    /* Turtle */

    private static int  x,  y,
                        dx, dy;

    private static boolean isPenDown = false;

    /* Field */

    private static void initField() {
        for (int i = 0; i < FIELD_HEIGHT; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                field[i][j] = EMPTY_CELL_SYMBOL;
            }
        }
    }

    private static String printField() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < FIELD_HEIGHT; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                if (y == i && x == j) {
                    result.append(TURTLE_SYMBOL);
                } else {
                    result.append(field[i][j]);
                }
            }
            result.append("\n");
        }
        result.append("\n");

        return result.toString();
    }

    /* Turtle */

    private static void initTurtle() {
        x = y = 0;
        dx = 1;
        dy = 0;
    }

    private static void putPenDown() {
        isPenDown = true;
    }

    private static void putPenUp() {
        isPenDown = false;
    }

    private static void turnLeft() {
        int temp = dx;
        dx = -dy;
        dy = temp;
    }

    private static void turnRight() {
        int temp = dx;
        dx = dy;
        dy = -temp;
    }

    private static boolean isInside(int x, int y) {
        return (x >= 0 && x < field[0].length) &&
               (y >= 0 && y < field.length);
    }

    private static void move(int steps) {
        for (int i = 0; i < steps; ++i) {
            if (isPenDown) {
                field[y][x] = MARKED_CELL_SYMBOL;
            }

            int nextX = x + dx;
            int nextY = y + dy;

            if (!isInside(nextX, nextY)) {
                break;
            }

            x = nextX;
            y = nextY;
        }
    }

    /* Command and Input */

    private static void processCommand(int[] commandAndParams) {
        int command = commandAndParams[0];
        switch (command) {
            case COMMAND_PUT_PEN_DOWN:
                putPenDown();
                break;
            case COMMAND_PUT_PEN_UP:
                putPenUp();
                break;
            case COMMAND_TURN_RIGHT:
                turnRight();
                break;
            case COMMAND_TURN_LEFT:
                turnLeft();
                break;
            case COMMAND_MOVE:
                int steps = commandAndParams[1];
                move(steps);
                break;
            case COMMAND_PRINT_FIELD:
                System.out.print(printField());
                break;
            case COMMAND_EXIT:
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Неверный код команды");
        }
    }

    private static void processInput(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            int command; int steps = 0;
            try {
                command = lineScanner.nextInt();
            } catch(InputMismatchException e) {
                throw new IllegalArgumentException("команда должна быть числом");
            } catch(NoSuchElementException e) {
                throw new IllegalArgumentException("команда не была введена");
            }

            if (command == COMMAND_MOVE) {
                try {
                    steps = lineScanner.nextInt();
                } catch(InputMismatchException e) {
                    throw new IllegalArgumentException("количество шагов должно быть числом");
                } catch(NoSuchElementException e) {
                    throw new IllegalArgumentException("количество шагов не введено");
                }

                if (steps < 0) {
                    throw new IllegalArgumentException("количество шагов не может быть отрицательным");
                }
            }

            if (command != COMMAND_MOVE) {
                processCommand(new int[] { command });
            } else {
                processCommand(new int[] { command, steps });
            }
        }
    }

    /* Entry Point */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initField();
        initTurtle();

        /*
         * Измените программу добавив вторую черепашку на поле
         */

        try {
            processInput(scanner);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}

