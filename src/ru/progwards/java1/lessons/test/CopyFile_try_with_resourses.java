package ru.progwards.java1.lessons.test;

/* Версия программы CopyFile, в которой демонстрируется
применение оператора try с ресурсами и управление
двумя ресурсами (в данном случае - файлами)
в одном операторе try
(Шилдт)
*/

import java.io.*;
import java.io.IOException;

public class CopyFile_try_with_resourses {
    public static void main(String args[]) throws IOException {
        int i;

        // сначала убедиться, что заданы оба файла
        if (args.length != 2) {
            System.out.println("Иcпoльзoвaниe: CopyFile откуда куда");
            return;
        }

        // открыть два файла и управлять ими в операторе try
        try (FileInputStream fin = new FileInputStream(args[0]);
             FileOutputStream fout = new FileOutputStream(args[1])) {

            do {
                i = fin.read();
                if (i != -1) fout.write(i);
            } while (i != -1);

        } catch (IOException e) {
            System.out.println("Oшибкa ввода-вывода: " + e);
        }
    }
}
