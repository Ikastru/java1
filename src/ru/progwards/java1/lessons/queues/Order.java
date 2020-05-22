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
    private static int count = 1;
    //Новая переменная для сортировки в очереди. Класс (1,2,3) умножается на миллион и
    //прибавляется номер заказа. Так все Ордера в очереди будут отсортированы как нужно.
    private long l;


    public Order(double sum){
        this.sum = sum;
        this.num = ++count;
        int c=0;
        if (sum<=10000)
            c = 1;
        else if (sum<=20000)
            c = 2;
        else
            c = 3;
        this.l = c*100000 + num;
    }

    public double getSum() {
        return sum;
    }

    public long getL() {
        return l;
    }

    public int getNum() {
        return num;
    }

}
