package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor {

    public OrderProcessor(String startPath){
        try {
            Files.createDirectory(Paths.get(startPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId){
        return 0;
    }

    public List<Order> process(String shopId){
        List<Order> list = new ArrayList<>();
        return list;
    }

    public Map<String, Double> statisticsByShop(){
        Map<String, Double> map = new HashMap<>();
        return map;
    }

    public Map<String, Double> statisticsByGoods(){
        Map<String, Double> map = new HashMap<>();
        return map;
    }

    public Map<LocalDate, Double> statisticsByDay(){
        Map<LocalDate, Double> map = new HashMap<>();
        return map;
    }
}
