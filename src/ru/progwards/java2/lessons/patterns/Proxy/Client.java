package ru.progwards.java2.lessons.patterns.Proxy;

public class Client {
    public static void main(String[] args) {
        Double p = 25d;
        AllP pokaz = new AllP(p);
        PokazaniaProxy pokazaniaProxy = new PokazaniaProxy(pokaz);
        pokazaniaProxy.show();
    }
}
