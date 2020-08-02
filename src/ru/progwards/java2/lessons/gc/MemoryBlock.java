package ru.progwards.java2.lessons.gc;

public class MemoryBlock {
    public int ptr;
    public int size;

    public MemoryBlock(int ptr, int size) {
        this.ptr = ptr;
        this.size = size;
    }
}

