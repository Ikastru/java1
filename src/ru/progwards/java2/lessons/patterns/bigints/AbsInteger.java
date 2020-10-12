package ru.progwards.java2.lessons.patterns.bigints;

public class AbsInteger {
    private static volatile AbsInteger instance;

    private AbsInteger(){ }

    public static AbsInteger getInstance() {
        AbsInteger localInstance = instance;
        if (localInstance == null) {
            synchronized (AbsInteger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AbsInteger();
                }
            }
        }
        return localInstance;
    }

    public static IntegerFactory create(int z){
        IntegerFactory factory = null;
        if (z > -128 && z < 127) {
            ByteInteger bint = new ByteInteger((byte) z);
            factory = bint;
        } else if (z > -32768 && z < 32767) {
            ShortInteger bint = new ShortInteger((short)z);
            factory = bint;
        } else {
            IntInteger bint = new IntInteger(z);
            factory = bint;
        }
        return factory;
    }

    public static AbsInteger add(int num1, int num2) {
        IntegerFactory factory = create(num1+num2);
        System.out.println(factory.getInfo());
        return null;
    }

    public static void main(String[] args) {
        AbsInteger absInteger = getInstance();
        IntegerFactory sI = absInteger.create((short) 20);
        String str1 = sI.getInfo().trim();
        int c = Integer.parseInt(str1);
        IntegerFactory bI = absInteger.create((byte) 100);
        String str2 = bI.getInfo().trim();
        int b = Integer.parseInt(str2);
        add(c, b);
    }
}

