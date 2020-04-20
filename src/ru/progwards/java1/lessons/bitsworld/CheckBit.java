package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {

    public static int checkBit(byte value, int bitNumber){
        int result = 0;
        if ((value & (1 << bitNumber)) == 1){
            result = 1;
        }
        else {
            result = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        byte value = 0b0100101;
        System.out.println(checkBit(value, 3));
    }
}
