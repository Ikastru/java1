package ru.progwards.java1.lessons.io1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCount {
    public static int calcEmpty(String fileName){
        try {
            int result = 0;
            Pattern regex = Pattern.compile("\\s");
            Matcher testMatcher = regex.matcher(fileName);
            while (testMatcher.find()) {
                result++;
            }
            return result;
        }
        catch (Exception e){
            return -1;
        }
    }
}
