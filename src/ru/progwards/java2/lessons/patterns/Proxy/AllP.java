package ru.progwards.java2.lessons.patterns.Proxy;

import java.util.ArrayList;
import java.util.List;

public class AllP implements PP {
    private Double place;

    public AllP(Double place){
        this.place = place;
    }

    public Double getPlace(){
        return place;
    }

    public boolean checkV(Double V){
        Double realV = Pokazania.INSTANCE.getV(place);
        return realV.equals(V);
    }

    public void setV(Double V){
        setV(place, V);
    }

    public void setV(Double place, Double V){
        Pokazania.INSTANCE.setMap(place, V);
        System.out.println("Для местоположения" + " " + place + " " + "установлена скорость" + " " + V);
    }

    public void show(){
        List<Double> keys = new ArrayList<>(Pokazania.INSTANCE.getMaps().keySet());
        for (Double m: keys){
            m.toString();
        }
    }
}
