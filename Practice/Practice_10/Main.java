import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
//		Scanner scanner = new Scanner(System.in);
//		ArrayListInt numbers = readNumber(scanner);
//
//		System.out.println("До перестановки: ");
//		printNumbers(numbers);
//
//		reverseNumbers(numbers);
//
//		System.out.println("После перестановки: ");
//		printNumbers(numbers);
//		
//		insertZeros(numbers);
//		
//		System.out.println("После вставки нулей: ");
//		printNumbers(numbers);
//		
//		removeOdd(numbers);
//		
//		System.out.println("После удаления нечетных: ");
//		printNumbers(numbers);
//		
//		System.out.println(numbers.size());
		
		final int NUMBER_OF_ELEMENTS = 100000000;
		final int NANOSECONDS_IN_MSECONDS = 1000000;
		ArrayListInt numbers = new ArrayListInt();
		
		long startTime = System.nanoTime();
		for (int i = 0; i < NUMBER_OF_ELEMENTS; ++i) {
			numbers.add(i);
		}
		long endTime = System.nanoTime();
		long deltaTime = endTime - startTime;
		long deltaTimeInMs = deltaTime / NANOSECONDS_IN_MSECONDS;
		
		System.out.printf("Время для нашего динам. списка: %d%n", deltaTimeInMs);
		
		ArrayList<Integer> otherNumbers = new ArrayList<Integer>();

		startTime = System.nanoTime();
		for (int i = 0; i < NUMBER_OF_ELEMENTS; ++i) {
			otherNumbers.add(i);
		}
		endTime = System.nanoTime();
		deltaTime = endTime - startTime;
		deltaTimeInMs = deltaTime / NANOSECONDS_IN_MSECONDS;
		
		System.out.printf("Время для Java динам. списка: %d%n", deltaTimeInMs);
	}

	private static void removeOdd(ArrayListInt numbers) {
		for (int i = 0; i < numbers.size(); ++i) {
			if (numbers.get(i) % 2 != 0) {
				numbers.remove(i--);
			}
		}
	}

	private static void insertZeros(ArrayListInt numbers) {
		for (int i = 0; i < numbers.size(); ++i) {
			if (numbers.get(i) % 2 == 0) {
				numbers.add(i++, 0);
			}
		}
	}

	private static ArrayListInt readNumber(Scanner scanner) {
		ArrayListInt numbers = new ArrayListInt();
		
		String line = scanner.nextLine();
		String[] tokens = line.split(" ");
		for (String token : tokens) {
			numbers.add(Integer.parseInt(token));
		}
		
		return numbers;
	}

	private static void printNumbers(ArrayListInt numbers) {
		for (int i = 0; i < numbers.size(); ++i) {
			System.out.print(numbers.get(i) + " ");
		}
		System.out.println();
	}
	
	private static void reverseNumbers(ArrayListInt numbers) {
		for (int i = 0, j = numbers.size() - 1; i < j; ++i, --j) {
			int temp = numbers.get(i);
			numbers.set(i, numbers.get(j));
			numbers.set(j, temp);
		}
	}

}
