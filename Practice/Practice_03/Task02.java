import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {
        double value = 0.0;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;
        value += 0.1;

        System.out.printf("%.2f\n", value);
        if (value == 1.0) {
            System.out.println("Равны");
        } else {
            System.out.println("Не равны");
        }
    }

}
