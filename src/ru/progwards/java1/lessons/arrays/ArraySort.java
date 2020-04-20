package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {

    private int[] a;

    public static void sort(int[] a){
        for (int i=0; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if(a[i] > a[j]) {
                    int time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }

//    public static void sort(int[] a){
//        for (int out = a.length - 1; out >= 1; out--){
//            for (int in = 0; in < out; in++){
//                if(a[in] > a[in + 1]) {
//                    int time = a[in];
//                    a[in] = a[in+1];
//                    a[in+1] = time;
//                }
//            }
//        }
//    }

       public static void main(String[] args) {
        int[] a={163, -1, 15, 25};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}





