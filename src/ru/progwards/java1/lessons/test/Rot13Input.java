package ru.progwards.java1.lessons.test;
 /*
 *Эта программа читает вводимые пользователем строки,
 * копирует их при помощи шифра Rot13
 * и затем печатает закодированную строку
  */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rot13Input {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            System.out.print(">");
            String line = in.readLine();
            if ((line==null) || line.equals("quit")) break;
            StringBuffer buf = new StringBuffer(line);
            for (int i=0; i<buf.length(); i++)
                buf.setCharAt(i, rot13(buf.charAt(i)));
            System.out.println(buf);
        }
    }

 /*
    *Этот метод сдвигает по кругу каждую букву алфавита на 13 мест
    * Поскольку в латинском алфавите 26 букв этот метод и шифрует
    * и дешифрует одновременно
  */
    public static char rot13(char c){
        if ((c >= 'A') && (c <= 'Z')){
            c+=13;
            if (c > 'Z') c -= 26;
        }
        if ((c >= 'a') && (c <= 'z')){
            c += 13;
            if (c > 'z') c -= 26;
        }
        return c;
    }

}
