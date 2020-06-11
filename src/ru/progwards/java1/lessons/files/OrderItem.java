package ru.progwards.java1.lessons.files;

/**
 * 3.2 Реализовать class OrderItem со следующим свойствами
 *
 * public String googsName - наименование товара
 *
 * public int count - количество
 *
 * public double price - цена за единицу
 */

public class OrderItem {

    public String googsName;

    public int count;

    public double price;

//    public OrderItem(String googsName, int count, double price) {
//        this.googsName = googsName;
//        this.count = count;
//        this.price = price;
//    }

    public String getGoogsName() {
        return googsName;
    }

    public double getSumma() {
        return price * count;
    }


}
