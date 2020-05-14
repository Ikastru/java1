package ru.progwards.java1.lessons.collections;

/**
 * Сделать итератор по одномерному массиву, реализовать методы hasNext() и next()
 */

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int currentIndex = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length && array[currentIndex] != null;
    }

    @Override
    public T next() {
        return array[currentIndex++];
    }
    public static void main(String[] args) {
        Integer[]array = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        ArrayIterator i = new ArrayIterator(array);
        for (;i.hasNext();) {
            System.out.println(i.next());
        }
    }

}
