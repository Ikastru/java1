package ru.progwards.java1.lessons.test;

public class EchoReverse {
    //Выводит строку набранную как аргумент программы (через точку к вызову программы) в консоли в обратном порядке
    public static void main(String[] args) {
        for (int i=args.length-1; i>=0; i--){
            for (int j=args[i].length()-1; j>=0; j--){
                System.out.println(args[i].charAt(j));
            }
        }
    }
}
