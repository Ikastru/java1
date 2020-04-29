package ru.progwards.java1.lessons.test;

public class FactorialNonRecurs {
    public static long factorial(int n){
        if (n<0) throw new IllegalArgumentException("n должно быть >= 0");
        long fact=1;
        int i=2;
        while (i<=n){
            fact *= i;
            i++;
        }
        return fact;
    }

    public static void main(String[] args) {
        System.out.println(factorial(20));
    }
}
