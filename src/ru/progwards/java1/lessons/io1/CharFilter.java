package ru.progwards.java1.lessons.io1;

import java.io.*;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws Exception{
        try{
            String str;

            BufferedReader br = new BufferedReader(new FileReader(inFileName));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName));
            try{
             while ((str = br.readLine())!=null){
                String rez = str.replaceAll(filter, "");
                bw.write(rez);
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
        String filter = "-,.()";
        try {
            filterFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt",  "FileOut3.txt", filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
