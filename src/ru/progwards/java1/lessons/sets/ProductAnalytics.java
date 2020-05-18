package ru.progwards.java1.lessons.sets;

/**
 * 2.9 Создать класс ProductAnalytics
 *
 * 2.10 Создать private List<Shop> shops - список магазинов
 *
 * 2.11 Создать private List<Product> products - список всех имеющихся в ассортименте
 * товаров. Все товары, присутствующие в магазинах, обязательно присутствуют в products,
 * но так же тут могут быть и товары, которых нет в магазинах
 *
 * 2.12 Создать конструктор  public ProductAnalytics(List<Product> products, List<Shop> shops)
 *
 * 2.13 Создать функцию public Set<Product> existInAll() - товары из products,
 * которые имеются во всех магазинах
 *
 * 2.14 Создать функцию public Set<Product> existAtListInOne() - товары из products,
 * которые имеются хотя бы в одном магазине
 *
 * 2.15 Создать функцию public Set<Product> notExistInShops() - товары из products,
 * которых нет ни в одном магазине
 *
 * 2.16 Создать функцию public Set<Product> existOnlyInOne() - товары из products,
 * которые есть только в одном магазине
 */

import java.util.*;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll(){
        HashSet<Product> productsInShops = new HashSet(products);
        for (Shop s: shops){
            productsInShops.retainAll(s.getProducts());
        }
        return productsInShops;
    }

    public Set<Product> existAtListInOne(){
        HashSet<Product> allProd = new HashSet(products);
        HashSet<Product> notExProd = new HashSet(notExistInShops());
        allProd.removeAll(notExProd);
        return allProd;
    }

    public Set<Product> notExistInShops(){
        HashSet<Product> allProductsInShops = new HashSet(products);
        HashSet<Product> productsInShops1 = new HashSet<>();
        for (Shop s: shops){
            productsInShops1.addAll(s.getProducts());
        }
        allProductsInShops.removeAll(productsInShops1);
        return allProductsInShops;
    }

    public Set<Product> existOnlyInOne(){
        HashSet<Product> allProdEx = new HashSet(products);
        HashSet<Product> notExProdEx = new HashSet(notExistInShops());
        allProdEx.removeAll(notExProdEx);
        return allProdEx;
    }

}
