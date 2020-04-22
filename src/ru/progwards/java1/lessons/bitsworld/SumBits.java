package ru.progwards.java1.lessons.bitsworld;

import static java.lang.Math.abs;

public class SumBits {

    public static int sumBits(byte value){
        int count = value & 1;
        while (value != 0) {
            count += ((value >>>= 1) & 1);
        }
        return count;
    }

    public static void main(String[] args) {
        byte value = 0b01011001;   //??? Ведь в задаче говорится про (byte) value, а робот подставляет int
        System.out.println(sumBits(value));
    }
}
