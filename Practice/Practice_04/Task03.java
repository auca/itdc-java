import java.util.Scanner;

public class Task02 {

    private static final int MIN_OPERAND_VALUE = 0;
    private static final int MAX_OPERAND_VALUE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество тестов: ");
        int tests = scanner.nextInt();

        int correctAnswers = 0, incorrectAnswers = 0;
        // int i = 0;
        // while (i < tests) {
        for (int i = 0; i < tests; ++i) {
            int firstOperand =
                MIN_OPERAND_VALUE + Math.random() *
                    (MAX_OPERAND_VALUE - MIN_OPERAND_VALUE + 1);
            int secondOperand =
                MIN_OPERAND_VALUE + Math.random() *
                    (MAX_OPERAND_VALUE - MIN_OPERAND_VALUE + 1);

            System.out.printf("%d + %d = ", firstOperand, secondOperand);
            int answer = Scanner.nextInt();

            if (firstOperand + secondOperand == answer) {
                ++correctAnswers;
            } else {
                ++incorrectAnswers;
            }

            // ++i
        }

        System.out.printf(
            "Количество правильных ответов: %d%n" +
            "Количество неправильных ответов: %d%n",
            correctAnswers,
            incorrectAnswers
        );
    }

}
