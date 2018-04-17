public class Rational {

    private int numerator,
                denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Знаменатель не может быть равен нулю");
        }

        int gcd = CustomMath.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational add(Rational other) {
        return new Rational(
            numerator * other.denominator +
                denominator * other.numerator,
            denominator * other.denominator
        );
    }

    public Rational subtract(Rational other) {
        return new Rational(
            numerator * other.denominator -
                denominator * other.numerator,
            denominator * other.denominator
        );
    }

    public Rational multiply(Rational other) {
        return new Rational(
            numerator * other.numerator,
            denominator * other.denominator
        );
    }

    public Rational divide(Rational other) {
        return new Rational(
            numerator * other.denominator,
            denominator * other.numerator
        );
    }

    public static Rational parseRational(String line) throws NumberFormatException, Exception {
        int delimeterPosition = line.indexOf('/');
        if (delimeterPosition <= -1) {
            return new Rational(Integer.parseInt(line), 1);
        }

        return new Rational(
            Integer.parseInt(line.substring(0, delimeterPosition)),
            Integer.parseInt(line.substring(delimeterPosition + 1))
        );
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

}
