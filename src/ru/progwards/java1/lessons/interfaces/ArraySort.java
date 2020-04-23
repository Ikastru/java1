package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public class ArraySort implements CompareWeight<ArraySort> {

    private int[] a;

    @Override
    public CompareResult compareWeight(ArraySort smthWeight){
        if (Arrays.compare(this.a, smthWeight.a) == 1){
            return CompareResult.GREATER;
        } else if (Arrays.compare(this.a, smthWeight.a) == -1){
            return CompareResult.LESS;
        } else {
            return CompareResult.EQUAL;
        }

    }

    public void sort(ArraySort smthWeight){
        for (int i=0; i<smthWeight.a.length; i++){
            for (int j=i+1; j<smthWeight.a.length; j++){
                if(compareWeight(smthWeight) == CompareResult.GREATER) {
                    int time = smthWeight.a[i];
                    smthWeight.a[i] = smthWeight.a[j];
                    smthWeight.a[j] = time;
                }
            }
        }
    }
}
