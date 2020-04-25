package ru.progwards.java1.lessons.bigints;

import java.util.Arrays;

public class ShortInteger extends AbsInteger {

    ShortInteger shorInt;
    short n;
    private byte[] digits;

    public ShortInteger(short n){
        this.n = n;
        this.shorInt = new ShortInteger(n);
    }

    public String toString(){
        int num = (int)Math.log10(n)+1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
            digits[i] = (byte)(n % 10);
            n = (byte) (n / 10);
        }
        return Arrays.toString(digits);
    }
}
