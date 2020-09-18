package ru.progwards.java2.lessons.patterns.bigints;

public class ByteInteger extends AbsInteger implements IntegerFactory {
    private static volatile ByteInteger instance;
    private static byte n;

    ByteInteger(byte n){
        this.n = n;
}

    public static ByteInteger getInstance(byte n) {
        ByteInteger localInstance = instance;
        if (localInstance == null) {
            synchronized (ByteInteger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ByteInteger(n);
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
