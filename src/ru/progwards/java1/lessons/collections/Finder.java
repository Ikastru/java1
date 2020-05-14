package ru.progwards.java1.lessons.collections;

/**
 * 2.1 Реализовать метод
 * public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) - найти 2 соседних числа
 * в коллекции сумма которых минимальна, вернуть коллекцию, содержащую индексы этих чисел
 *
 * 2.2 Реализовать метод
 * public static Collection<Integer> findLocalMax(Collection<Integer> numbers) - найти локальные максимумы
 * - числа, которые больше соседа справа и слева. Первый и последний элемент коллекции не может являться
 * локальным  максимумом, вернуть коллекцию, содержащую значения этих максимумов
 *
 * 2.3 Реализовать метод
 * public static boolean findSequence(Collection<Integer> numbers) - проверить, содержит ли коллекция
 * все числа от 1 до size(), порядок может быть произвольный
 *
 * 2.4 Реализовать метод
 *
 * public static String findSimilar(Collection<String> names) - найдите максимальное количество
 * повторяющихся подряд элементов. Результат вернуть в виде строки <элемент>:<количество>, например
 * Василий:5. При равенстве максимального количества у разных повторяющихся элементов, вернуть результат
 * для элемента, повторяющаяся последовательность которого началась с наименьшего индекса.
 */

import java.util.*;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        Integer[] arr = numbers.toArray(new Integer[numbers.size()]);
        Collection<Integer> arrayList = new ArrayList();
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < numbers.size(); i++){
            if(arr[i]+arr[i+1]<arr[i+2]+arr[i+1]){
                 temp1 = i;
                 temp2 = i+1;
            }
            arrayList.add(temp1);
            arrayList.add(temp2);
        }
        return arrayList;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        Integer[] arr = numbers.toArray(new Integer[numbers.size()]);
        Collection<Integer> arrayList = new ArrayList();
        for (int i = 0; i < numbers.size(); i++){
            if(arr[i] > arr[i+1] && arr[i] > arr[i-1]){
                arrayList.add(arr[i]);
            }
        }
        return arrayList;
    }

    public static boolean findSequence(Collection<Integer> numbers){
        Collection<Integer> arrayList = new ArrayList();
        for (int i = 0; i < numbers.size(); i++){
            arrayList.add(i);
        }
        if (numbers.containsAll(arrayList)){
            return true;
        }
        return false;
    }

    public static String findSimilar(Collection<String> names){
        String[] arr = names.toArray(new String[names.size()]);
        StringBuilder strB = new StringBuilder();
        int k=0, max=0;
        for (int i = 0; i <names.size(); i++) {
            if (arr[i+1].equals(arr[i])) {
                k++;
            }
            else
                k = 0;
            if (k > max) {
                strB.setLength(0);
                strB.append(arr[i]);
                max = k;
            }
        }
        return strB.toString()+max;
    }
}
