package ru.progwards.java1.lessons.bigints;


import java.util.Arrays;

public class ByteInteger extends AbsInteger {
    ByteInteger byInt;
    byte n;
    private byte[] digits;

    public ByteInteger(byte n){
        this.n = n;
      this.byInt = new ByteInteger(n);
}

    public String toString(){
        int num = (int)Math.log10(n)+1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
            digits[i] = (byte)(n % 10);
             n = (byte) (n / 10);
        }
        return Arrays.toString(digits);
    }

}
