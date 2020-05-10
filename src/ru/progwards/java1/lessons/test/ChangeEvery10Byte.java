package ru.progwards.java1.lessons.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class ChangeEvery10Byte {
    public static String setStars(String filename) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        try (RandomAccessFile com = new RandomAccessFile(filename, "rw")) {
            char[] arr = com.readLine().toCharArray();
            for (int j = 0; j < arr.length; j++){
                sb1.append(arr[j]);
            }
            System.out.println(sb1.toString());
            for (int i = 9; i < arr.length; i += 10) {
                sb.append(arr[i]);
                arr[i]=42;
            }
            String str1 = Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
            System.out.println(str1);
            com.seek(0);
            com.writeBytes(str1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return sb1.toString();
        }
        return sb.toString();
    }
}
