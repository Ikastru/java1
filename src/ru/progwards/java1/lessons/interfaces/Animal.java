package ru.progwards.java1.lessons.interfaces;


import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight {
    private double weight;

    public Animal() {

    }

    public enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK}
    public enum FoodKind {UNKNOWN, HAY, CORN}

    public Animal(double weight){
        this.weight = weight;
    }

    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }

    public String toString(){
        return "I am "+ getKind() + "," + " eat " + getFoodKind();
    }

    public double getWeight(){
        return weight;
    }

    public double getFoodCoeff(){
        return 0.02;
    }

    public double calculateFoodWeight(){
        return this.weight*getFoodCoeff();
    }

    public String toStringFull(){
       return "I am "+ getKind() + "," + " eat " + getFoodKind() + " " + calculateFoodWeight();
    }

    public boolean equals(Object anObject){
        if (this == anObject) return true;
        if (anObject==null || this.getClass() != anObject.getClass()) return false;
        double weight = ((Animal)anObject).weight;
        return this.weight==weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    public double getFood1kgPrice(){
        double price;
        switch (this.getFoodKind())
        {
            case HAY:
                price= (double)20;
                break;
            case CORN:
                price= (double) 50;
                break;
            case UNKNOWN:
                price=  (double) 0;
                break;
            default: price= 0;
        }
        return price;
    }

    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }

    public int compareFoodPrice(Animal animal){
        return Double.compare(this.getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeig){
        if (this.weight < ((Animal)smthHasWeig).weight){
            return CompareResult.LESS;
        } else if(this.weight > ((Animal)smthHasWeig).weight) {
            return CompareResult.GREATER;
        } else {
            return CompareResult.EQUAL;
        }
    }

}
