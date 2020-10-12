package ru.progwards.java2.lessons.patterns.bigints;

public class ShortInteger implements IntegerFactory {
    private static short n;

    ShortInteger(short n){
        this.n = n;
    }

    @Override
    public String getInfo(){
        return Integer.toString(n);
    }
}

