package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {

    private boolean[] sieve;

    public Eratosthenes(int N){
        this.sieve = new boolean[N];
        Arrays.fill(this.sieve, true);
        shift(N);
    }

    private void shift(int N){
        for (int i = 2; i<N; i++) {
            if (sieve[i]) {
                for (int j = i; j * i < N; j++) {
                    sieve[i * j] = false;
                }
            }
        }
    }

    public boolean isSimple(int n){
        if (sieve[n])
            return true;
        else
       return false;
    }

    public void print(){
        System.out.println(Arrays.toString(sieve));
    }

    public static void main(String[] args) {

        int N=1000;

        Eratosthenes eratosthenes = new Eratosthenes(N);
        eratosthenes.print();
        System.out.println(eratosthenes.isSimple(18));

    }

}
