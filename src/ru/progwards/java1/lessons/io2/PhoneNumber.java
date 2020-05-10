package ru.progwards.java1.lessons.io2;

import java.util.Arrays;

public class PhoneNumber {
    public static String format(String phone) throws Exception{
        StringBuilder sb = new StringBuilder();
        for (char c : phone.toCharArray()) {
            if (Character.isDigit(c))
                sb.append(c);
        }
        String regNum = sb.toString();
        char[] arr = regNum.toCharArray();
        if (arr.length<10 || arr.length>11){
            throw new Exception("Номер введён неправильно");
        }
        if (arr[0]==56){
            arr[0]=55;
        }
        String str = Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
        StringBuilder sb1 = new StringBuilder(str);
        if(arr[0] != 43){
            sb1.insert(0, '+');
        }
        sb1.insert(2, '(');
        sb1.insert(6, ')');
        sb1.insert(10, '-');
        regNum = sb1.toString();
        return regNum;
    }

    public static void main(String[] args) {
        try {
            System.out.println( format("89LM-85214G736"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
