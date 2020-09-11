package ru.progwards.java2.lessons.synchro;

public class Simposion extends BaseRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.nanoTime() + ": Обед начался");

        Fork[] forks = new Fork[Main.PHILOSOPHERS_COUNT];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        Philosopher[] philosophers = new Philosopher[Main.PHILOSOPHERS_COUNT];
        State state = new State(philosophers);
        for (int i = 0; i < philosophers.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(i, leftFork, rightFork, state);

            threads[i] = new Thread(philosophers[i], "Философ " + (i + 1));
            threads[i].start();
        }

        waitMillis(Main.DINNER_DURATION_IN_MS);
        stopPhilosophers(philosophers);
        stopThreads(threads);

        System.out.println(System.nanoTime() + ": Обед закончен");
        printStats(philosophers);
    }

    private static void stopPhilosophers(Philosopher[] philosophers) throws InterruptedException {
        for (Philosopher philosopher: philosophers) {
            if (!philosopher.stopped.get()) {
                philosopher.stop();
            }
        }
        waitMillis(200);
    }

    private static void printStats(Philosopher[] philosophers) {
        int totalCount = 0;
        for (Philosopher philosopher : philosophers) {
            totalCount += philosopher.eatSum.intValue();
        }
        if (totalCount > 0) {
            System.out.println("Статистика приёма пищи:");
            System.out.println("Общее время обеда: " + totalCount);
            for (int i = 0; i < philosophers.length; i++) {
                System.out.println(
                        "Философ " + (i + 1) + ": " + (100.0 * philosophers[i].eatSum.intValue() / totalCount) + " %" + " от общего времени" + " или " + philosophers[i].eatSum.intValue()/1000 + " секунд");
            }
        }
    }
}