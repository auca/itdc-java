import java.util.Scanner;

public class Task10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер года: ");
        int year = scanner.nextInt();

        System.out.print("Введите номер месяца: ");
        int month = scanner.nextInt();

        int days = 28;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days += 3;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days += 2;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    days++;
                }
                break;
            default:
                System.err.printf("Номер месяца %d является некоректным\n", month);
                System.exit(-1);
        }

        System.out.println(days);
    }

}
