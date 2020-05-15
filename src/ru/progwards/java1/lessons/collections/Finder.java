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
        Object[] arr = numbers.toArray();
        Collection<Integer> arrayList = new ArrayList();
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 2; i < numbers.size(); i++){
            if(((Integer)arr[i-2] + (Integer)arr[i - 1]) < ((Integer)arr[i] + (Integer)arr[i - 1])){
                 temp1 = i-2;
                 temp2 = i-1;
            }
        }
        arrayList.add(temp1);
        arrayList.add(temp2);
        return arrayList;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        Object[] arr = numbers.toArray();
        Collection<Integer> arrayList = new ArrayList();
        for (int i = 2; i < numbers.size(); i++){
            if((Integer)arr[i-1] > (Integer)arr[i] && (Integer)arr[i-1] > (Integer)arr[i-2]){
                arrayList.add((Integer)arr[i-1]);
            }
        }
        return arrayList;
    }

    public static boolean findSequence(Collection<Integer> numbers){
        Collection<Integer> arrayList = new ArrayList();
        for (int i = 1; i <= numbers.size(); i++){
            arrayList.add(i);
        }
        if (numbers.containsAll(arrayList)){
            return true;
        }
        return false;
    }

    public static String findSimilar(Collection<String> names){
        Object[] arr = names.toArray();
        int a = arr.length;
        StringBuilder strB = new StringBuilder();
        int k=0, max=0;
        for (int i = 1; i <names.size(); i++) {
            if (arr[i].equals(arr[i-1])) {
                k++;
            }
            else
                k = 0;
            if (k > max) {
                strB.setLength(0);
                strB.append(arr[i-1]);
                max = k;
            }
        }
        return strB.toString()+":"+(max+1);
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i=0; i<11; i++){
            numbers.add(i);
        }
        ArrayList<String> name = new ArrayList<>();
        for (int i=0; i<20; i++){
            name.add("Вася"); name.add("Леша"); name.add("Петя"); name.add("Саша"); name.add("Вася");
            name.add("Вася"); name.add("Вася"); name.add("Петя"); name.add("Дима"); name.add("Андрей");
            name.add("Ваня"); name.add("Света"); name.add("Люда"); name.add("Ваня"); name.add("Петя");
            name.add("Дима"); name.add("Катя"); name.add("Светозар"); name.add("Вася"); name.add("Паша");
        }
        System.out.println(findMinSumPair(numbers));
        System.out.println(findLocalMax(numbers));
        System.out.println(findSequence(numbers));
        System.out.println(findSimilar(name));
    }
}
