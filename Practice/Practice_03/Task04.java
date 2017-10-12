import java.util.Scanner;

public class Task04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int firstNumber = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int secondNumber = scanner.nextInt();
        System.out.print("Введите третье число: ");
        int thirdNumber = scanner.nextInt();

        int greatestNumber;
        if (firstNumber > secondNumber) {
            if (firstNumber > thirdNumber) {
                greatestNumber = firstNumber;
            } else {
                greatestNumber = thirdNumber;
            }
        } else {
            if (secondNumber > thirdNumber) {
                greatestNumber = secondNumber;
            } else {
                greatestNumber = thirdNumber;
            }
        }

        System.out.printf("Значение %d является максимальным.\n", greatestNumber);
    }

}
