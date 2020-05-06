package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) {
        int result = 0;
        try {
            FileReader fileReader  = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.equals("")) {
                    result++;
                }
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return result;
    }
}
