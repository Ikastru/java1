package ru.progwards.java1.lessons.io2;

import java.util.ArrayList;
import java.util.Collections;

public class Translator {
    private ArrayList<String> inStr = new ArrayList<String>();
    private ArrayList<String> outStr = new ArrayList<String>();
    private ArrayList<String> sentenceArr = new ArrayList<String>();

    Translator(String[] inLang, String[] outLang){
        try {
            Collections.addAll(this.inStr, inLang);
            Collections.addAll(this.outStr, outLang);
//        for(int i=0; i<inLang.length; i++){
//            this.inStr.add(inLang[i]);
//        }
//        for(int j=0; j<outLang.length; j++){
//            this.outStr.add(outLang[j]);
//        }
        } catch (Exception ignore) { }
    }

    public String translate(String sentence){
        String str = sentence.toLowerCase();
        String[] s = str.split(" ");
        for(int i=0; i<s.length; i++){
            this.sentenceArr.add(s[i]);
        }
        StringBuilder rez = new StringBuilder();
        for(int j=0; j<inStr.size(); j++){
            for(int k=0; k<sentenceArr.size(); k++) {
                if (sentenceArr.get(j).equals(inStr.get(k))) {
                    rez.append(" ");
                    rez.append(outStr.get(j));
                }
            }
        }
        return rez.toString().trim();
    }

    public static void main(String[] args) {
        String[] arr1 = {"зубр", "орешки", "кетчуп"};
        String[] arr2 = {"полотно", "верх", "катомба"};
        Translator translator = new Translator(arr1, arr2);
        System.out.println(translator.translate("fackupp орешки зубр"));
    }
}
