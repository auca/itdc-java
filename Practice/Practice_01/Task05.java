import java.util.Scanner;

public class Task05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение стороны a: ");
        int sideA = scanner.nextInt();
        System.out.print("Введите значение стороны b: ");
        int sideB = scanner.nextInt();
        System.out.print("Введите значение стороны c: ");
        int sideC = scanner.nextInt();

        int result = sideA * sideB * sideC;
        System.out.printf(
            "Объем параллелепипеда (%d x %d x %d) равен %d",
            sideA, sideB, sideC,
            result
        );
    }

}

