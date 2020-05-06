package ru.progwards.java1.lessons.io1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCount {
    public static int calcEmpty(String fileName) {
        int result = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            String line = in.readLine();
            while (in.readLine() != null) {
                if (line.isEmpty()) {
                    result++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(calcEmpty("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt"));
    }
}
