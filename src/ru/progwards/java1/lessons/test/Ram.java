package ru.progwards.java1.lessons.test;

/**
 * Эмулятор RAM машины
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Ram {
    private static String FILE_NAME;
    List<String> lines;

    public Ram(String filename) {
        this.FILE_NAME = filename;
    }

    public void calculate() {
        try {
            lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pat = Pattern.compile(" ");
        List<String> allProgram = new ArrayList<>();
        for (String line : lines) {
            String strs[] = pat.split(line);
            for (int i = 0; i<strs.length; i++){
                allProgram.add(strs[i]);
            }
        }
        System.out.println(allProgram);
    }


    public static void main(String[] args) {
        Ram ram = new Ram("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\FileRam.txt");
        ram.calculate();
    }
}


