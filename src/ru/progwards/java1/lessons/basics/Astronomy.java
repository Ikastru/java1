package ru.progwards.java1.lessons.basics;

public class Astronomy {

    final static double PI = 3.14;
    final static double RADIUS = 6371.2;
    final static double MERCURY = 2439.7;
    final static double JUP = 71492;

    public static Double sphereSquare(Double r){
        return (4*PI*Math.pow(r,2));
    }

    public static Double earthSquare(){
        return sphereSquare(RADIUS);
    }

    public static Double mercurySquare(){
        return sphereSquare(MERCURY);
    }

    public static Double jupiterSquare(){
        return sphereSquare(JUP);
    }

    public static Double earthVsMercury(){
        return earthSquare()/ mercurySquare();
    }

    public static Double earthVsJupiter(){
        return earthSquare()/ jupiterSquare();
    }
    public static void main(String[] args) {
        double e=earthSquare();
        double m=mercurySquare();
        double j=jupiterSquare();
        double evm=earthVsMercury();
        double evj=earthVsJupiter();
        System.out.println(e);
        System.out.println(m);
        System.out.println(j);
        System.out.println(evm);
        System.out.println(evj);
    }
}

