package ru.progwards.java2.lessons.synchro;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

enum PhilosopherState { Get, Eat, Pon }

public class Philosopher implements Runnable {
    static final int maxWaitMs = 100;                          //  должно быть больше 0
    static AtomicInteger token = new AtomicInteger(0);
    static int instances = 0;
    static Random rand = new Random();
    AtomicBoolean end = new AtomicBoolean(false);
    int id;
    PhilosopherState state = PhilosopherState.Get;
    Fork left;
    Fork right;
    int timesEaten = 0;
    int reflectTime = rand.nextInt(maxWaitMs);

    Philosopher() {
        id = instances++;
        left = Simposion.forks.get(id);
        right = Simposion.forks.get((id+1)%Simposion.philosopherCount);
    }

    void reflect() { try { Thread.sleep(reflectTime); }
    catch (InterruptedException ex) {} }

    void eat(){
        state = PhilosopherState.Eat;
    }

    void waitForFork(Fork fork) {
        do {
            if (fork.holder.get() == Fork.ON_TABLE) {
                fork.holder.set(id);
                return;
            } else {
                reflect();                            //  проверить позднее снова
            }
        } while (true);
    }

    public void run() {
        do {
            if (state == PhilosopherState.Pon) {
                state = PhilosopherState.Get;       //  философ проголодался
            } else { // ==PhilosopherState.Get
                if (token.get() == id) {            //  захват
                    waitForFork(left);
                    waitForFork(right);             //  готов есть
                    token.set((id+2)% Simposion.philosopherCount);
                    eat();
                    timesEaten++;
                    reflect();                        //  ест
                    left.holder.set(Fork.ON_TABLE);
                    right.holder.set(Fork.ON_TABLE);
                    state = PhilosopherState.Pon;   //  начинает думать
                    reflect();
                } else {                    //  тогда не его очередь
                    reflect();
                }
            }
        } while (!end.get());
    }
}

