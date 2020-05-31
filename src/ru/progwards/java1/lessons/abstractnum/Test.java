package ru.progwards.java1.lessons.abstractnum;

/**
 * 7. Реализовать класс Test вычисляющий, с методом main
 * 7.1 Объем куба в целых числах, со стороной 3
 * 7.2 Объем куба в double, со стороной 3
 * 7.3 Объем пирамиды в целых числах, со стороной 3
 * 7.4 Объем пирамиды в double, со стороной 3
 * 7.5 Вывести вычисленные значения на консоль
 */

public class Test {
    public static void main(String[] args) {
        Cube cubeInt = new Cube(new IntNumber(3));
        System.out.println(cubeInt.volume());
        Cube cubeDub = new Cube(new DoubleNumber(3));
        System.out.println(cubeDub.volume());
        Pyramid pyramidInt = new Pyramid(new IntNumber(3));
        System.out.println(pyramidInt.volume());
        Pyramid pyramidDub = new Pyramid(new DoubleNumber(3));
        System.out.println(pyramidDub.volume());
    }
}
