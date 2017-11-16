import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Rational a = Rational.parse(scanner.nextLine());
            Rational b = Rational.parse(scanner.nextLine());
            System.out.println(a.add(b));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO: доделать задание самостоятельно
    }

}
