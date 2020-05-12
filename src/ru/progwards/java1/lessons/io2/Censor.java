package ru.progwards.java1.lessons.io2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class CensorException extends Exception{
    String errStr = super.getMessage();
    public String toString(){
        return " "+errStr+":"+errStr;
    }
}

public class Censor {

    public static void censorFile(String inoutFileName, String[] obscene) {

        StringBuilder strB = new StringBuilder();
        FileReader fileReader  = null;
        try {
            fileReader = new FileReader(inoutFileName);
        } catch (Exception e) {
            CensorException censorException = new CensorException();
            censorException.toString();
        }
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            strB = strB.append(scanner.nextLine());
        }
        String strTime = strB.toString();
        String[] strArr = strTime.split(" ");
        for (int i = 0; i<strArr.length; i++){
            for (int j = 0; j<obscene.length; j++){
                if (strArr[i].equals(obscene[j])){
                    char [] ObsArr = obscene[j].toCharArray();
                    for (int k=0; k<ObsArr.length; k++){
                        ObsArr[k] =42;
                    }
                    strArr[i] = Arrays.toString(ObsArr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
//                    for (char с : strArr[i].toCharArray()){
//                        с = '*';
//                    }
                }
            }
        }
        System.out.println(Arrays.toString(strArr));
        String res = Arrays.toString(strArr).replace("[", "").replace("]", "").replace(",", "");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inoutFileName));
            bw.write(res);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            CensorException censorException1 = new CensorException();
            censorException1.toString();
        }
    }

    public static void main(String[] args) {
        String[] filter = {"count", "write", "day"};
        try {
            censorFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt", filter);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
