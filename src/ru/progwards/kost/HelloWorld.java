package ru.progwards.kost;

import java.util.Arrays;

public class HelloWorld {

    static void helloWorld(){
        String str;
        str = "Hello World!";
        System.out.println(str);
    }

    static int addition(int x, int y){
        return x+y;
    }

    public static int arrayMax(int[] a){
        if (a.length == 0)
            return 0;
        Arrays.sort(a);
        return a[a.length - 1];
    }

    public static void main(String[] args){
        byte b1=123;
        short s1=32023;
        int i1=65432;
        long l1=123456789012345L;
        float fl=1.22278f;
        double pi=3.1415926535;
        double koe=1.5*Math.pow(10,7);
        String message="Запись фильма \"Матрица\" находится в файле C:\\video\\matrix.avi";
        helloWorld();
        System.out.print("Я знаю, что 5 + 7 =");
        System.out.println(addition(5,7));
        double t = 5 / 2;
        System.out.println(t);
        double p = 5.234%2;
        System.out.println(p);
        System.out.println(koe);

        int[] a1 = {12, 5, 0, 58, 36};
        int[] a2 = Arrays.copyOf(a1, a1.length);
        a2[2] = 0;
        System.out.println(Arrays.equals(a1, a2));

        System.out.println(arrayMax(a1));

    }
}
