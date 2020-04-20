package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    private byte num;

    public Binary(byte num){
        this.num = num;
    }

    public String toString(){
        int len=8;
        return Integer.toBinaryString( (1 << len) | (num & ((1 << len) - 1)) ).substring( 1 );
//      return Integer.toBinaryString(num);
    }

    public static void main(String[] args) {
        Binary binary = new Binary((byte) 127);
        System.out.println(binary.toString());
    }

}
