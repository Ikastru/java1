package ru.progwards.java1.lessons.bigints;


import java.util.Arrays;

public class ByteInteger extends AbsInteger {

    byte n;
//    private byte[] digits;

    public ByteInteger(byte n){
        this.n = n;
}

    public String toString(){
        return Integer.toString(n);
    }
//        int num = (int)Math.log10(n)+1;
//        this.digits = new byte[num];
//        for (int i = 0; i < num; i++) {
//            digits[i] = (byte)(n % 10);
//             n = (byte) (n / 10);
//        }
//        return Arrays.toString(digits);
//    }

}
