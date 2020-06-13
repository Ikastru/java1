package ru.progwards.java1.lessons.test;

import java.util.StringTokenizer;

public class TEST {


    public static void main(String[] args) {
        String txt =
                "StringTokenizer - этот класс, " +
                        "нам строку разобьёт на раз.";
        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
        while (tokenizer.hasMoreTokens())
            System.out.print(tokenizer.nextToken());
            System.out.println(" ");
        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);
    }

}
