package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {

    public static int checkBit(byte value, int bitNumber){
        int result = 0;
        if (((value >>> bitNumber) & 1) == 1){
            result = 1;
        }
        else {
            result = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        int len =8;
        byte value = 47;
        System.out.println(Integer.toBinaryString( (1 << len) | (value & ((1 << len) - 1)) ).substring( 1 ));
        System.out.println(checkBit(value, 5));
    }
}
