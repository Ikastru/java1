package ru.progwards.java1.lessons.interfaces;

import ru.progwards.java1.lessons.interfaces.Animal;

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
        Duck duck = new Duck(5);
        Hamster hamster = new Hamster(5);
        System.out.println(duck.equals(hamster));
        System.out.println(duck.compareFoodPrice(hamster));
    }
}
