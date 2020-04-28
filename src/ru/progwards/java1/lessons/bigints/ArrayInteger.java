package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    private byte[] digits;

    public ArrayInteger(int n) {
        int num = (int) Math.log10(n) + 1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
            digits[i] = (byte) (n % 10);
            n = n / 10;
        }
    }

    public void fromInt(BigInteger value) {
        String str1 = value.toString().trim();
        double c = Double.parseDouble(str1);
        int num = (int) Math.log10(value.doubleValue()) + 1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
//            String s = value.mod(BigInteger.TEN).toString();
            digits[i] = (byte) (c%10);
            c= c/10;
//            value = value.divide(BigInteger.TEN);
        }

    }

    public BigInteger toInt() {
//        return new BigInteger(this.digits);
//        return BigInteger.valueOf(456782765);
        String str = "";
        BigInteger a = null;

        for (int i = 0; i < digits.length; i++) {
            str += digits[i];
        }
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
        }
        a = new BigInteger(result.trim());
        return a;
    }

    public boolean add(ArrayInteger num) {
//        int len = this.digits.length > num.digits.length ? this.digits.length : num.digits.length;
//        int[] result = new int[len];
//        for (int i = 0; i < len; i++) {
//
//            if (i < this.digits.length) {
//
//                result[i] = this.digits[i];
//            }
//
//            if (i < num.digits.length) {
//
//                result[i] += num.digits.length;
//            }
//        }
//        return true;
//    }
        if (num.digits.length > this.digits.length) {
            for (int i = 0; i < this.digits.length; i++) {
                this.digits[i] = 0;
            }
            return false;

        } else {
            return true;
        }
    }



    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(159);
        System.out.println(Arrays.toString(arrayInteger.digits));
        arrayInteger.fromInt(new BigInteger("159"));
        System.out.println(Arrays.toString(arrayInteger.digits));
        System.out.println(arrayInteger.toInt());
        System.out.println(arrayInteger.add(arrayInteger));
    }
}
