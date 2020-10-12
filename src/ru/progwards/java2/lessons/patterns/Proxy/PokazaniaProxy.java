package ru.progwards.java2.lessons.patterns.Proxy;

import java.util.ArrayList;
import java.util.List;

public class PokazaniaProxy implements PP {
    private AllP pokazania;
    double Sigma = 20;
    double MO = 100;

    public PokazaniaProxy(AllP pokazania){
        this.pokazania = pokazania;
    }

    public Double getPlace(){
        return pokazania.getPlace();
    }

    public boolean checkV(Double V){
        return pokazania.checkV(V);
    }

    public void setV(Double place, Double V){
        if (25 == pokazania.getPlace() || pokazania.getPlace() == place){
            pokazania.setV(place, V);
        } else {
            System.out.println("Невозможно установить скорость");
        }
    }

    @Override
    public void show() {
        List<Double> keys = new ArrayList<>(Pokazania.INSTANCE.getMaps().keySet());
        for (Double m: keys){
            if (keys.indexOf(m)<=50){
                m.toString();
            } else {
                if (MO-3*Sigma <= m && m<=MO+3*Sigma)
                    m.toString();
            }
        }
    }
}
