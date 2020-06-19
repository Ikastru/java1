package ru.progwards.java1.lessons.files;

import java.nio.file.Path;

public class Additional implements Comparable<Additional>{

    public Path filename;
    public String last; //  lastModifiedTime
    public String size; //  размер в строку
    public String line; //  первая строка

    public Additional(Path filename, String last, String size, String line) {
        this.filename = filename;
        this.last = last;
        this.size = size;
        this.line = line;
    }

    @Override
    public String toString() {
        return "OneFile{" +
                "filename=" + filename +
                ", last='" + last + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int compareTo(Additional file) {
        String tKey = this.filename.getFileName() + this.last + this.size;
        String oKey = file.filename.getFileName() + file.last + file.size;
        return tKey.compareTo(oKey);
    }
}
