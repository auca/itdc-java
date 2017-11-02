import java.util.Scanner;

public class Task01 {

    private static final int MIN_DICE_VALUE = 1;
    private static final int MAX_DICE_VALUE = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N: ");

        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i) {
            System.out.printf("Введите элемент %d: ", i);
            numbers[i] = scanner.nextInt();
        }

        System.out.print("До: ");
        printNumbers(numbers);

        reverseNumbers(numbers);
        // numbers = createReversedNumbers(numbers);

        System.out.print("После: ");
        printNumbers(numbers);
    }

    private static void printNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; ++i) {
            System.out.print(numbers[i]);
            System.out.print(i != numbers.length - 1 ? " " : "\n");
        }
    }

    private static void reverseNumbers(int[] numbers) {
        for (int i = 0, j = numbers.length - 1; i < j; ++i, --j) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    private static int[] createReversedNumbers(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i <  numbers.length; ++i) {
            result[i] = numbers[numbers.length - i - 1];
        }

        return result;
    }

}

