package ru.progwards.java2.lessons.synchro;

import java.util.ArrayList;

public class Simposion {
    static final int philosopherCount = 5;
    static final int runSeconds = 15;
    static ArrayList<Fork> forks = new ArrayList<>();
    static ArrayList<Philosopher> philosophers = new ArrayList<>();

    public static void start(){
        for (Philosopher p : philosophers) new Thread(p).start();
    }

    public static void stop(){
        for (Philosopher p : philosophers) p.end.set(true);
        for (Philosopher p : philosophers)
            System.out.printf("Фил%02d: ел %,d раз, %,d/sec\n",
                    p.id, p.timesEaten, p.timesEaten/runSeconds);
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < philosopherCount ; i++) forks.add(new Fork());
        for (int i = 0 ; i < philosopherCount ; i++)
            philosophers.add(new Philosopher());
        start();
        long endTime = System.currentTimeMillis() + (runSeconds * 1000);

        do {                                                    //  напечатать статусы
            StringBuilder sb = new StringBuilder("|");

            for (Philosopher p : philosophers) {
                sb.append(p.state.toString());
                sb.append("|");            //  Моментальный снимок
            }

            sb.append("     |");

            for (Fork f : forks) {
                int holder = f.holder.get();
                sb.append(holder==-1?"   ":String.format("Ф%02d",holder));
                sb.append("|");
            }

            System.out.println(sb.toString());
            try {Thread.sleep(1000);} catch (Exception ex) {}
        } while (System.currentTimeMillis() < endTime);

        stop();
    }
}
