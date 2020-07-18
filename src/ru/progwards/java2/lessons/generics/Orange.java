package ru.progwards.java2.lessons.generics;

public class Orange extends Fruit {
    float weight;

    public Orange() {
        this.weight = 1.5f;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

}

