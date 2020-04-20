package ru.progwards.java1.lessons.bitsworld;

public class SumBits {

    public static int sumBits(byte value){
        int count = value & 1;
        while (value != 0) {
            count += ((value >>>= 1) & 1);
        }
        return count;
    }

    public static void main(String[] args) {
        byte value = 0b0100101;
        System.out.println(sumBits(value));
    }
}
