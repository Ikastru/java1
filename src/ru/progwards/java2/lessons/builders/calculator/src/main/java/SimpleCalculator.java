public class SimpleCalculator {
    static int check(long var) {
        if (var > Integer.MAX_VALUE || var < Integer.MIN_VALUE)
            throw new ArithmeticException("Произошло переполнение размера по типу данных");
        return (int) var;
    }

    public static int sum(int a, int b) throws ArithmeticException {
        long result = (long) a + b;
        return check(result);
    }

    public static int diff(int a, int b) throws ArithmeticException {
        long result = (long) a - b;
        return check(result);
    }

    public static int mult(int a, int b) throws ArithmeticException {
        long result = (long) a * b;
        return check(result);
    }

    public static int div(int a, int b) throws ArithmeticException {
        if (b == 0)
            throw new ArithmeticException("Деление на ноль");
        long result = (long) a / b;
        return check(result);
    }
}
