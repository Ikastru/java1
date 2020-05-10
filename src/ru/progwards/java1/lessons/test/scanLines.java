package ru.progwards.java1.lessons.test;

import java.util.Scanner;

public class scanLines {

    public static void scanLines() {
        Scanner scanner = new Scanner(System.in);
        String line;
        for (;;) {
            line = scanner.nextLine();
            if ((line == null) || "/stop".equals(line)) {
                break;
            } else
            if (line.contains("Привет")) {
                System.out.println("Здравствуйте!");
            } else if (line.contains("как дела")) {
                System.out.println("Хорошо");
            } else if (line.contains("/stop")) {
                break;
            } else {
                System.out.println(line);
            }
        }
    }
    public static void main(String[] args) {
        scanLines();
    }

}
