package ru.progwards.java1.lessons.test;

public class Echo {
    //Выводит строку набранную как аргумент программы (через точку к вызову программы) в консоли
    public static void main(String[] args) {
        int i=0;
        while (i<args.length){
            System.out.print(args[i] + " ");
        }
    }
}
