package ru.progwards.java1.lessons.test;

public class StrBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println(sb.toString());
        sb.append(" my");
        sb.append(" friends");
        System.out.println(sb);
    }
}
