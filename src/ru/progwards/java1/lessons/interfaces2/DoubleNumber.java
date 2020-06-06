package ru.progwards.java1.lessons.interfaces2;


public class DoubleNumber extends Number implements Comparable<Number> {

    double num;

    public DoubleNumber(double num){
        this.num = num;
    }

    @Override
    public int compareTo(Number num){
        double i = num.toDub();
        return Double.compare(this.num, i);
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
