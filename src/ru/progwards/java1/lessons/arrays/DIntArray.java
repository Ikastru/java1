package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] a;

    public DIntArray() {
        this.a = new int[0];
    }

    public DIntArray(int[] a) {
        this.a=Arrays.copyOf(a, a.length);

    }

    public void add(int num){
        int [] b;
        b = new int[a.length+1];
        System.arraycopy (a, 0, b, 0, a.length);
        b[a.length] = num;
        System.out.println(Arrays.toString(b));
    }

    public void atInsert(int pos, int num){
        int [] b = new int[a.length+1];
        System.arraycopy (a, 0, b, 0, pos);
        b[pos] = num;
        System.arraycopy (a, pos, b, pos+1, a.length-pos);
        System.out.println(Arrays.toString(b));
    }

    public void atDelete(int pos){
        int [] b = new int[a.length-1];
        System.arraycopy (a, 0, b, 0, pos);
        System.arraycopy (a, pos+1, b, pos, a.length-pos-1);
        System.out.println(Arrays.toString(b));
    }

    public int at(int pos){
        return a[pos];
    }

    public static void main(String[] args) {
        int [] a = {15,45,89,-1,5,134};
        System.out.println(a.length);
        DIntArray dIntArray=new DIntArray(a);

        System.out.println(Arrays.toString(a));
        dIntArray.add(3);
        dIntArray.atInsert(3, 87);
        dIntArray.atDelete(2);
        System.out.println(dIntArray.at(5));
    }
}
