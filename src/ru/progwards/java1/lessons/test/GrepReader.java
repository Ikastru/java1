package ru.progwards.java1.lessons.test;

import java.io.*;

/**
 * Фильтрация строк текста в файле
 * Это подкласс класса BufferedReader, он отфильтровывает
 * все строки текста, не содержащие данный образец
 */

public class GrepReader extends BufferedReader {

    String pattern; //Строка, совпадения с которой мы будем искать

    /**
    *Передаем строку базовому классу и сами запоминаем её
     */

    public GrepReader(Reader in, String pattern){
        super(in);
        this.pattern = pattern;
    }

    /**
     * Это сам фильтр. Вызывается метод базового класса readLine()
     * для получения строк, но возвращаются только строки, содержащие
     * заданный образец. Когда метод readLine() возвращает null(конец файла)
     * мы также возвращаем null
     */

    public final String readLine() throws IOException {
        String line;
        do {line = super.readLine();}
        while ((line!=null) && (line.indexOf(pattern)==-1));
        return line;
    }

    /**
     * Этот класс демонстрирует применение GrepReader.
     * Он печатает сроки файла, содержащие заданную подстроку.
     */

    public static class TestGrep{
        public static void main(String[] args) throws IOException {
            try {
                if (args.length != 2) {
                    try {
                        throw new IllegalAccessException("Неправильное чило аргументов");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                GrepReader in;
                try {
                    in = new GrepReader(new FileReader(args[1]), args[0]);
                    String line;
                    while ((line = in.readLine()) != null) System.out.println(line);
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Формат: java GrepReader$Test" + " <образец> <файл>");
            }
        }
    }
}
