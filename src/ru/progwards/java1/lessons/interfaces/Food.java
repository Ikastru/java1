package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight {
    private int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CompareResult compareWeight(double smthHasWeigt){
        if (Double.compare(this.weight, smthHasWeigt)==-1){
            return CompareResult.LESS;
        } else if(Double.compare(this.weight, smthHasWeigt)==1){
            return CompareResult.GREATER;
        } else {
            return CompareResult.EQUAL;
        }
    }

    public static void sort(Animal[] a){
        for (int i=0; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if(Double.compare((a[i]).getWeight(), (a[j]).getWeight()) == 1) {
                    Animal time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
            }
        }
    }
}
