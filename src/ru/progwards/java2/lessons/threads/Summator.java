package ru.progwards.java2.lessons.threads;

/*
Реализовать класс Summator который суммирует все числа от 1 до number в несколько потоков. Например для числа 5
должно быть просуммировано 1+2+3+4+5

1.1 Конструктор Summator(int count) - инициализирует класс, с указанием в какое количество потоков
надо будет проводить суммирование, count - количество потоков.

1.2 Метод public BigInteger sum(BigInteger number) - который, собственно и запускает потоки выполняющие суммирование,
number - число, до которого надо просуммировать числа. Для этого нужно будет разбить весь диапазон суммируемых чисел на
блоки равного размера, по количеству потоков. Каждому потоку выдать блок для суммирования от n...m.
Например, если мы суммируем 1000 в 3 потока, то первому достанется от 1 до 333 второму от 334 до 666,
третьему от 667 до 1000. После чего результат суммирования каждого блока нужно будет инкрементировать в
общую сумму и вернуть как результат метода.
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Summator {

    //Количество потоков
    private int count;

    private Summator(int count) {
        this.count = count;
    }

    //Начало блока
    BigInteger start = BigInteger.ONE;
    //Конец блока
    BigInteger finish;
    //Список сумм блоков
    List<BigInteger> list = new ArrayList<>();

    public BigInteger sum(BigInteger number) {
        //Размер блока
        BigInteger countSize = number.divide(BigInteger.valueOf(count));
        this.finish = countSize;
        for (int i = 0; i < count; i++) {
                Thread thread = new Thread(r);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            start = finish.add(BigInteger.ONE);
            finish = finish.add(countSize);
        }
        return summ(list);
    }

    //Сумма блоков
    private BigInteger summ(List<BigInteger> list) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < list.size(); i++) {
            result = result.add(list.get(i));
        }
        return result;
    }

    //Сумма в блоке
    private BigInteger sumBlock(BigInteger start, BigInteger finish) {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger i = start; i.compareTo(finish) <= 0; i = i.add(BigInteger.ONE)) {
            sum = sum.add(i);
        }
        return sum;
    }

     Runnable r = () -> {
         list.add(sumBlock(start, finish));
         System.out.println(list.toString());
     };

    public static void main(String[] args) {
        Summator summator = new Summator(4);
        System.out.println(summator.sum(new BigInteger("2400")));
    }
}

