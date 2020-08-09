package ru.progwards.java2.lessons.calculator;

import java.util.Stack;

public class RPN {
    //Метод Calculate принимает выражение в виде строки и возвращает результат, в своей работе использует другие методы класса
    public double calculate(String input) {
        String output = getExpression(input); //Преобразовываем выражение в постфиксную запись
        double result = counting(output); //Решаем полученное выражение
        return result; //Возвращаем результат
    }

    //Метод, преобразующий входную строку с выражением в постфиксную запись
    private String getExpression(String input) {
        StringBuilder output = new StringBuilder(); //Строка для хранения выражения
        Stack<Character> operStack = new Stack<>(); //Стек для хранения операторов
        char[] arr = input.toCharArray();

        for (int i = 0; i < input.length(); i++) //Для каждого символа в входной строке
        {
            //Разделители пропускаем
            if (isDelimeter(arr[i]))
                continue; //Переходим к следующему символу

            //Если символ - цифра, то считываем все число
            if (Character.isDigit(arr[i])) //Если цифра
            {
                //Читаем до разделителя или оператора, что бы получить число
                while (!isDelimeter(arr[i]) && !isOperator(arr[i])) {
                    output.append(arr[i]); //Добавляем каждую цифру числа к нашей строке
                    i++; //Переходим к следующему символу
                    if (i == input.length()) break; //Если символ - последний, то выходим из цикла
                }
                output.append(" "); //Дописываем после числа пробел в строку с выражением
                i--; //Возвращаемся на один символ назад, к символу перед разделителем
            }

            //Если символ - оператор
            if (isOperator(arr[i])) //Если оператор
            {
                //Если символ - открывающая скобка
                if (arr[i] == '('){
                    operStack.push(arr[i]); //Записываем её в стек
                }
                else if (arr[i] == ')') //Если символ - закрывающая скобка
                {
                    //Выписываем все операторы до открывающей скобки в строку
                    char s = operStack.pop();

                    while (s != '(') {
                        output.append(s + ' ');
                        s = operStack.pop();
                    }
                } else //Если любой другой оператор
                {
                    if (!operStack.empty()) //Если в стеке есть элементы
                        if (getPriority(arr[i]) <= getPriority(operStack.peek())) //И если приоритет нашего оператора меньше или равен приоритету оператора на вершине стека
                            output.append(operStack.pop().toString() + " "); //То добавляем последний оператор из стека в строку с выражением

                    operStack.push(arr[i]); //Если стек пуст, или же приоритет оператора выше - добавляем операторов на вершину стека
                }
            }
        }
        //Когда прошли по всем символам, выкидываем из стека все оставшиеся там операторы в строку
        while (!operStack.empty())
            output.append(operStack.pop() + " ");

        return output.toString(); //Возвращаем выражение в постфиксной записи
    }

    //Метод, вычисляющий значение выражения, уже преобразованного в постфиксную запись
    private double counting(String input) {
        double result = 0; //Результат
        Stack<Double> temp = new Stack<>(); // стек для решения
        char[] arr = input.toCharArray();

        for (int i = 0; i < input.length(); i++) //Для каждого символа в строке
        {
            //Если символ - цифра, то читаем все число и записываем на вершину стека
            if (Character.isDigit(arr[i]))
            {
                StringBuilder a = new StringBuilder();

                while (!isDelimeter(arr[i]) && !isOperator(arr[i])) //Пока не разделитель
                {
                    a.append(arr[i]) ; //Добавляем
                    i++;
                    if (i == input.length()) break;
                }
                temp.push(Double.parseDouble(a.toString())); //Записываем в стек
                i--;
            }
            else if (isOperator(arr[i])) //Если символ - оператор
            {
                //Берем два последних значения из стека
                Double a = temp.pop();
                Double b = temp.pop();

                switch (arr[i]) //И производим над ними действие, согласно оператору
                {
                    case '+': result = b + a; break;
                    case '-': result = b - a; break;
                    case '*': result = b * a; break;
                    case '/': result = b / a; break;
                    case '^': result = Math.pow(b, a); break;
                }
                temp.push(result); //Результат вычисления записываем обратно в стек
            }
        }
        return temp.peek(); //Забираем результат всех вычислений из стека и возвращаем его
    }

    //Метод возвращает true, если проверяемый символ - разделитель ("пробел" или "равно")
    private boolean isDelimeter(char c) {
        if ((" =".indexOf(c) != -1))
            return true;
        return false;
    }

    //Метод возвращает true, если проверяемый символ - оператор
    private boolean isOperator(char с) {
        if (("+-/*^()".indexOf(с) != -1))
            return true;
        return false;
    }

    //Метод возвращает приоритет оператора
    private byte getPriority(char s) {
        switch (s) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 3;
            case '*':
                return 4;
            case '/':
                return 4;
            case '^':
                return 5;
            default:
                return 6;
        }
    }
}
