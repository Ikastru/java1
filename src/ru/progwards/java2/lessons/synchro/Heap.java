package ru.progwards.java2.lessons.synchro;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Heap {
    public int maxHeapSize;
    public byte[] bytes;
    CopyOnWriteArrayList<MemoryBlock> free;
    ConcurrentSkipListMap<Integer, MemoryBlock> used;
    int magicSortNumber = 10_000_000;
    Long sync = 0L;

    public Heap(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[maxHeapSize];
        free = new CopyOnWriteArrayList<>();
        free.add(new MemoryBlock(0, maxHeapSize));
        used = new ConcurrentSkipListMap<>(Integer::compareTo);
    }

    public int malloc(int size) throws OutOfMemoryException {
        int ptr = mallocOneBlock(size);
        if (ptr < 0) {
            ptr = mallocOneBlock(size);
            if (ptr < 0) {
                throw new OutOfMemoryException("Нет свободных блоков для ", size);
            }
        }
        return ptr;
    }

    public int mallocOneBlock(int size) {
        synchronized (this.sync) {
            int ptr = fastFindFreeBlock(size);
            if (ptr < 0) {
                return -1;
            }
            MemoryBlock block = free.get(ptr);
            if (block.size == size) {
                used.put(block.ptr, block);
                free.remove(ptr);
                return block.ptr;
            }
            MemoryBlock useMB = new MemoryBlock(block.ptr, size);
            used.put(useMB.ptr, useMB);
            block.ptr = block.ptr + size;
            block.size = block.size - size;
            if (block.size == 0) {
                free.remove(ptr);
            } else {
                //Сделано для оптимизации - нельзя всякий раз при аллоцировании
                //запускать сортировку свободного пространства - в этом нет никакого смысла
                //только тратится время.
                if (free.size() % magicSortNumber == 0) {
                    free.sort(Comparator.comparing(a -> a.size));
                }
            }
            return useMB.ptr;
        }
    }

    private int fastFindFreeBlock(int size) {
        synchronized (this.sync) {
            int ptr = -1;
            int beg = 0;
            int end = free.size() - 1;
            while (beg <= end) {
                int mid = (beg + end) / 2;
                MemoryBlock block = free.get(mid);
                if (block.size < size) {
                    beg = mid + 1;
                } else if (block.size > size) {
                    end = mid - 1;
                    ptr = mid;
                } else {
                    ptr = mid;
                    break;
                }
            }
            return ptr;
        }
    }

    public void free(int ptr) throws InvalidPointerException {
        synchronized (this.sync) {
            MemoryBlock block = used.get(ptr);
            if (block == null) {
                throw new InvalidPointerException("Неверный адрес участка памяти", ptr);
            }
            free.add(block);
            free.sort(Comparator.comparingInt(a -> a.size));
            used.remove(ptr);
        }
    }

    public void defrag() {
        synchronized (this.sync) {
            ListIterator<MemoryBlock> iterator = free.listIterator();
            MemoryBlock curr;
            MemoryBlock prev = iterator.next();
            while (iterator.hasNext()) {
                curr = iterator.next();
                if ((prev.ptr + prev.size) == curr.ptr) {
                    prev.size += curr.size;
                    iterator.remove();
                    ;
                } else {
                    prev = curr;
                }
            }
        }
    }
}
