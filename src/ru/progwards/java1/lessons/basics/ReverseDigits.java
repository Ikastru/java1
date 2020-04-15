package ru.progwards.java1.lessons.basics;

public class ReverseDigits {

    public static int reverseDigits(int number){
        int a=number/100;
        int b=number%100;
        int c=b/10;
        int d=b%10;
       return d*100+c*10+a;
    }
    public static void main(String[] args) {
        int number = 123;
        System.out.println(reverseDigits(number));
    }
}
