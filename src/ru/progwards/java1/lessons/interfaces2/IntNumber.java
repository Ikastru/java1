package ru.progwards.java1.lessons.interfaces2;


public class IntNumber extends Number implements Comparable<Number> {

    int num;

    public IntNumber(int num){
        this.num = num;
    }

    @Override
    public int compareTo(Number num){
        int i = num.toInt();
        return Integer.compare(this.num, i);
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
