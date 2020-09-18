package ru.progwards.java2.lessons.patterns.Proxy;

public class GPS {
    public double lat; // широта
    public double lon; // долгота
    public long time1; // время в мс
    @Override
    public String toString(){
        System.out.println(" ");
        return "широта: " + lat + "долгота: " + lon + "время: " + time1;
    }
}

