package ru.progwards.java2.lessons.recursion;

import java.util.HashSet;

public class AsNumbersSum {

    static void findCombinationsUtil(int[] arr, int index, int n, int red_num) {

        // Установить для хранения всех отдельныx элементов
        HashSet<Integer> s = new HashSet<>();
        int sum = 0;

        // Базовое условие
        if (red_num < 0) {
            return;
        }
        if (red_num == 0) {
            // Перебираем все элементы и сохраняем их в наборе
            for (int i = 0; i < index; i++) {
                s.add(arr[i]);
            }

            // Рассчитать сумму всех элементов множества
            for (Integer itr : s) {
                sum = sum + itr;
            }

            // Сравнить, равна ли сумма n или нет, если оно равно n вывести числа
            if (sum == n) {
                for (Integer i : s) {
                    System.out.println(i + " ");
                }
                System.out.println();
                return;
            }
        }

        // Найти предыдущий номер, сохраненный в массиве
        int prev = (index == 0) ? 1 : arr[index - 1];
        for (int k = prev; k <= n; k++) {
            // Сохраняем все числа рекурсивно\
            if (index < n) {
                arr[index] = k;
                findCombinationsUtil(arr, index + 1, n, red_num - k);
            }
        }
    }

    // Функция, чтобы найти все различные комбинации n
    static void findCombinations(int n) {
        int[] a = new int[n];
        findCombinationsUtil(a, 0, n, n);
    }

    public static void main(String[] arr) {
        int n = 10;
        findCombinations(n);
    }
}
