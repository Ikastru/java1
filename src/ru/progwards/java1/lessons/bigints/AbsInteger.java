package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class AbsInteger {

    public static AbsInteger add(AbsInteger num1, AbsInteger num2){

       String str1 = " "+num1;
       String str2 = " "+num2;
       String strArr1[] = str1.split(" ");
       String strArr2[] = str2.split(" ");
       byte numArr1[] = new byte[strArr1.length];
       byte numArr2[] = new byte[strArr2.length];
       byte numArr[] = new byte[0];
       if (strArr1.length == strArr2.length){
           for (int i =strArr1.length-1; i>0; i-- ){
               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
           }
       } else if (strArr1.length > strArr2.length){
           for (int i =strArr2.length-1; i>0; i-- ){
               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
           }
       } else if (strArr1.length < strArr2.length){
           for (int i =strArr1.length-1; i>0; i-- ){
               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
           }
       }
       String str = numArr.toString();
       byte bn = Byte.parseByte(str);
       ByteInteger byInt1 = new ByteInteger(bn);
       return byInt1;
    }

}

