package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

/**
 * Реализовать класс по типу BlockArray - обобщающий динамический массив, растущий блоками, на основе обычного статического массива. Стратегия роста - при полном заполнении текущего объема, новый размер массива должен быть в 2 раза больше предыдущего.
 *
 * 3.1 в классе разместить private переменную - массив обобщающего типа.
 *
 * 3.2 конструктор - по умолчанию.
 *
 * 3.2 метод add - добавляет элемент в конец массива.
 *
 * 3.3 метод с именем insert - добавляет элемент в заданную позицию позицию массива. Параметр int pos - первый, параметр с элементом массива - второй.
 *
 * 3.4 метод remove(int pos) - удаляет элемент в позиции pos массива.
 *
 * 3.5 метод с get(int pos) - возвращает элемент по индексу pos.
 *
 * 3.6 метод с size() - возвращает текущий реальный объем массива.
 *
 */

public class DynamicArray<T> {
    private T[] array = (T[])new Object[10];
    private int count = 1;
    private int size = 10;

    public void proverka(){
        if (count >= size+1){
            size *=2;
            T[] array = (T[])new Object[size];
        }
    }

    public void add (T a){
        proverka();
        array[count-1] = a;
        count++;
    }

    public void insert(int pos, T elem){
        proverka();
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, pos);
        System.arraycopy(array, pos, newArray, pos+1, count-pos);
        array = (T[]) newArray;
        array[pos] = elem;
        size++;
        count++;
    }

    public void remove(int pos){
        int sizeNew = size - 1;
        if (pos == 0) {
            System.arraycopy(array, 1, array, 0, sizeNew);
        }
        else {
            if (pos == sizeNew)
                array[sizeNew] = null;
            else {
                System.arraycopy(array, pos+1, array, pos, count-pos);
            }
        }
        size--;
        count--;
    }

    public T get(int pos){
        if (pos >= 0 && pos < size)
            return array[pos];
        else
            return null;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(5);
        dynamicArray.add(10);
        dynamicArray.add(7);
        dynamicArray.insert(2, 15);
        System.out.println(Arrays.toString(dynamicArray.array));
        dynamicArray.remove(2);
        System.out.println(Arrays.toString(dynamicArray.array));
    }


}

