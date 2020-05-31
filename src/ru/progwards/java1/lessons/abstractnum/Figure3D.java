package ru.progwards.java1.lessons.abstractnum;

/**
 * 4. Реализовать класс Figure3D, содержащий
 * 4.1 конструктор
 * public Figure3D(Number segment), который будет сохранять в классе значение длины отрезка.
 * Для куба это будет длина стороны, для шара - радиус
 *
 * 4.2 метод
 * public Number volume(), который будет возвращать объем фигуры. Для класса Figure3D это будет null;
 */

public class Figure3D {

    Number segment;

    public Figure3D(Number segment){
        this.segment = segment;
    }

    public Number volume(){
        return null;
    }
}
