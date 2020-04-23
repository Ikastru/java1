package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    private static CacheInfo lastFibo = new CacheInfo();


    public static int fiboNumber(int n){
        lastFibo.n = n;
        if (n== lastFibo.fibo){
            return lastFibo.fibo;
        }
        int prev = 0, next = 1;
        for(int i = 0; i < n; i++){
            next = prev + next;
            prev = next - prev;
        }
        lastFibo.fibo = prev;
        return prev;
    }

    public static class CacheInfo{
        public int n;
        public int fibo;
    }

       public static CacheInfo getLastFibo(){
        return lastFibo;
    }

    public static void clearLastFibo(){
        lastFibo.fibo = 0;
    }

    public static void main(String[] args) {

        System.out.println(fiboNumber(10));
        System.out.println(getLastFibo());
        clearLastFibo();
        System.out.println(fiboNumber(11));
        System.out.println(getLastFibo());
    }
}
