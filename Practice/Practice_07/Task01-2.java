import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	/* Commands */

	private static final int COMMAND_PUT_PEN_DOWN     = 1;
	private static final int COMMAND_PUT_PEN_UP       = 2;
	private static final int COMMAND_TURN_RIGHT       = 3;
	private static final int COMMAND_TURN_LEFT        = 4;
	private static final int COMMAND_MOVE             = 5;
	private static final int COMMAND_PRINT_GAME_FIELD = 6;
	private static final int COMMAND_EXIT             = 9;

	/* Game Field */

	public static final int GAME_FIELD_WIDTH  = 20;
	public static final int GAME_FIELD_HEIGHT = 20;

	public static final char GAME_FIELD_EMPTY_CELL  = '.';
	public static final char GAME_FIELD_MARKED_CELL = '*';
	public static final char GAME_FIELD_TURTLE_CELL = 'T';

	/* Turtle */

	static int turtleX, turtleY;
	static int turtleDX, turtleDY;
	static boolean isTurtlesPenDown;

	/* Game Field */

	public static char[][] createGameField(int width, int height, char emptyCellValue) {
		char[][] gameField = new char[width][height];
		initGameField(gameField, width, height, emptyCellValue);

		return gameField;
	}

	public static void initGameField(char[][] gameField, int width, int height, char emptyCellValue) {
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				gameField[x][y] = emptyCellValue;
			}
		}
	}

	public static String convertGameFieldToString(char[][] gameField, char turtleCellValue) {
		StringBuilder result = new StringBuilder("");
		for (int y = 0; y < gameField.length; ++y) {
			for (int x = 0; x < gameField[0].length; ++x) {
				if (y == turtleY && x == turtleX) {
					result.append(turtleCellValue);
				} else {
					result.append(gameField[x][y]);
				}
			}
			result.append('\n');
		}

		return result.toString();
	}

	/* Turtle */

	public static void initTurtle() {
		turtleX = turtleY = 0;
		turtleDX = 1; turtleDY = 0;
		isTurtlesPenDown = false;
	}
	
	public static void putTurtlesPenDown() {
		isTurtlesPenDown = true;
	}
	
	public static void putTurtlesPenUp() {
		isTurtlesPenDown = false;
	}

	public static void turnTurtleRight() {
		int temp = turtleDX;
		turtleDX = turtleDY;
		turtleDY = temp;

		turtleDX *= -1;
	}
	
	public static void turnTurtleLeft() {
		int temp = turtleDX;
		turtleDX = turtleDY;
		turtleDY = temp;

		turtleDY *= -1;
	}
	
	public static void moveTurtle(int steps, char[][] gameField, char markedCellValue) {
		for (int i = 0; i < steps; ++i) {
			int nextX = turtleX + turtleDX;
			int nextY = turtleY + turtleDY;
			if (!(nextX >= 0 && nextX < gameField.length &&
				  nextY >= 0 && nextY < gameField[0].length)) {
				break;
			}

			if (isTurtlesPenDown) {
				gameField[turtleX][turtleY] = markedCellValue;
			}

			turtleX = nextX;
			turtleY = nextY;
		}
	}

	/* Custom Program Logic */

	public static void processCommand(char[][] gameField) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (!scanner.hasNextLine()) {
			throw new Exception("Строка команды не была введена.");
		}
		String commandLine = scanner.nextLine();
		Scanner commandScanner = new Scanner(commandLine);

		if (!commandScanner.hasNext()) {
			throw new Exception("Команда не была введена.");
		}
		int command;
		try {
			command = commandScanner.nextInt();
		} catch(InputMismatchException e) {
			throw new Exception("Команда должна быть числом.");
		}
		
		switch (command) {
			case COMMAND_PUT_PEN_DOWN:
				putTurtlesPenDown();
				break;
			case COMMAND_PUT_PEN_UP:
				putTurtlesPenUp();
				break;
			case COMMAND_TURN_RIGHT:
				turnTurtleRight();
				break;
			case COMMAND_TURN_LEFT:
				turnTurtleLeft();
				break;
			case COMMAND_MOVE:
				if (!commandScanner.hasNext()) {
					throw new Exception("Количество шагов не было введено.");
				}
				int steps;
				try {
					steps = commandScanner.nextInt();
				} catch(InputMismatchException e) {
					throw new Exception("Количество шагов должно быть числом.");
				}

				if (steps < 0) {
					throw new Exception("Количество шагов не может быть отрицательным.");
				}
				moveTurtle(steps, gameField, GAME_FIELD_MARKED_CELL);
				break;
			case COMMAND_PRINT_GAME_FIELD:
				System.out.println(
					convertGameFieldToString(
						gameField,
						GAME_FIELD_TURTLE_CELL
					)
				);
				break;
			case COMMAND_EXIT:
				System.exit(0);
			default:
				throw new Exception("Неверная команда. Попробуйте еще раз.");
		}	
	}
	
	public static void main(String[] args) {
		char[][] gameField =
			createGameField(
				GAME_FIELD_WIDTH,
				GAME_FIELD_HEIGHT,
				GAME_FIELD_EMPTY_CELL
			);
		initTurtle();

		while (true) {
			try {
				processCommand(gameField);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
