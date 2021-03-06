package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {

    public static int maxSide(int a, int b, int c){
        int d=0;
        if (a>b){
            d=a;
        } else if (c>a){
            d=c;
        } else {
            d=b;
        }
        return d;
    }

    public static int minSide(int a, int b, int c){
        int d=0;
        if (a>b){
            d=b;
        } else if (c>a){
            d=a;
        } else {
            d=c;
        }
        return d;
    }

    public static boolean isEquilateralTriangle(int a, int b, int c){
        if (a==b && b==c)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(maxSide(1500, 1000, 900));
        System.out.println(minSide(1007, 1000, 900));
        System.out.println(isEquilateralTriangle(5,5,5));
    }
}

