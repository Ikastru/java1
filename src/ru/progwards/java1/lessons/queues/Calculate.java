package ru.progwards.java1.lessons.queues;

/**
 * Class Calculate, который содержит методы:
 *
 * 3.7 public static double calculation1(), возвращающую результат вычисления
 * следующей формулы:
 *
 * 2.2*(3+12.1), используя класс StackCalc
 *
 *
 * 3.8 public static double calculation2(), возвращающую результат вычисления
 * следующей формулы:
 *
 * (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
 */

public class Calculate {

    private static StackCalc stackCalc1 = new StackCalc();

    public static double calculation1(){
        stackCalc1.clearAll();
        stackCalc1.push(12.1);
        stackCalc1.push(3.0);
        stackCalc1.add();
        stackCalc1.push(2.2);
        stackCalc1.mul();
        return stackCalc1.pop();
    }

    public static double calculation2(){
        stackCalc1.clearAll();
        stackCalc1.push(12.1);
        stackCalc1.push(55.6);
        stackCalc1.sub();
        double expr1 = stackCalc1.pop();
        stackCalc1.clearAll();
        stackCalc1.push(24.0);
        stackCalc1.push(737.22);
        stackCalc1.add();
        double expr2 = stackCalc1.pop();
        stackCalc1.clearAll();
        stackCalc1.push(expr1);
        stackCalc1.push(expr2);
        stackCalc1.div();
        double expr3 = stackCalc1.pop();
        stackCalc1.clearAll();
        stackCalc1.push(9.2);
        stackCalc1.push(13.001);
        stackCalc1.sub();
        stackCalc1.push(2.0);
        stackCalc1.mul();
        stackCalc1.push(87.0);
        stackCalc1.add();
        double expr4 = stackCalc1.pop();
        stackCalc1.clearAll();
        stackCalc1.push(3.33);
        stackCalc1.push(19.0);
        stackCalc1.sub();
        stackCalc1.push(expr4);
        stackCalc1.mul();
        stackCalc1.push(expr3);
        stackCalc1.add();
        return stackCalc1.pop();
    }

}
