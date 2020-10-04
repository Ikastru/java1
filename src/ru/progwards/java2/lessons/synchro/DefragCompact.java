package ru.progwards.java2.lessons.synchro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefragCompact {

    private int maxHeapSize;
    private byte[] bytes;
    ArrayList<MemoryBlock> free;
    ConcurrentSkipListMap<Integer, MemoryBlock> used;

    public DefragCompact(int maxHeapSize, byte[] bytes, CopyOnWriteArrayList<MemoryBlock> free, ConcurrentSkipListMap<Integer, MemoryBlock> used) {
        this.maxHeapSize = maxHeapSize;
        this.bytes = Arrays.copyOf(bytes, bytes.length);
        this.free = new ArrayList<>(free);
        this.used = new ConcurrentSkipListMap<>(used);
    }

    public void defrag() {
        ListIterator<MemoryBlock> iterator = free.listIterator();
        MemoryBlock curr;
        MemoryBlock prev = iterator.next();
        while (iterator.hasNext()) {
            curr = iterator.next();
            if ((prev.ptr + prev.size)== curr.ptr) {
                prev.size += curr.size;
                iterator.remove();;
            }  else  {
                prev = curr;
            }
        }
    }

    public void compact() {
        if (used.size()==0) {
            free.clear();
            free.add(new MemoryBlock(0, maxHeapSize));
            return;
        }

        MemoryBlock fst = used.firstEntry().getValue();
        int d = fst.ptr;
        if ( d>0) {
            moveBlock(fst,d);
        }
        MemoryBlock prev = fst;
        for (MemoryBlock item : used.values()) {
            d = item.ptr - (prev.ptr + prev.size);
            if (d > 0) {
                moveBlock(item, d);
            }
            prev = item;
        }
        free.clear();
        MemoryBlock mbLast = used.lastEntry().getValue();
        int ptr = mbLast.ptr + mbLast.size;
        free.add(new MemoryBlock(ptr,maxHeapSize-ptr));
    }

    private void moveBlock(MemoryBlock mb, int d) {
        for (int i = mb.ptr; i < mb.ptr + mb.size ; i++) {
            bytes[(i -d)]= bytes[i];
        }
        mb.ptr -= d;
    }
}
