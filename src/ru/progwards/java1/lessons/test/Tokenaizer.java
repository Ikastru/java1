package ru.progwards.java1.lessons.test;

/**
 * Работа с регулярным выражением и токенайзером
 *
 * Создайте метод с сигнатурой String swapWords(String sentance), который возвращает слова фразы из sentence
 * через одно, начиная с первого, через пробел в виде строки. Разделители " .,-!\n"
 *
 * Например, слова фразы "Слово - серебро, молчание - золото!" должны быть преобразованы в
 * "серебро Слово золото молчание"
 */

import java.util.StringTokenizer;

public class Tokenaizer {

    public static String swapWords(String sentance) {
        String str = sentance.replaceAll("[!,.-]", "");
        String[] strings = str.split("\\s+");
        for (int i = 1; i<strings.length ; i=i+2){
            String tmp=strings[i-1];
            strings[i-1] = strings[i];
            strings[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<strings.length; i++) {
            sb.append(strings[i] + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String txt =
                "StringTokenizer - этот класс, " +
                        "нам строку разобьёт на раз.";
        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
        while (tokenizer.hasMoreTokens())
            System.out.print(tokenizer.nextToken());
        System.out.println(" ");

        String sentance = "StringTokenizer - этот класс, " +
                "нам строку разобьёт на раз.";
        System.out.println(swapWords(sentance));
    }
}
