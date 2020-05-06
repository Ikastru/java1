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
            while (in.readLine() != null) {
                if (in.readLine().equals("")) {
                    result++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result/3;

    }
}
