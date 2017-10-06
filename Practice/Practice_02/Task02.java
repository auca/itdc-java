import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Первое значение: ? ");
        int a = scanner.nextInt();

        System.out.print("Второе значение: ? ");
        int b = scanner.nextInt();

        System.out.printf("До перестановки: a = %d; b = %d;\n", a, b);

        a += b;
        b = a - b;
        a -= b;

        System.out.printf("После перестановки: a = %d; b = %d;\n", a, b);
    }

}

