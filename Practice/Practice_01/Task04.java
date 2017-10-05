import java.util.Scanner;

public class Task04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ? ");
        int firstNumber = scanner.nextInt();

        System.out.print("Введите второе число: ? ");
        int secondNumber = scanner.nextInt();

        int result = firstNumber + secondNumber;
        System.out.println(firstNumber + " + " + secondNumber + " = " + result);
    }

}

