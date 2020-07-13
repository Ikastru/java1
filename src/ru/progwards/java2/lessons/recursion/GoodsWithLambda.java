package ru.progwards.java2.lessons.recursion;

/**
 * 1.2 Реализовать класс GoodsWithLambda
 * 1.3 реализовать метод
 * public void setGoods(List<Goods> list)
 * 1.4 реализовать метод
 * public List<Goods> sortByName() - вернуть список, отсортированный по наименованию
 * 1.5 реализовать метод
 * public List<Goods> sortByNumber() - вернуть список, отсортированный по артикулу, без учета регистра
 * 1.6 реализовать метод
 * public List<Goods> sortByPartNumber() - вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
 * 1.7 реализовать метод
 * public List<Goods> sortByAvailabilityAndNumber() - вернуть список, отсортированный по количеству, а для одинакового количества, по артикулу, без учета регистра
 * 1.8 реализовать метод
 * public List<Goods> expiredAfter(instant date) - вернуть список, с товаром, который будет просрочен после указанной даты, отсортированный по дате годности
 * 1.9 реализовать метод
 * public List<Goods> сountLess(int count) - вернуть список, с товаром, количество на складе которого меньше указанного, отсортированный по количеству
 * 1.10 реализовать метод
 * public List<Goods> сountBetween(int count1, int count2) - вернуть список, с товаром, количество на складе которого больше count1 и меньше count2, отсортированный по количеству
 */

import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsWithLambda {

    List<Goods> list = new ArrayList<>();

    public void setGoods(List<Goods> list){
        this.list.addAll(list);
    }

    public List<Goods> sortByName(){
        list.sort(Comparator.comparing(a -> a.name));
        return list;
    }

    public List<Goods> sortByNumber(){
        list.sort(Comparator.comparing(a -> a.number.toLowerCase()));
        return list;
    }

    public List<Goods> sortByPartNumber(){
        list.sort(Comparator.comparing(a -> a.number.substring(0, 3).toLowerCase()));
        return list;
    }

    public List<Goods> sortByAvailabilityAndNumber(){
        List<Goods> sort = list.stream().sorted(Comparator.comparing(a -> a.number.toLowerCase())).sorted(Comparator.comparing(a -> a.available)).collect(Collectors.toList());
        return sort;
    }

    public List<Goods> expiredAfter(Instant date){
        Predicate<Goods> greater = b -> date.compareTo(b.expired)>0;
        List<Goods> filter = list.stream().filter(greater).collect(Collectors.toList());
        filter.sort(Comparator.comparing(a -> a.expired));
        return filter;
    }

    public List<Goods> сountLess(int count){
        Predicate<Goods> below = b -> b.available < count;
        List<Goods> filter = list.stream().filter(below).collect(Collectors.toList());
        filter.sort(Comparator.comparing(a -> a.available));
        return filter;
    }

    public List<Goods> сountBetween(int count1, int count2){
        Predicate<Goods> greater1 = b -> b.available > count1;
        Predicate<Goods> below2 = b -> b.available < count2;
        Predicate<Goods> composed = below2.and(greater1);
        List<Goods> filter = list.stream().filter(composed).collect(Collectors.toList());
        return filter;
    }
}
