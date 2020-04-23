package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight<T> {

    public enum CompareResult {LESS, EQUAL, GREATER};
    public CompareResult compareWeight(T smthWeight);
}
