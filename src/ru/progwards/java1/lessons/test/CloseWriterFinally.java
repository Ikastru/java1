package ru.progwards.java1.lessons.test;

import java.io.FileWriter;
import java.io.IOException;

public class CloseWriterFinally {
    public static void main(String[] args) {
        try{
            FileWriter writer = new FileWriter("?????", false);
            try{
                writer.write("какая-то строка");
            } catch (IOException e){
                System.out.println(e);
            } finally {
                writer.close();
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
