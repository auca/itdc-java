import java.util.Scanner;

public class Task03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите четырёхзначное число? ");
        int number = scanner.nextInt();

        int sum = number / 1000 + number % 10 + number / 100 % 10 + number / 10 % 10;
        System.out.print("Сумма всех цифр числа " + number + " равна " + sum);
    }

}

