package ru.progwards.kost;

public class HelloWorld {

    static void helloWorld(){
        String str;
        str = "Hello World!";
        System.out.println(str);
    }

    static int addition(int x, int y){
        return x+y;
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
    }
}
