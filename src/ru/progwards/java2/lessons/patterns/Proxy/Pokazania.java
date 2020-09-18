package ru.progwards.java2.lessons.patterns.Proxy;

import java.util.ArrayList;
import java.util.List;

public class Pokazania {
    List<GPS> listGPS = new ArrayList<>();
    double mo = mathOD();
    double sigma = Math.sqrt(dispersia());

    public void addGPS(GPS gps){
        listGPS.add(gps);
    }

    public void showList(){
        for (GPS list: listGPS){
            list.toString();
        }
    }

    public double mathOD(){
        double v = 0;
        for (GPS list: listGPS){
            v+=Math.sqrt(list.lat*list.lat + list.lon*list.lon)/list.time1;
        }
        return v/listGPS.size();
    }

    public double dispersia(){
        double d = 0;
        for (GPS list: listGPS){
            d+=mo*((Math.sqrt(list.lat*list.lat + list.lon*list.lon)/list.time1)-mo)*((Math.sqrt(list.lat*list.lat + list.lon*list.lon)/list.time1)-mo);
        }
        return d;
    }

}
