package ru.progwards.java1.lessons.collections;

/**
 * Сделать итератор по одномерному массиву, реализовать методы hasNext() и next()
 */

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int currentIndex = 0;
    private int currentSize = array.length;

    ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < currentSize && array[currentIndex] != null;
    }

    @Override
    public T next() {
        return array[currentIndex++];
    }

}
