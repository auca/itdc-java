
import java.util.Scanner;

public class Task03 {

    private static final int MIN_DICE_VALUE = 1;
    private static final int MAX_DICE_VALUE = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N: ");

        int n = scanner.nextInt();
        int[] counts = new int[2 * (MAX_DICE_VALUE - MIN_DICE_VALUE) + 1];

        for (int i = 0; i < n; ++i) {
            int sum = throwDice() + throwDice();
            ++counts[sum - 2];
        }

        for (int i = 0; i < counts.length; ++i) {
            System.out.printf("%d: %d%n", i + 2, counts[i]);
        }
    }

    private static int throwDice() {
        return (int) (MIN_DICE_VALUE + Math.random() * (MAX_DICE_VALUE - MIN_DICE_VALUE + 1));
    }

}

