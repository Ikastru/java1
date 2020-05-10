package ru.progwards.java1.lessons.test;

import java.io.File;

/**
 * Это класс состоит из статического метода delete() и самостоятельной
 * программы, удаляющей заданный файл или каталог
 */
public class DeleteFile {

    public static void main(String[] args) {
        if (args.length != 1){
            System.err.println("Формат команды: java Delete <файл или каталог>");
            System.exit(0);
        }
        try {
            delete(args[0]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void delete(String filename){
        File f = new File(filename);
        if (!f.exists()) fail ("Delete: нет такого файла или каталога: " + filename);
        if (!f.canWrite()) fail ("Delete: защищён от записи: " + filename);
        //Если это каталог, убеждаеся, что он пуст
        if (f.isDirectory()){
            String[] files = f.list();
            if(files.length>0){
                fail ("Delete: каталог не пустой: " + filename);
            }
        }
        boolean success = f.delete();
        if (!success) fail("Delete: удаление не удалось");
    }

    protected static void fail(String msg) throws IllegalArgumentException{
        throw new IllegalArgumentException(msg);
    }

}
