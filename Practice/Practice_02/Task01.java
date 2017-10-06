import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Первое значение: ? ");
        int a = scanner.nextInt();

        System.out.print("Второе значение: ? ");
        int b = scanner.nextInt();

        System.out.printf("До перестановки: a = %d; b = %d;\n", a, b);

        int temp = a;
        a = b;
        b = temp;

        System.out.printf("После перестановки: a = %d; b = %d;\n", a, b);
    }

}

