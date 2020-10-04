package ru.progwards.java2.lessons.synchro;

public class Philosopher implements Runnable {
    private String name;
    private Fork right;
    private Fork left;
    private long reflectTime;
    private long eatTime;
    private Waiter waiter;
    Long sync = 0L;


    public Philosopher(String name, Fork left, Fork right, long reflectTime, long eatTime, Waiter waiter) {
        this.name = name;
        this.right = right;
        this.left = left;
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        this.waiter = waiter;
    }

    public Fork getRight() {
        return right;
    }

    public Fork getLeft() {
        return left;
    }

    public void setRight(Fork right) {
        this.right = right;
    }

    public void setLeft(Fork left) {
        this.left = left;
    }

    public void reflect() {
        System.out.println(name + " размышляет");
        try {
            Thread.sleep(reflectTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        System.out.println(name + " ест");
        try {
            Thread.sleep(eatTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putUpLeftFork(Waiter waiter) {
            if (waiter.getForks().get(left.getId()).getAvailability()) {
                System.out.println(name + " взял левую вилку");
                left.setAvailability(false);
                waiter.getForks().put(left.getId(), left);
            }
    }

    public void putUpRightFork(Waiter waiter) {
            if (waiter.getForks().get(right.getId()).getAvailability()) {
                System.out.println(name + " взял правую вилку");
                right.setAvailability(false);
                waiter.getForks().put(right.getId(), right);
            }
    }

    public void putDownRightFork(Waiter waiter) {
            right.setAvailability(true);
            System.out.println(name + " положил правую вилку");
            waiter.getForks().put(right.getId(), right);
    }

    public void putDownLeftFork(Waiter waiter) {
            left.setAvailability(true);
            System.out.println(name + " положил левую вилку");
            waiter.getForks().put(left.getId(), left);
    }

    @Override
    public void run() {
        while (true) {
            reflect();
            synchronized (this.sync) {
                putUpLeftFork(waiter);
                if (!left.getAvailability())
                    putUpRightFork(waiter);
                if (!right.getAvailability() && !left.getAvailability()) {
                    eat();
                    putDownRightFork(waiter);
                    putDownLeftFork(waiter);
                }
            }
        }
    }
}