package ru.progwards.java2.lessons.patterns.bigints;

public class IntInteger implements IntegerFactory {
    private static int n;

    IntInteger(int n){
        this.n = n;
    }

    @Override
    public String getInfo(){
        return Integer.toString(n);
    }

}

