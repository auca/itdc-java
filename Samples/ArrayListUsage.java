import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListUsage {

	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> numbers = readNumber(scanner);

		System.out.println("До перестановки: ");
		printNumbers(numbers);

		reverseNumbers(numbers);
		
		System.out.println("После перестановки: ");
		printNumbers(numbers);
		
		insertZeros(numbers);
		
		System.out.println("После вставки нулей: ");
		printNumbers(numbers);
		
		removeOdd(numbers);
		
		System.out.println("После удаления четных: ");
		printNumbers(numbers);
	}

	private static void removeOdd(ArrayList<Integer> numbers) {
		for (int i = 0; i < numbers.size(); ++i) {
			if (numbers.get(i) % 2 != 0) {
				numbers.remove(i--);
			}
		}
	}

	private static void insertZeros(ArrayList<Integer> numbers) {
		for (int i = 0; i < numbers.size(); ++i) {
			if (numbers.get(i) % 2 == 0) {
				numbers.add(i++, 0);
			}
		}
	}

	private static ArrayList<Integer> readNumber(Scanner scanner) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		String line = scanner.nextLine();
		String[] tokens = line.split(" ");
		for (String token : tokens) {
			numbers.add(Integer.parseInt(token));
		}
		
		return numbers;
	}

	private static void printNumbers(ArrayList<Integer> numbers) {
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
	
	private static void reverseNumbers(ArrayList<Integer> numbers) {
		for (int i = 0, j = numbers.size() - 1; i < j; ++i, --j) {
			int temp = numbers.get(i);
			numbers.set(i, numbers.get(j));
			numbers.set(j, temp);
		}
	}

}
