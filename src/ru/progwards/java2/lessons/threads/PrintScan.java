package ru.progwards.java2.lessons.threads;
 /*
 Создать класс PrintScan - МФУ - на котором возможно одновременно выполнять печать и сканирование документов,
 но нельзя одновременно печатать или сканировать два документа.

2.1 Метод static void print(String name, int pages) - "печатает" страницы документа с именем name - выводит на консоль

print <name> page 1
print <name> page 2
...
с интервалом в 50 мс. Вместо <name> выводится содержимое name. Пока один документ "печатается", второй
не может быть напечатан


2.2 Метод static void scan(String name, int pages) - "сканирует" страницы документа с именем name - выводит на консоль

scan <name> page 1
scan <name> page 2
...
с интервалом в 70 мс. Вместо <name> выводится содержимое name. Пока один документ "сканируется",
второй не может быть отсканирован

2.3 Метод static void main(...)

Написать тест, который запускает на печатать параллельно 10 документов и запускает на сканирование еще 10
документов параллельно. Потоки создает только main, print и scan содержат только синхронизацию.
  */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {
    private final static Lock lock1 = new ReentrantLock();
    private final static Lock lock2 = new ReentrantLock();

    static void print(String name, int pages) {
        lock1.lock();
        try {
            for (int i = 1; i <= pages; i++) {
                System.out.println("печатается страница " + i + " " + name);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock1.unlock();
        }
    }

    static void scan(String name, int pages) {
        lock2.lock();
        try {
            for (int i = 1; i <= pages; i++) {
                System.out.println("сканируется страница " + i + " " + name);
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock2.unlock();
        }
    }

    static class RunnablePrint implements Runnable {
        String docName;
        int pages;

        public RunnablePrint(String docName, int pages) {
            this.docName = docName;
            this.pages = pages;
        }

        @Override
        public void run() {
            print(docName, pages);
            scan(docName, pages);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnablePrint("Документ 1", 10));
        Thread t2 = new Thread(new RunnablePrint("Документ 2", 30));
        Thread t3 = new Thread(new RunnablePrint("Документ 3", 20));
        Thread t4 = new Thread(new RunnablePrint("Документ 4", 50));
        Thread t5 = new Thread(new RunnablePrint("Документ 5", 10));
        Thread t6 = new Thread(new RunnablePrint("Документ 6", 4));
        Thread t7 = new Thread(new RunnablePrint("Документ 7", 20));
        Thread t8 = new Thread(new RunnablePrint("Документ 8", 60));
        Thread t9 = new Thread(new RunnablePrint("Документ 9", 12));
        Thread t10 = new Thread(new RunnablePrint("Документ 10", 50));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
