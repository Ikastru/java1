package ru.progwards.java2.lessons.synchro;

public class Simposion {
    private int quantityPhilosopher;

    public Simposion() {
        quantityPhilosopher = 5;
    }

    public void start() {
        Waiter waiter = new Waiter();

        Philosopher[] philosophers = new Philosopher[quantityPhilosopher];

        for (int i = 0; i < quantityPhilosopher; i++) {
            Fork left = waiter.getForks().get(i);
            Fork right = waiter.getForks().get((i+1)%quantityPhilosopher);

            philosophers[i] = new Philosopher("Philosopher" + (i+1), left, right, 200, 300, waiter);

            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }

    public static void main(String[] args) {
       Simposion simposion = new Simposion();
       simposion.start();
    }
}