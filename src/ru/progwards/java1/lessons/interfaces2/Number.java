package ru.progwards.java1.lessons.interfaces2;

/**
 Классы Number, IntNumber, DoubleNumber, ArraySort
 Для классов Number, IntNumber и DoubleNumber из задачи A4.1 реализовать интерфейс Comparable.
 1 в классе Number должна быть абстрактная реализация
 2 в классе IntNumber - реализация для целых чисел
 3 в классе DoubleNumber - реализация для чисел с плавающей точкой

 4 Для класса ArraySort из задачи 2 урока 6 переделать сортировку массива целых чисел на сортировку Comparable<Number>
 public static void sort(Comparable<Number>[] a)
 */

public abstract class Number implements Comparable<Number> {

    @Override
    public abstract int compareTo(Number num);

    public Number mul(Number num){
        return null;
    }

    public Number div(Number num){
        return null;
    }

    public Number newNumber(String strNum){
        return null;
    }

    public String toString(){
        return null;
    }

    public Integer toInt(){
        return null;
    }

    public Double toDub(){
        return null;
    }
}
