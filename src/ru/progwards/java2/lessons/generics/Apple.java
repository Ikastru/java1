package ru.progwards.java2.lessons.generics;

public class Apple extends Fruit {
    float weight;

    public Apple() {
        this.weight = 1.0f;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

}
