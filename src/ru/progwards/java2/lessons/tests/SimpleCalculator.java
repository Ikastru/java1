package ru.progwards.java2.lessons.tests;

/**
 * 1.1 Реализовать класс SimpleCalculator с методами
 * int sum(int val1, int val2);
 * int diff(int val1, int val2);
 * int mult(int val1, int val2);
 * int div(int val1, int val2);
 * класс поместить в package ru.progwards.java2.lessons.tests.calc
 *
 * 1.2 Создать параметризованные тесты для этих методов,
 * тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.
 *
 * 1.3 Написать для всех методов тесты для проверки соответствующих исключений,
 * тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.
 */

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

    public int div(int a, int b) throws ArithmeticException {
        if (b == 0)
            throw new ArithmeticException("Деление на ноль");
        long result = (long) a / b;
        return check(result);
    }
}

