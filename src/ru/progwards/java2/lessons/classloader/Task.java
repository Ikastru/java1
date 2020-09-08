package ru.progwards.java2.lessons.classloader;

import java.util.Arrays;

public class Task {
    private long modifiedTime;

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }
    // метод для обработки данных и возвращения результата в виде строки
    public String process(byte[] data){
        return Arrays.toString(data);
    }
}
