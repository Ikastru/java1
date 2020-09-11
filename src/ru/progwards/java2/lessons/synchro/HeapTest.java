package ru.progwards.java2.lessons.synchro;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class HeapTest {
    static final int maxSize = 19327;
    static final int maxSmall = 10;
    static final int maxMedium = 100;
    static final int maxBig = 1000;
    static final int maxHuge = 10000;
    static int allocated = 0;

    static class Block {
        public int ptr;
        public int size;

        public Block(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }
    }

    static int getRandomSize() {
        int n = Math.abs(ThreadLocalRandom.current().nextInt() % 10);
        int size = Math.abs(ThreadLocalRandom.current().nextInt());
        if (n < 6)
            size %= maxSmall;
        else if (n < 8)
            size %= maxMedium;
        else if (n < 9)
            size %= maxBig;
        else
            size %= maxHuge;
        return size > (maxSize - allocated) - 1 ? (maxSize - allocated) / 2 + 1 : size + 1;
    }

    public static void main(String[] args) throws InvalidPointerException, OutOfMemoryException {
        Heap heap = new Heap(maxSize);
        ArrayDeque<Block> blocks = new ArrayDeque<>();
        final AtomicInteger[] count = {new AtomicInteger()};
        final int[] allocTime = {0};
        final int[] freeTime = {0};

        long start = System.currentTimeMillis();
        // alloc and free 30% random
        Runnable r = () -> {
                while ((maxSize - allocated) > 50000) {
                    long lstart, lstop;
                    int size = getRandomSize();
                    allocated += size;
                    count[0].getAndIncrement();
                    lstart = System.currentTimeMillis();
                    int ptr = heap.malloc(size);
                    DefragCompact defragCompact = new DefragCompact(maxSize, heap.bytes, heap.free, heap.used);
                    defragCompact.compact();
                    lstop = System.currentTimeMillis();
                    allocTime[0] += lstop - lstart;
                    blocks.offer(new Block(ptr, size));
                    int n = Math.abs(ThreadLocalRandom.current().nextInt() % 25);
                    if (n == 0) {
                        //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
                        for (int i = 0; i < 5; i++) {
                            Block block = blocks.poll();
                            if (block == null)
                                break;
                            lstart = System.currentTimeMillis();
                            heap.free(block.ptr);
                            lstop = System.currentTimeMillis();
                            freeTime[0] += lstop - lstart;
                            allocated -= block.size;
                        }
                        //blocks.remove(n);
                        defragCompact.defrag();
                    }
                    n = Math.abs(ThreadLocalRandom.current().nextInt() % 100000);
                    if (n == 0)
                        System.out.println(maxSize - allocated);
                }
        };

        Runnable r2 = () -> {

                DefragCompact defragCompact = new DefragCompact(maxSize, heap.bytes, heap.free, heap.used);
                defragCompact.compact();
                defragCompact.defrag();
        };
        Thread t = new Thread(r2);
        t.setDaemon(true);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        long stop = System.currentTimeMillis();
        System.out.println("malloc time: " + allocTime[0] + " free time: " + freeTime[0]);
        System.out.println("total time: " + (stop - start) + " count: " + count[0]);
    }
}