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

    public CompareResult compareWeight(int smthWeight){
        if (this.weight < smthWeight){
            return CompareResult.LESS;
        } else if(this.weight > smthWeight){
            return CompareResult.GREATER;
        } else {
            return CompareResult.EQUAL;
        }
    }

}
