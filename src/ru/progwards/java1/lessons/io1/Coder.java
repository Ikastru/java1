package ru.progwards.java1.lessons.io1;

import java.io.*;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try(BufferedReader in = new BufferedReader(new FileReader(inFileName))) {
            String line = in.readLine();
            StringBuffer buf = new StringBuffer(line);
            String text = coder(buf, code);
            FileWriter writer = new FileWriter(outFileName);
            writer.write(text);
            writer.flush();
        }
        catch(Exception ex){
//            System.out.println(ex.getMessage());
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

