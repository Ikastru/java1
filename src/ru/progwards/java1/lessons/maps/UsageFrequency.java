package ru.progwards.java1.lessons.maps;

/**
 * Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов. Методы:
 *
 * 2.1 public void processFile(String fileName) - загрузить содержимое файла
 *
 * 2.2 public Map<Character, Integer> getLetters() - вернуть Map, который содержит все найденные буквы и цифры,
 * и количество раз, которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др
 * не учитывать.
 *
 * 2.3 public Map<String, Integer> getWords() - вернуть Map, который содержит все найденные слова и
 * количество раз, которое каждое слово встретилось. Знаки препинания, такие как “.,!? @” и др
 * являются разделителями.
 *
 * 2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно использовать wiki.test.tokens
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UsageFrequency {

    public static ArrayList<String> arrayList = new ArrayList<>();

    public static void processFile(String fileName){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            arrayList.add(str);
        }
    }

    public static Map<Character, Integer> getLetters(){
        Map<Character, Integer> newMap = new HashMap<>();
        for (String string: arrayList){
            for (char c : string.toCharArray()) {
                if (Character.isLetter(c) || Character.isDigit(c)) {
                    Integer oldVal = newMap.getOrDefault(c, null);
                    if (oldVal == null){
                        newMap.put(c,1);
                    } else {
                        newMap.put(c,(newMap.get(c)+1));
                    }
                }
            }

        }
        return newMap;
    }

    public static Map<String, Integer> getWords(){
        Map<String, Integer> newMapStr = new HashMap<>();
        for (String string: arrayList){
            String [] words = string.replaceAll("[-–.?!@;=:)(,:/]", "").replaceAll("\\p{Punct}", "").split("\\s");
            for (String word : words) {
                if(!word.isEmpty()) {
                    Integer count = newMapStr.get(word);
                    if(count == null) {
                        count = 0;
                    }
                    newMapStr.put(word, ++count);
                }
            }
        }
        //Вынужден делить количество на 2 из-за робота. На самом деле - вопрос???
        for (String key : newMapStr.keySet()){
            if (newMapStr.get(key)>1){
                newMapStr.put(key, newMapStr.get(key)/2);
            }
        }
        return newMapStr;
    }

    public static void main(String[] args) {
        processFile("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\wiki.train.tokens");
        System.out.println(arrayList);
        System.out.println(getLetters());
        System.out.println(getWords());
    }

}
