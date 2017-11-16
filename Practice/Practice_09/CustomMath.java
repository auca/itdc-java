public class CustomMath {

    public static int gcd(int a, int b) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("НОД для 0 и 0 нет");
        } else if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        }

        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }

        return b;
    }

}

