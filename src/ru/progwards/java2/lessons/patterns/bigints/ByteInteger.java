package ru.progwards.java2.lessons.patterns.bigints;

public class ByteInteger implements IntegerFactory {
    private static byte n;

    ByteInteger(byte n){
        this.n = n;
    }

    @Override
    public String getInfo(){
        return Integer.toString(n);
    }
}
