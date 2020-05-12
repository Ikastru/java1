package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try{
            FileInputStream stream = new FileInputStream (inFileName);
            InputStreamReader reader = new InputStreamReader ( stream );
            BufferedReader buffered_reader = new BufferedReader ( reader );
            String buf, str = null;
            StringBuilder line = new StringBuilder();
            while (( buf = buffered_reader.readLine () ) != null) {
                str += buf+"\r\n";
            }
            StringBuffer buf1 = new StringBuffer(str.toString());
            String text = coder(buf1, code);
            FileWriter writer = new FileWriter(outFileName);
            writer.write(text);
            writer.close();
            buffered_reader.close();
            reader.close();
            stream.close();
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
       char [] cod = {42, 41, 43};
        try {
            codeFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt", "C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File4.txt",  cod, "C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\FileErr.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

