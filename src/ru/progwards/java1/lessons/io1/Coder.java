package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) throws IOException {
        PrintWriter log;
        FileWriter logFile;
        logFile = new FileWriter(logName, true);
        log = new PrintWriter(logFile);
        try(FileReader reader = new FileReader(inFileName)) {
            char[] buf = new char[1024];
            int[] ibuf = new int[1024];
            int c;
            while((c = reader.read(buf))>0){
                if(c < 1024){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
            for(int i=0; i<=1024; i++){
                ibuf[i] = (int)buf[i];
            }
            System.out.println(Arrays.toString(ibuf));
            FileWriter writer = new FileWriter(outFileName, false);
            String text = Arrays.toString(ibuf);
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            log.printf("\n%s: %s\n", LocalDateTime.now(), ex.getMessage());
            ex.printStackTrace(log);
            log.flush();
        }

    }
}

