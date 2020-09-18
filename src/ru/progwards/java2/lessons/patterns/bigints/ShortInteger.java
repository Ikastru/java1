package ru.progwards.java2.lessons.patterns.bigints;

public class ShortInteger extends AbsInteger implements IntegerFactory {
    private static volatile ShortInteger instance;
    private static short n;

    ShortInteger(short n){
        this.n = n;
    }

    public static ShortInteger getInstance(short n) {
        ShortInteger localInstance = instance;
        if (localInstance == null) {
            synchronized (ShortInteger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ShortInteger(n);
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

