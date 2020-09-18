package ru.progwards.java2.lessons.patterns.bigints;

public class AbsInteger {

    public static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        String str1 = num1.toString().trim();
        String str2 = num2.toString().trim();
        int c = Integer.parseInt(str1);
        int d = Integer.parseInt(str2);
        int q = c + d;
        IntegerFactory factory = null;
        if (q > -128 && q < 127) {
            ByteInteger bint = ByteInteger.getInstance((byte) q);
            factory = bint;
        } else if (q > -32768 && q < 32767) {
            ShortInteger bint = ShortInteger.getInstance((short)q);
            factory = bint;
        } else {
            IntInteger bint = IntInteger.getInstance(q);
            factory = bint;
        }
        System.out.println(factory.toString());
        return null;
    }

    public static void main(String[] args) {
        ShortInteger shortInteger = new ShortInteger((short) 20);
        ByteInteger byteInteger = new ByteInteger((byte) 100);
        add(shortInteger, byteInteger);
    }
}

