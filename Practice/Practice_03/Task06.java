import java.util.Scanner;

public class Task06 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество баллов: ");
        int points = scanner.nextInt();

        if (points < 0 || points > 100) {
            System.err.printf("Значение %d является некоректным\n", points);
            System.exit(-1);
        } else {
            char grade;
            if (points >= 90) {
                grade = 'A';
            } else if (points >= 80) {
                grade = 'B';
            } else if (points >= 70) {
                grade = 'C';
            } else if (points >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            System.out.printf("Оценка: %c\n", grade);
        }
    }

}

