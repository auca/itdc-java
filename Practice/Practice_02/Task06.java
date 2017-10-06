import java.util.Scanner;

public class Task06 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите минимальное значение? ");
        int minimum = scanner.nextInt();
        System.out.print("Введите максимальное значение? ");
        int maximum = scanner.nextInt();

        int random = (int) (minimum + Math.random() * (maximum - minimum + 1));
        System.out.printf("Случайно число от %d до %d: %d\n", minimum, maximum, random);
    }

}

