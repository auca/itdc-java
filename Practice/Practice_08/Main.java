import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {
    private static final int COMMAND_PUT_PEN_DOWN = 1,
                             COMMAND_PUT_PEN_UP   = 2,
                             COMMAND_TURN_RIGHT   = 3,
                             COMMAND_TURN_LEFT    = 4,
                             COMMAND_MOVE         = 5,
                             COMMAND_PRINT_FIELD  = 6,
                             COMMAND_EXIT         = 9;

    private static final int FIELD_WIDTH  = 20,
                             FIELD_HEIGHT = 20;

    private static Field field = new Field(FIELD_WIDTH, FIELD_HEIGHT);
    private static Turtle turtle = new Turtle(field);

    private static void processCommand(int[] commandAndParams) {
        int command = commandAndParams[0];
        switch (command) {
            case COMMAND_PUT_PEN_DOWN:
                turtle.putPenDown();
                break;
            case COMMAND_PUT_PEN_UP:
                turtle.putPenUp();
                break;
            case COMMAND_TURN_RIGHT:
                turtle.turnRight();
                break;
            case COMMAND_TURN_LEFT:
                turtle.turnLeft();
                break;
            case COMMAND_MOVE:
                int steps = commandAndParams[1];
                turtle.move(steps);
                break;
            case COMMAND_PRINT_FIELD:
                System.out.print(field.toString(new Turtle[] {
                    turtle
                }));
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
                throw new IllegalArgumentException("Команда должна быть числом");
            } catch(NoSuchElementException e) {
                throw new IllegalArgumentException("Команда не была введена");
            }

            if (command == COMMAND_MOVE) {
                try {
                    steps = lineScanner.nextInt();
                } catch(InputMismatchException e) {
                    throw new IllegalArgumentException("Количество шагов должно быть числом");
                } catch(NoSuchElementException e) {
                    throw new IllegalArgumentException("Количество шагов не введено");
                }

                if (steps < 0) {
                    throw new IllegalArgumentException("Количество шагов не может быть отрицательным");
                }
            }

            if (command != COMMAND_MOVE) {
                processCommand(new int[] { command });
            } else {
                processCommand(new int[] { command, steps });
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            processInput(scanner);
        } catch (Exception e) {
            System.out.println("Произошла ошибка: \n" + e.getMessage());
        }
    }

    /*
       public static void main(String[] args) {
            Field field = new Field(FIELD_WIDTH, FIELD_HEIGHT);
            Turtle firstTurtle = new Turtle(field);
            Turtle secondTurtle = new Turtle(field, 5, 5);
            Turtle thirdTurtle = new Turtle(field, 6, 2);
            firstTurtle.putPenDown();
            firstTurtle.move(10);
            secondTurtle.turnRight();
            secondTurtle.putPenDown();
            secondTurtle.move(10);
            thirdTurtle.turnLeft();
            thirdTurtle.move(5);
            System.out.print(field.printField(new Turtle[] {
                firstTurtle, secondTurtle, thirdTurtle
            }));
       }
     */

}
