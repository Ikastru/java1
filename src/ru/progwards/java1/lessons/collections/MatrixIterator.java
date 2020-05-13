package ru.progwards.java1.lessons.collections;

/**
 * Сделать итератор MatrixIterator по двумерному массиву (матрице), который разворачивает
 * матрицу в линейную последовательность построчно:
 * a[0][0], a[0][1], ...a[0][N],a[1][0], a[1][1]...a[1][N]... a[M][N]
 *
 * Шаблон для итератора взять от ArrayIterator
 */

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private T[][] array;
    private int i, j;

    public MatrixIterator(T[][] m) {
        this.array = m;
    }

    @Override
    public boolean hasNext() {
        return i < array.length && j < array[i].length;
    }

    @Override
    public T next() {
        T r = array[i][j++];
        if (j >= array[i].length) {
            i++;
            j = 0;
        }
        return r;
    }
}
