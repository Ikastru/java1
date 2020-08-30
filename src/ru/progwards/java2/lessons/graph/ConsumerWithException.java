package ru.progwards.java2.lessons.graph;

@FunctionalInterface
public interface ConsumerWithException<T> {
    void accept(T t) throws Exception;
}

