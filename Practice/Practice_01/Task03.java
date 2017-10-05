import java.util.Scanner;

public class Task03 {

    public static void main(String[] args) {
        System.out.print("Как вас зовут? ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Привет, " + name);
    }

}

