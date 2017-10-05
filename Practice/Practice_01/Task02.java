public class Task02 {

    public static void main(String[] args) {
        // Вариант #1
        System.out.println("**************");
        System.out.println("*Привет, Мир!*");
        System.out.println("**************");

        // Вариант #2 (плохой с точки зрения стиля)
        System.out.println("**************\n*Привет, Мир!*\n**************");

        // Вариант #3: минимизация вызовов путем конкатенации строк
        System.out.println(
            "**************\n" +
            "*Привет, Мир!*\n" +
            "**************"
        );
    }

}

