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
    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{{1}, {2,3,4,5,},{6,7},{8,9,10,11,12,}};
        MatrixIterator i = new MatrixIterator(array);
        for (;i.hasNext();) {
            System.out.println(i.next());
        }
    }
}
