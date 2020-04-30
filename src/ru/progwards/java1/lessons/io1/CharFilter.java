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
            }} finally {
    br.close();
    bw.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
