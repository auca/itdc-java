import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите вещественное число: ");
        double value = scanner.nextDouble();

        double result = value;
        if (result < 0) {
            result *= -1;
        }
        System.out.printf("|%.2f| = %.2f\n", value, result);
    }

}
