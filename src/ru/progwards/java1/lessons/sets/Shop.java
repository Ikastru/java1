package ru.progwards.java1.lessons.sets;

/**
 * 2.5 Создать класс Shop - магазин
 *
 * 2.6 Создать private List<Product> products - товары имеющиеся в магазине
 *
 * 2.7 Создать конструктор public Shop(List<Product> products)
 *
 * 2.8 Создать метод public List<Product> getProducts()
 */

import java.util.List;

public class Shop {
    private List<Product> products;
    public Shop(List<Product> products){
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
