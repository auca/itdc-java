import java.io.IOException;
import java.util.Scanner;

public class Main {
	static final int[] DAYS = {
        0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
 // month: 1   2  3   4   5   6   7   8   9   10  11  12
	};

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Месяц: ");
			if (!scanner.hasNext()) {
				break;
			}
			int month = scanner.nextInt();
	
			System.out.print("Год: ");
			if (!scanner.hasNext()) {
				break;
			}
			int year = scanner.nextInt();
	
			try {
				System.out.println("Дней: " + getDaysInMonth(year, month));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static int getDaysInMonth(int year, int month) {
		int days;
		if (month == 2) {
			days = isLeapYear(year) ? 29 : 28;
		} else if (month >= 1 && month <= 12) {
			days = DAYS[month];
		} else {
			throw new IllegalArgumentException(
				"Произошла ошибка при расчете. Введите число от 1 до 12.\n"
			);
		}
 
        	return days;
	}

	public static boolean isLeapYear(int year) {
        	return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

}
