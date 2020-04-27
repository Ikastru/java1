package ru.progwards.java1.lessons.bigints;

import java.util.Arrays;

public class IntInteger extends AbsInteger {

    int n;
    private int[] digits;

    public IntInteger(int n){
        this.n = n;
    }

    public String toString(){
        return Integer.toString(n);
    }
//        int num = (int)Math.log10(n)+1;
//        this.digits = new int[num];
//        for (int i = 0; i < num; i++) {
//            digits[i] = (n % 10);
//            n = (n / 10);
//        }
//        return Arrays.toString(digits);
//    }
}
