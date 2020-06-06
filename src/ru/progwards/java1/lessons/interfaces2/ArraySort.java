package ru.progwards.java1.lessons.interfaces2;

import java.util.Arrays;

public class ArraySort  {

    private Number[] a;

    public static void sort(Number[] a){
        for (int i=0; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if(a[i].compareTo(a[j])==1) {
                    Number time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }

    public static void main(String[] args) {
        Number[] a={new IntNumber(163), new IntNumber(-1), new IntNumber(15), new IntNumber(25)};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

