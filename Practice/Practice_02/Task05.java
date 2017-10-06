import java.util.Scanner;

public class Task05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите температуру в градусах Фаренгейта? ");
        double degreesFahrenheit = scanner.nextDouble();

        double degreesCelsius = (degreesFahrenheit - 32.0) * 5.0 / 9.0;
        System.out.printf("Температура в градусах Цельсия: %.2f\n", degreesCelsius);
    }

}

