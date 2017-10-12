import java.util.Scanner;

public class Task03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int firstNumber = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int secondNumber = scanner.nextInt();

        if (firstNumber > secondNumber) {
            System.out.printf(
                "Первое число %d больше второго числа %d.\n",
                firstNumber, secondNumber
            );
        } else {
            System.out.printf(
                "Второе число %d больше или равно первого числа %d.\n",
                firstNumber, secondNumber
            );
        }

    }

}
