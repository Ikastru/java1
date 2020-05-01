package ru.progwards.java1.lessons.test;

/*
*Эта программа отображает факториалы чисел, введённые пользователем,
* в интерактивном режиме
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FactorialQuoter {
    public static void main(String[] args) throws Exception {
        //Подготовка чтения строк, вводимых пользователем
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            System.out.print("Факториал >");
            //Считывание введённой пользователем строки
            String line = in.readLine();
            //Если считан конец файла или пользователь ввёд quit
            if ((line==null) || line.equals("quit")) break;
            //Пытаемся проанализировать строку, вычислить и напечатать факториал
            try{
                int x = Integer.parseInt(line);
                System.out.println(x + "!= " + FactorialBigInteg.factorialBI(x));
            }
            catch (Exception e){
                System.out.println("Некорректный ввод");
            }
        }
    }
}
