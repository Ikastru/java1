package ru.progwards.java1.lessons.test;

import java.util.*;

public class TEST {

    public static void main(String[] args) {
        Collection<Integer> numbers = new ArrayList();
        for (int i = 0; i < 5; i++)
            numbers.add(i);
        numbers.add(Collections.min(numbers));
        System.out.println(numbers);

        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
            Integer n = listIterator.next();
            if (n % 2 != 0)
                listIterator.remove();
            else
                listIterator.add(n * 2);
        }
        System.out.println(linkedList);
    }
}
