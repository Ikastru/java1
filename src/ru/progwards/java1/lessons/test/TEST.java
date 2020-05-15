package ru.progwards.java1.lessons.test;

import java.util.*;

public class TEST {

    public static void doTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(19);
        treeSet.add(12);
        treeSet.add(15);
        treeSet.add(10);

        String s = "";
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext())
            s += iterator.next();
        System.out.println(s);
    }

    public static void main(String[] args) {
        doTreeSet();
    }

}
