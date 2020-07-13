package ru.progwards.java2.lessons.recursion;

/**
 * Решить задачу Ханойской башни методом рекурсии
 * 3.1 Реализовать конструктор
 * public HanoiTower(int size, int pos), который инициализирует башню с size кольцами (1..size). pos - номер начального штыря (0,1,2)
 * 3.2 Реализовать метод
 * public move(int from, int to), который переносит башню со штыря from на штырь to
 * 3.3 Реализовать метод
 * void print(), который выводит текущее состояние башни на консоль в формате
 * <p>
 * каждое кольцо 5 символов - 3 символа добитых слева нулями, края <>
 * пустой штырь - буква I (латинское И большое) - остальное пробелы
 * между краями колец - один пробел
 * высота всегда == size
 * основание - символ "=", 17 шт
 * 3.3 реализовать метод void setTrace(boolean on) который включает отладочную печать состояния игрового поля после каждого шага алгоритма (метода move).
 * В результате все промежуточные ходы должны быть отображены
 */

import java.util.ArrayDeque;

import static java.lang.String.format;

public class HanoiTower {

    private int size;
    private int count;
    private final int pos;
    boolean setTrace;
    ArrayDeque<Integer>[] shtir;

    public HanoiTower(int size, int pos) {
        this.size = size;
        this.count = size;
        this.pos = pos;
        shtir = new ArrayDeque[]{new ArrayDeque<Integer>(), new ArrayDeque<Integer>(), new ArrayDeque<Integer>()};
        for (int i = size; i > 0; i--) {
            shtir[pos].addFirst(i);
        }
    }

    public void move(int from, int to) {
        int third = 0;
        switch (from) {
            case 0:
                if (to == 1) third = 2;
                else third = 1;
                break;
            case 1:
                if (to == 2) third = 0;
                else third = 2;
                break;
            case 2:
                if (to == 0) third = 1;
                else third = 0;
                break;
        }
        doTowersInt(count, from, third, to);
    }

    //Распечатка движения в символах из Лафоре
    public void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1)
            System.out.println("Disk 1 from " + from + " to " + to);
        else {
            doTowers(topN - 1, from, to, inter); // from-->inter
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to); // inter-->to
        }
    }

    //Распечатка движения в картинках
    public void doTowersInt(int topN, int from, int inter, int to) {
        if (topN == 1)
            System.out.println("Disk 1 from " + from + " to " + to);
        else {
            doTowersInt(topN - 1, from, to, inter); // from-->inter
            if(setTrace){
                print(topN, from, to);
            }
            doTowersInt(topN - 1, inter, from, to); // inter-->to
        }
    }

    void print(int topN, int from, int to) {
        System.out.println("Disk " + topN + " from " + from + " to " + to);
    }

    void setTrace(boolean on) {
        setTrace = on;
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower(3, 0);
        hanoiTower.setTrace(true);
        hanoiTower.move(0, 2);
    }
}