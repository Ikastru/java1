package ru.progwards.java2.lessons.calculator;

/**
 * Посмотреть что со скобками - не работают
 */

import java.util.Scanner;

public class MyCalculator {
    public static void main(String[] args) {
        while (true) //Бесконечный цикл
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите выражение: ");
            String expr = in.nextLine();
            System.out.println(new RPN().calculate(expr));
        }
    }
}
