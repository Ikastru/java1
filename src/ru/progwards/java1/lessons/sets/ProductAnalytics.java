package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll(){
        HashSet<Product> productsInShops = new HashSet<>();
        for (Shop s: shops){
            productsInShops.retainAll(s.getProducts());
        }
        return productsInShops;
    }

    public Set<Product> existAtListInOne(){
        HashSet<Product> productsInShops2 = new HashSet<>();
        for (Shop s: shops){
            productsInShops2.addAll(s.getProducts());
        }
        HashSet<Product> APIS = new HashSet(products);
        HashSet<Product> PIS = new HashSet<>();
        for (Shop s: shops){
            PIS.addAll(s.getProducts());
        }
        APIS.removeAll(PIS);
        return APIS;
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
        HashSet OnlyInOne = new HashSet();
        ListIterator<Shop> iter = (ListIterator<Shop>) shops.iterator();
        HashSet result = new HashSet();
        while(iter.hasNext()){
            result.addAll(iter.next().getProducts());
            result.addAll(iter.previous().getProducts());
            result.removeAll(iter.next().getProducts());
        }
        return result;
    }

}
