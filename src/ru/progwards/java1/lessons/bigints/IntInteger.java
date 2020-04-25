package ru.progwards.java1.lessons.bigints;

import java.util.Arrays;

public class IntInteger extends AbsInteger {

    IntInteger intInt;
    int n;
    private int[] digits;

    public IntInteger(int n){
        this.n = n;
        this.intInt = new IntInteger(n);
    }

    public String toString(){
        int num = (int)Math.log10(n)+1;
        this.digits = new int[num];
        for (int i = 0; i < num; i++) {
            digits[i] = (n % 10);
            n = (n / 10);
        }
        return Arrays.toString(digits);
    }
}
