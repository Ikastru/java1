package ru.progwards.java2.lessons.recursion;

/**
 * Реализовать класс, AsNumbersSum, содержащий метод
 * public static String asNumbersSum(int number), который раскладывает параметр number,
 * как всевозможные уникальные комбинации сумм натуральных чисел, например:
 * 5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
 *
 * Строка должна содержать результат, отформатированный точно, как указано в примере. Повторные комбинации не допускаются,
 * например, если а строке уже есть 3+2, то 2+3 там быть не должно.
 * Задача должна быть решена методом рекурсии, циклы использовать запрещено.
 */
public class AsNumbersSum {

    static int[] iArr = new int[100];
    String rezult = "";

    public static String asNumbersSum(int number) {
        AsNumbersSum asNumbersSum = new AsNumbersSum();
        asNumbersSum.currSlag(number, number, 0);
        return asNumbersSum.rezult.substring(0, asNumbersSum.rezult.length() - 1);
    }

    void currSlag(int n, int k, int i) {
        if (n < 0)
            return;
        if (n == 0) {
            for (int j = 0; j < i; j++) {
                rezult += iArr[j] + "+";
            }
            rezult = rezult.substring(0, rezult.length() - 1) + "=";
        } else {
            if (n >= k) {
                iArr[i] = k;
                currSlag(n - k, k, i + 1);
            }
            if (k > 1)
                currSlag(n, k - 1, i);
        }
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(5));
    }
}