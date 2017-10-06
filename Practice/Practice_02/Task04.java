import java.util.Scanner;

public class Task04 {

    public static void main(String[] args) {
        final double CM_IN_INCHES = 2.54;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите значение в дюймах? ");
        double inches = scanner.nextDouble();

        double centimeters = inches * CM_IN_INCHES;
        System.out.printf("%.2f дюйм. = %.2f см.\n", inches, centimeters);
    }

}

