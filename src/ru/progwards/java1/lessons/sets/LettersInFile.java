package ru.progwards.java1.lessons.sets;

/**
 * Реализовать класс, считывающий содержимое файла и возвращающего набор букв,
 * которые встречались в этом файле. Буквы, это латинские [A..Z[ и [a..z] и русские [А..Я]
 * и [а..я], остальные символы надо игнорировать
 *
 * 3.1 Метод public static String process(String fileName) - вернуть все буквы,
 * которые встретились в файле, сконкатенированные в виде строки.
 * Буквы должны быть упорядочены по алфавиту, типа “ADEF...”.
 * Все возникающие исключения, по работе с потоками, пробросить выше.
 */

import java.io.*;
import java.util.*;

public class LettersInFile {

    public static String process(String fileName) throws IOException {
        
        String result = "";

        Comparator<Character> awesomeComparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (o1 == o2) return 0;
                int w1 = 0;
                int w2 = 0;
                if (Character.isUpperCase(o1)) w1 = +2;
                if (Character.isLetter(o1)) w1 = +2;
                else if (Character.isDigit(o1)) w1++;

                if (Character.isUpperCase(o2)) w2 = +2;
                if (Character.isLetter(o2)) w2 = +2;
                else if (Character.isDigit(o2)) w2++;

                if (w1 == w2) return o1 > o2 ? 1 : -1;
                else return w1 > w2 ? -1 : 1;
            }
        };


        try (
                FileReader br = new FileReader(fileName);
                Scanner scanner = new Scanner(br);
        ) {
            String str = "";
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                for (char c : str.toCharArray()) {
                    if (!Character.isAlphabetic(c)){
                        str = str.replace(c, ' ');
                    }
                }

            }
            str.replaceAll(" ", "");
            Character[] arr = new Character[str.length()];
            for (int i = 0; i < arr.length; i++) arr[i] = str.charAt(i);
            Arrays.sort(arr, awesomeComparator);
            int n = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != arr[i-1]) n++;
            }
            Character[] res = new Character[n];
            res[0] = arr[0];
            n = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != arr[i-1]) res[n++] = arr[i];
            }
            result = Arrays.toString(res).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println(process("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\File3.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

