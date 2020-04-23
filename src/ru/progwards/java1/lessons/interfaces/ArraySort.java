package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public class ArraySort {

    public static void sort(CompareWeight[] a){
        for (int i=0; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if(a[i].compareWeight(a[j]) == CompareWeight.CompareResult.GREATER) {
                    CompareWeight time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }

}
