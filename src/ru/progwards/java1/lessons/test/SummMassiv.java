package ru.progwards.java1.lessons.test;

public class SummMassiv {
   public static int summMass(int[] mass){
        int sum = 0;
        for (int x:mass){
            sum+=x;
        }
        return sum;
    }

    public static void main(String[] args) {
         int[] arr = {1,2,3,4,5};
        System.out.println(summMass(arr));
    }
}
