package ru.progwards.java1.lessons.queues;

/**
 * 2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с
 * приоритетом, разбивая их по 3-м классам
 * 3 - заказы до 10000 руб включительно
 * 2 - заказы от 10000 до 20000 руб включительно
 * 1 - заказы от 20000 руб
 *
 * 2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для
 * обслуживания. Вначале обслуживаются заказы класса 1, потом 2, потом 3.
 * Внутри каждого класса заказы должны обслуживаться в порядке поступления
 * (по номеру заказа). Метод не выбрасывает исключения, возвращает null в случае
 * пустой очереди.
 *
 * Продумать, и, при необходимости, добавить в классы нужные методы и свойства,
 * для того, чтобы реализовать эту задачу.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {

    Comparator<Order> comparator = new Comparator<>() {
        @Override
        public int compare(Order o1, Order o2) {
            int res=0;
            if (o1.getC() == o2.getC()){
                res = (o1.getNum() - o2.getNum());
            } else {
                res= o1.getL() - (o2.getL());
            }
            return res;
        }
    };

    PriorityQueue<Order> myQueue = new PriorityQueue<Order>(comparator);

    /**
     * Или в компараторе нужно учитывать классы 1,2,3.
     *
     */

//    public static Comparator<Order> numComparator = new Comparator<Order>(){
//
//        @Override
//        public int compare(Order c1, Order c2) {
//            int res = 0;
//            if(c1.getSum()<=10000 && c2.getSum()>10000){
//                res = 1;
//            }
//            if(c1.getSum()<=20000 && c2.getSum()>20000){
//                res = 1;
//            }
//            if(c1.getSum()>10000 && c2.getSum()<=10000){
//                res = -1;
//            }
//            if(c1.getSum()>20000 && c2.getSum()<=20000){
//                res = -1;
//            }
//            if( (c1.getSum()<=10000 && c2.getSum()<=10000) || (c1.getSum()<=20000 && c2.getSum()<=20000)
//            || (c1.getSum()>20000 && c2.getSum()>20000)) {
//                res = (c1.getNum() - c2.getNum());
//            }
//            return res;
//        }
//    };


    public void add(Order order){
        myQueue.add(order);
    }

    public Order get(){
        return myQueue.poll();
    }
}
