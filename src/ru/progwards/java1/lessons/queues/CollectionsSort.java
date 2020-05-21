package ru.progwards.java1.lessons.queues;

/**
 * Сравнение методов сортировки коллекций
 * 1.1 Реализовать метод public static void mySort(Collection<Integer> data) -
 * переделать алгоритм из класса ArraySort из ДЗ про массивы, на коллекции.
 * Не использовать встроенные методы сортировок
 * <p>
 * 1.2 Реализовать метод public static void minSort(Collection<Integer> data) по
 * следующему алгоритму
 * - создать новую коллекцию
 * - найти минимальный элемент с использованием функции min()
 * - переместить его в новую коллекцию
 * - делать так до тех пор, пока все элементы не окажутся в новой коллекции
 * - скопировать новую коллекцию в старую
 * <p>
 * 1.3 Реализовать метод public static void collSort(Collection<Integer> data) используя
 * метод sort из Collections
 * <p>
 * 1.4 Реализовать метод public static Collection<String> compareSort() в котором
 * сравнить производительность методов и вернуть их имена, отсортированные в порядке
 * производительности, первый - самый быстрый. В случае равенства производительности
 * каких-то методов, возвращать их названия в алфавитном порядке.
 */


import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        Integer[] arr = data.toArray(new Integer[data.size()]);
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (arr[i] > arr[j]) {
                    Integer time = arr[i];
                    arr[i] = arr[j];
                    arr[j] = time;
                }
            }
        }

    }

    public static void minSort(Collection<Integer> data) {
        ArrayList<Integer> arrayList = new ArrayList();
        Integer[] min = new Integer[data.size()];
        for (int i = 0; i < min.length; i++) {
            min[i] = Collections.min(data);
            data.remove(min[i]);
            arrayList.add(min[i]);
        }
        data.addAll(arrayList);
    }

    static void collSort(Collection<Integer> data) {
        ArrayList<Integer> arrayListSort = new ArrayList(data);
        Collections.sort(arrayListSort);
        data.clear();
        data.addAll(arrayListSort);
    }

    public static Collection<String> compareSort() {
        final int ELEMENTS_COUNT = 1000;
        ArrayList<Integer> arrayListExample = new ArrayList();
        ArrayList<String> arrayListString = new ArrayList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            arrayListExample.add(i);
        }
        var startTime1 = new Date().getTime();
        mySort(arrayListExample);
        var resTime1 = new Date().getTime() - startTime1;

        var startTime2 = new Date().getTime();
        minSort(arrayListExample);
        var resTime2 = new Date().getTime() - startTime2;

        var startTime3 = new Date().getTime();
        collSort(arrayListExample);
        var resTime3 = new Date().getTime() - startTime3;

        class FuncSort {
            public String str;
            public long resTime;

            public FuncSort(String str, long resTime) {
                this.str = str;
                this.resTime = resTime;
            }
        }

        FuncSort funcSortMy = new FuncSort("mySort", resTime1);
        FuncSort funcSortMin = new FuncSort("minSort", resTime2);
        FuncSort funcSortColl = new FuncSort("collSort", resTime3);
        Comparator<FuncSort> awesomeComparator = new Comparator<FuncSort>() {
            @Override
            public int compare(FuncSort o1, FuncSort o2) {
                return (int) (o1.resTime - o2.resTime);
            }
        };
        ArrayList<FuncSort> arrayListF = new ArrayList();
        arrayListF.add(funcSortMy);
        arrayListF.add(funcSortMin);
        arrayListF.add(funcSortColl);
        Collections.sort(arrayListF, awesomeComparator);
        for (FuncSort f : arrayListF) {
            arrayListString.add(f.str);
        }
        System.out.println(resTime1);
        System.out.println(resTime2);
        System.out.println(resTime3);
        return arrayListString;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(163);
        arrayList.add(-1);
        arrayList.add(15);
        arrayList.add(25);
        System.out.println(arrayList);
        minSort(arrayList);
        System.out.println(arrayList);

        System.out.println(compareSort());
    }
}
