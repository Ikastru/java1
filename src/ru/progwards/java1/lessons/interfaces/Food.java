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

    @Override
    public CompareResult compareWeight(CompareWeight smthWeight){
        if (this.weight < ((Food)smthWeight).weight){
            return CompareResult.LESS;
        } else if(this.weight > ((Food)smthWeight).weight){
            return CompareResult.GREATER;
        } else {
            return CompareResult.EQUAL;
        }
    }

}
