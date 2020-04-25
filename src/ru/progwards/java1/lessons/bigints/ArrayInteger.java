package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    private byte[] digits;

    public  ArrayInteger(int n){
        int num = (int)Math.log10(n)+1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
            digits[i] = (byte)(n % 10);
            n = n / 10;
        }
    }

    public void fromInt(BigInteger value){
        BigInteger ten = new BigInteger("10");
        int num = (int)Math.log10(value.doubleValue())+1;
        this.digits = new byte[num];
        for (int i = 0; i < num; i++) {
            String s = value.mod(ten).toString();
            digits[i] = (byte)Integer.parseInt(s);
            value = value.divide(ten);
        }

    }

    public BigInteger toInt(){
//        return new BigInteger(this.digits);
//        return BigInteger.valueOf(456782765);
        String str = "";
        BigInteger a = null;

        for (int i = 0; i < this.digits.length; i++) {
            str += this.digits[i];
        }
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result = str.charAt(i) + result;
        }
        a = new BigInteger(result);
        return a;
    }

    public boolean add(ArrayInteger num){
        if (num.digits.length > this.digits.length){
            for (int i = 0; i < this.digits.length; i++){
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
