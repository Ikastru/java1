package ru.progwards.java1.lessons.test;

import java.util.*;

public class TEST {

    int sumLastAndFirst(ArrayDeque<Integer> deque) {
        if (deque.getFirst() == null || deque.getLast() == null){
            return 0;
        }
        return deque.getFirst() + deque.getLast();
    }

    static void pqTest() {
        PriorityQueue pQueue = new PriorityQueue<>();
        pQueue.offer(10);
        pQueue.offer(1);
        pQueue.offer(9);
        pQueue.offer(3);
        System.out.println(pQueue);
    }

    public static void main(String[] args) {
        pqTest();
    }

}
