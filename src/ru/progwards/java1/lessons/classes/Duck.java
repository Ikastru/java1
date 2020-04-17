package ru.progwards.java1.lessons.classes;

public class Duck extends Animal {
    public Duck(double weight) {
        super(weight);
    }

    public Duck() {
        super();
    }

    public AnimalKind getKind(){
        return AnimalKind.DUCK;
    }

    public FoodKind getFoodKind(){
        return FoodKind.CORN;
    }

    public double getFoodCoeff(){
        return 0.04;
    }

    public static void main(String[] args) {
        Animal animal = new Animal(5);
        System.out.println(animal.getKind());
        System.out.println(animal.getFoodKind());
        System.out.println(animal.getFoodCoeff());
        System.out.println(animal.toStringFull());
    }
}
