package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {

    final static double RADIUS = 6371.2;
    final static double PI = 3.14;

    public static double volumeBallDouble(double radius){
        return (4/3)*PI*Math.pow(radius,3);
    }

    public static float volumeBallFloat(float radius){
        return (float) ((4/3)*PI*Math.pow(radius,3));
    }

    public static double calculateAccuracy(double radius){
        return volumeBallDouble(radius)-volumeBallFloat((float)radius);
    }

    public static void main(String[] args) {
        System.out.println(volumeBallDouble(RADIUS));
        System.out.println(calculateAccuracy(1));
        System.out.println(calculateAccuracy(RADIUS));
    }
}
