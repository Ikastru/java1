package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try{
            FileReader fileReader  = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            String line = scanner.nextLine();
            StringBuffer buf = new StringBuffer(line);
            String text = coder(buf, code);
            FileWriter writer = new FileWriter(outFileName);
            writer.write(text);
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
        return str1.toString();
    }
}

