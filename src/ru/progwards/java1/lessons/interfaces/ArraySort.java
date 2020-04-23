package ru.progwards.java1.lessons.interfaces;

public class ArraySort implements CompareWeight {

    private int[] a;

    public CompareResult compareWeight(int smthWeight){
        if (this.a[0] > smthWeight){
            return CompareResult.GREATER;
        } else {
            return CompareResult.LESS;
        }

    }

    public void sort(int[] a){
        for (int i=0; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if(this.compareWeight(a[j]) == CompareResult.GREATER) {
                    int time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }
}
