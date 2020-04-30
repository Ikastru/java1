package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coder {
    static Logger LOGGER;
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {

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
        catch(Exception ex){
            LOGGER.log(Level.WARNING,"что-то пошло не так" , logName);
        }

    }
}

