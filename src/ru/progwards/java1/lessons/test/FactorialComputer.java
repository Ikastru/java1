package ru.progwards.java1.lessons.test;

/*
*Эта программа вычисляет факториал числа, заданного в командной строке
* При помощи try/catch обрабатываются возможные ошибки пользовательского ввода
 */

public class FactorialComputer {
    public static void main(String[] args) {
        try{
            int x = Integer.parseInt(args[0]);
            System.out.println(x+" =" + FactorialBigInteg.factorialBI(x));
        }
        //Пользователь забыл задать аргумент, args[0] остаётся неопределённым
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы забыли задать аргумент");
            System.out.println("Формат: java FactorialComputer<число>");
        }
        //Аргумент не является числом
        catch (NumberFormatException e){
            System.out.println("Заданный элемент должен быть целым числом");
        }
        //Аргумент отрицательный
        catch (IllegalArgumentException e){
            //Отображает сообщение, выданное методом factorialBI
            System.out.println("Плохой аргумент: "+e.getMessage());
        }
    }
}
