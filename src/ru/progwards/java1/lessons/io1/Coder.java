package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try{
            FileReader fileReader  = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            StringBuilder line = new StringBuilder();
            while (scanner.hasNextLine()) {
                 line.append(scanner.nextLine()+"\n");
            }
            StringBuffer buf = new StringBuffer(line.toString());
            String text = coder(buf, code);
            FileWriter writer = new FileWriter(outFileName);
            writer.write(text);
            writer.close();
        }
        catch(Exception ex){
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream(logName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ex.printStackTrace(pw);
        }
    }

    public static String coder(StringBuffer str, char[] code1){
        StringBuilder str1 = new StringBuilder();
        for (int i=0; i<str.length(); i++) {
            str1 = str1.append(code1[(int)str.charAt(i)]);
        }
        return str1.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
    }

    public static void main(String[] args) {
        String filter = "a";
        filter=filter.repeat(96)+"12345";
        try {
            codeFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt", "C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File4.txt",  filter.toCharArray(), "C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\FileErr.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

