package ru.progwards.java1.lessons.collections;

/**
 1.1 Реализовать метод
 public static Collection<Integer> fillEven(int n) - создать коллекцию и заполнить последовательностью
 четных возрастающих чисел начиная с 2, количество элементов в коллекции n

 1.2 Реализовать метод
 public static Collection<Integer> fillOdd(int n) - создать коллекцию и заполнить последовательностью
 нечетных убывающих чисел, минимальное число в коллекции 1, количество элементов в коллекции n*3

 1.3 Реализовать метод
 public static Collection<Integer> fill3(int n) - создать коллекцию и заполнить ее тройками чисел.
 Каждая тройка создается по алгоритму: первое число тройки - индекс числа в коллекции, второе - индекс в квадрате, третье - индекс в кубе, количество элементов в коллекции n*3
 **/

import java.util.ArrayList;
import java.util.Collection;

public class Creator {

    public static Collection<Integer> fillEven(int n){
        Collection<Integer> collection = new ArrayList<>();
        for (int i=1; i<=n; i++){
            collection.add(2*i);
        }
        return collection;
    }

    public static Collection<Integer> fillOdd(int n){
        Collection<Integer> collection = new ArrayList<>();
        for (int i=3*n; i>=1; i--){
            if(i%2 != 0) {
                collection.add(i);
            }
        }
        return collection;
    }

    public static Collection<Integer> fill3(int n){
        Collection<Integer> collection = new ArrayList<>();
        for (int i=1; i<=3*n; i++) {
        collection.add(i);
        collection.add(i*i);
        collection.add(i*i*i);
        }
        return collection;
    }

}
