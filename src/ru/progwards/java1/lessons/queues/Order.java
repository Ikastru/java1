package ru.progwards.java1.lessons.queues;

/**
 * Создать класс - очередь, на обслуживание заявок клиентов в зависимости от
 * величины суммы заказа.
 *
 * 2.1 Создать отдельный класс Order
 *
 * 2.2 Создать приватное свойство double sum  - сумма заказа
 *
 * 2.3 Создать приватное свойство int num  - номер заказа
 *
 * 2.4 Создать конструктор public Order(double sum) - для номера заказа создать
 * систему автонумерации, начиная с 1
 *
 * 2.5 Создать геттер public double getSum()
 *
 * 2.6 Создать геттер public int getNum()
 */


public class Order {

    private double sum;
    private int num;
    private static int count = 0;

    public Order(double sum){
        this.sum = sum;
        this.num = ++count;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }
}
