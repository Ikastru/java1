package ru.progwards.java1.lessons.queues;

/**
 * 2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с
 * приоритетом, разбивая их по 3-м классам
 * 3 - заказы до 10000 руб включительно
 * 2 - заказы от 10000 до 20000 руб включительно
 * 1 - заказы от 20000 руб
 * <p>
 * 2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для
 * обслуживания. Вначале обслуживаются заказы класса 1, потом 2, потом 3.
 * Внутри каждого класса заказы должны обслуживаться в порядке поступления
 * (по номеру заказа). Метод не выбрасывает исключения, возвращает null в случае
 * пустой очереди.
 * <p>
 * Продумать, и, при необходимости, добавить в классы нужные методы и свойства,
 * для того, чтобы реализовать эту задачу.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {

    static Comparator<Order> comparator = new Comparator<>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int) (o1.getL() - o2.getL());
        }
    };

    static PriorityQueue<Order> myQueue = new PriorityQueue<Order>(comparator);

    /**
     * Или в компараторе нужно учитывать классы 1,2,3.
     *
     */

    public static void add(Order order) {
        myQueue.add(order);
    }

    public static Order get() {
        return myQueue.poll();
    }

    public static String getStr() {
        return " " + myQueue.poll().getL();
    }

    public static void main(String[] args) {
        Order order1 = new Order(17041.0);
        Order order2 = new Order(7265.0);
        Order order3 = new Order(9844.0);
        Order order4 = new Order(4366.0);
        Order order5 = new Order(6975.0);
        Order order6 = new Order(17385.0);
        Order order7 = new Order(16082.0);
        Order order8 = new Order(11516.0);
        Order order9 = new Order(535.0);
        Order order10 = new Order(19272.0);
        Order order11 = new Order(7323.0);
        Order order12 = new Order(13866.0);
        Order order13 = new Order(28938.0);
        Order order14 = new Order(23550.0);
        Order order15 = new Order(12369.0);
        Order order16 = new Order(7045.0);
        Order order17 = new Order(24763.0);
        Order order18 = new Order(29476.0);
        Order order19 = new Order(24366.0);
        Order order20 = new Order(4775.0);
        Order order21 = new Order(6351.0);
        Order order22 = new Order(3654.0);
        Order order23 = new Order(24745.0);
        add(order1);
        add(order2);
        add(order3);
        add(order4);
        add(order5);
        add(order6);
        add(order7);
        add(order8);
        add(order9);
        add(order10);
        add(order11);
        add(order12);
        add(order13);
        add(order14);
        add(order15);
        add(order16);
        add(order17);
        add(order18);
        add(order19);
        add(order20);
        add(order21);
        add(order22);
        add(order23);
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
//        System.out.println(getStr());System.out.println(getStr());System.out.println(getStr());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
        System.out.println(get());
    }
}
