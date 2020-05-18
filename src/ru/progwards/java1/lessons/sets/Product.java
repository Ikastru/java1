package ru.progwards.java1.lessons.sets;

/**
 * 2.1 Создать класс Product - товар,
 *
 * 2.2. Создать private String code - уникальный артикул товара
 *
 * 2.3 Создать конструктор public Product(String code)
 *
 * 2.4 Метод public String getCode()
 */

public class Product {
    private String code;
    public Product(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
