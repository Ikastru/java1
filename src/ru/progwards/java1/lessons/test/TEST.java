package ru.progwards.java1.lessons.test;


import java.nio.file.Path;
import java.nio.file.Paths;

public class TEST {

    public static String createFolder(String name) {
        Path path0 = Paths.get("/home/jobe/runs");
        Path path1 =path0.resolve(name);
        System.out.println(path1);
        return path1.getParent()+"";
    }

    public static void main(String[] args) {
        System.out.println(createFolder("gob"));

    }

}
