package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws Exception{
        try{
            FileReader br = new FileReader(inFileName);
            Scanner scanner = new Scanner(br);
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName));
            try{
             while (scanner.hasNextLine()){
                 String str = scanner.nextLine();
                 for (char c : filter.toCharArray()) {
                     str = str.replace(c, ' ');
                 }
                 bw.write(str.replaceAll(" ", ""));
                 bw.flush();
             }
            } finally {
                br.close();
                bw.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String filter = "—,.()";
        try {
            filterFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt",  "FileOut3.txt", filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
