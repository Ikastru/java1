package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCount {
    public static int calcEmpty(String fileName){
        try {
            int result = 0;
//            Pattern regex = Pattern.compile("^\\s");
//            Matcher testMatcher = regex.matcher(fileName);
            FileReader reader = new FileReader(fileName);
            while((reader.read())!=-1) {
                if (reader.toString().equals(""))
                result++;
            }
            return result;
        }
        catch (Exception e){
            return -1;
        }
    }
}
