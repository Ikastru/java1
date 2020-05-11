package ru.progwards.java1.lessons.io2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Censor {

    public static void censorFile(String inoutFileName, String[] obscene){

        class CensorException extends Exception{
            String errStr = super.getMessage();
            public String toString(){
                return " "+inoutFileName+errStr;
            }
        }

        StringBuilder strB = new StringBuilder();
        FileReader fileReader  = null;
        try {
            fileReader = new FileReader(inoutFileName);
        } catch (FileNotFoundException e) {
            CensorException censorException = new CensorException();
            censorException.toString();
        }
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            strB = strB.append(scanner.nextLine());
        }
        String strTime = strB.toString();
        String[] strArr = strTime.split(" ");
        for (int i = 0; i<strArr.length-1; i++){
            for (int j = 0; j<obscene.length-1; j++){
                if (strArr[i] == obscene[j]){
                    for (char с : strArr[i].toCharArray()){
                        с = 42;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(strArr));
        String res = Arrays.toString(strArr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
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
        String[] filter = {"Java", "Oracle", "Sun", "Microsystems"};
        censorFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt", filter);
    }
}
