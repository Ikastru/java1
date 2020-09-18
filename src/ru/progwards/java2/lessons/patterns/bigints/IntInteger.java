package ru.progwards.java2.lessons.patterns.bigints;

public class IntInteger extends AbsInteger implements IntegerFactory {
    private static volatile IntInteger instance;
    private static int n;

    IntInteger(int n){
        this.n = n;
    }

    public static IntInteger getInstance(int n) {
        IntInteger localInstance = instance;
        if (localInstance == null) {
            synchronized (IntInteger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new IntInteger(n);
                }
            }
        }
        return localInstance;
    }

    @Override
    public String toString(){
        return Integer.toString(n);
    }

}

