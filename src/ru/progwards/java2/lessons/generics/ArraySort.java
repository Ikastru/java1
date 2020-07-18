package ru.progwards.java2.lessons.generics;

/**
 * Реализовать статический метод с именем sort, сортирующий произвольный массив обобщающих типов,
 * по алгоритму из первого урока:
 */

import java.util.Arrays;

public class ArraySort<T> {

    private T[] a;

    public static <T extends Comparable> void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].compareTo(a[j]) > 0) {
                    T time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {163, -1, 15, 25};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

