package ru.progwards.java1.lessons.io1;

import java.io.*;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try(BufferedReader in = new BufferedReader(new FileReader(inFileName))) {
            String line = in.readLine();
            StringBuffer buf = new StringBuffer(line);
            while(line!=null){
                for (int i=0; i<buf.length(); i++)
                    buf.setCharAt(i, coder(buf.charAt(i)));
                System.out.println(buf);
            }
            String text = buf.toString();
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

    public static char coder(char c){
        if ((c >= 'A') && (c <= 'Z')){
            c+=13;
            if (c > 'Z') c -= 26;
        }
        if ((c >= 'a') && (c <= 'z')){
            c += 13;
            if (c > 'z') c -= 26;
        }
        return c;
    }
}

