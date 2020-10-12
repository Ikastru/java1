package ru.progwards.java2.lessons.patterns.Proxy;

public interface PP {
    //Местоположение
    public Double getPlace();
    //проверить скорость
    public boolean checkV(Double V);
    //Скорость
    public void setV(Double place, Double V);
    //Показать данные
    public void show();
}
