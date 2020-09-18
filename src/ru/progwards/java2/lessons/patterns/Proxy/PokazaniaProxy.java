package ru.progwards.java2.lessons.patterns.Proxy;

public class PokazaniaProxy extends Pokazania {
    Pokazania pokazania = new Pokazania();

    public PokazaniaProxy(Pokazania pokazania) {
        this.pokazania = pokazania;
    }

    @Override
    public void showList() {
        for (GPS gps : pokazania.listGPS) {
            if (pokazania.listGPS.indexOf(gps) <= 50) {
                gps.toString();
            } else {
                if (Math.sqrt(gps.lat * gps.lat + gps.lon * gps.lon) / gps.time1 >= (pokazania.mo - 3 * pokazania.sigma) || Math.sqrt(gps.lat * gps.lat + gps.lon * gps.lon) / gps.time1 <= (pokazania.mo + 3 * pokazania.sigma)) {
                    gps.toString();
                }
            }
        }
    }
}
