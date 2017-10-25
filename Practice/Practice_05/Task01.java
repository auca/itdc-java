import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите исходную координату ");
        int coordinate = scanner.nextInt();

        System.out.print("Введите первую точку ");
        int firstPoint = scanner.nextInt();
        System.out.print("Введите вторую точку ");
        int secondPoint = scanner.nextInt();

        int firstDistance = abs(firstPoint - coordinate);
        int secondDistance = abs(secondPoint - coordinate);

        if (firstDistance < secondDistance) {
            System.out.printf("Первая точка ближе второй. Дистанция равна %d%n", firstDistance);
        } else if (secondDistance < firstDistance) {
            System.out.printf("Вторая точка ближе первой. Дистанция равна %d%n", secondDistance);
        } else {
            System.out.printf("Дистанция равна %d%n", firstDistance);
        }
    }

    private static int abs(int x) {
        return x < 0 ? -x : x;
    }

}
