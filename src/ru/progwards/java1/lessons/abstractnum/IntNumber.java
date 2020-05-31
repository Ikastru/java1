package ru.progwards.java1.lessons.abstractnum;

/**
 * 2. Реализовать класс IntNumber, который содержит
 * 2.1 конструктор
 * public IntNumber(int num), который будет сохранять в классе значение целого числа.
 * 2.2 переопределение метода
 * public Number mul(Number num), который будет возвращать произведение содержимого класса на num.
 * 2.3 переопределение метода
 * public Number div(Number num), который будет возвращать частное от деления содержимого класса на num.
 * 2.4 переопределение метода
 * public Number newNumber(String strNum), который будет возвращать новый экземпляр класса
 * IntNumber со значением параметра strNum, содержащим целое число,, например 4 или 3.
 * Надо будет правильно распарсить строку и привести значение к целому типу.
 *
 * 2.5 public String toString() - привести значение числа к строке
 */

public class IntNumber extends Number {

    int num;

    public IntNumber(int num){
        this.num = num;
    }

    @Override
    public Number mul(Number num){
        int i = num.toInt();
        return new IntNumber(this.num*i);
    }

    @Override
    public Number div(Number num){
        int i = num.toInt();
        return new IntNumber(this.num/i);
    }

    @Override
    public Number newNumber(String strNum){
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString(){
        return String.valueOf(num);
    }

    @Override
    public Integer toInt(){
        return num;
    }
}
