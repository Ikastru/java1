package ru.progwards.java1.lessons.bitsworld;

public class SumBits {

    public static int sumBits(int value){
        int count = value & 1;
        while (value > 0) {
            count += ((value >>>= 1) & 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int value = 0b10000000;   //??? Ведь в задаче говорится про (byte) value, а робот подставляет int
        System.out.println(sumBits(value));
    }
}
