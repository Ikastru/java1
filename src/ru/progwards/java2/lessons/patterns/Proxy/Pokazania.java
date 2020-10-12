package ru.progwards.java2.lessons.patterns.Proxy;

import java.util.HashMap;
import java.util.Map;

public enum Pokazania {
    INSTANCE;
    private Map<Double, Double> maps;

    Pokazania(){
        maps = new HashMap<>();
    }

    public Map<Double, Double> getMaps(){
        return maps;
    }

    Double getMap(Double place){
        return maps.get(place);
    }

    public void addInMaps(double lat, double lon, long t){
        maps.put(Math.sqrt(lat*lat + lon*lon), Math.sqrt(lat*lat + lon*lon)/t);
    }

    public void setMap(Double place, Double V){
        maps.put(place, V);
    }

    public Double getV(Double place){
        return maps.get(place);
    }
}
