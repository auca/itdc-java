import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        int number = scanner.nextInt();

        int sum = 0;
        do {
            sum += number % 10;
            number /= 10;
        } while (number != 0);

        System.out.printf("Сумма всех его цифр равна %d%n", sum);
    }

}
