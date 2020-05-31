package ru.progwards.java1.lessons.abstractnum;

/**
 * 3. Реализовать класс DoubleNumber, который содержит
 * 3.1 конструктор
 * public DoubleNumber(double num), который будет сохранять в классе значение числа double.
 * 3.2 переопределение метода
 * public Number mul(Number num), который будет возвращать произведение содержимого класса на num.
 * 3.3 переопределение метода
 * public Number div(Number num), который будет возвращать частное от деления содержимого класса на num.
 * 3.4 переопределение метода
 * public Number newNumber(String strNum), который будет возвращать новый экземпляр класса DoubleNumber
 * со значением параметра strNum, содержащим число типа double, например 3.14.
 * Надо будет правильно распарсить строку и привести значение к числу с плавающей точкой.
 *
 * 3.5 public String toString() - привести значение числа к строке
 */

public class DoubleNumber extends Number {

    double num;

    public DoubleNumber(double num){
        this.num = num;
    }

    @Override
    public Number mul(Number num){
        double i = num.toDub();
        return new DoubleNumber(this.num*i);
    }

    @Override
    public Number div(Number num){
        double i = num.toDub();
        return new DoubleNumber(this.num/i);
    }

    @Override
    public Number newNumber(String strNum){
        return new DoubleNumber(Double.parseDouble(strNum));
    }

    @Override
    public String toString(){
        return String.valueOf(num);
    }

    @Override
    public Double toDub(){
        return num;
    }
}
