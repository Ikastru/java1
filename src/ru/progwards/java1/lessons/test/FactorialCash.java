package ru.progwards.java1.lessons.test;

/**
 * Эта программа вычисляет факториалы и кэширует результаты в таблице для дальнейшего использования.
 * 20! это самый большой вычисляемый программой факториал (ограничение типа long)
 * поэтому программа выдаст исключение, если аргумент окаждется слишком большим или маленьким.
 **/

public class FactorialCash {
    // Создаем массив для хранения факториала от 0! до 20!.
    static long[] table = new long[21];
    // Инициальзирукм первый элемент массива
    static { table[0] = 1; }  // факториал 0 равен 1.
    // запоминаем номер последнего вычисленного факториала
    static int last = 0;

    public static long factorialCash(int x) throws IllegalArgumentException {
        // Проверяем x.
        if (x >= table.length) throw new IllegalArgumentException("Переполнение, х слишком большое.");
        if (x<0) throw new IllegalArgumentException("х должен быть неотрицательным.");

        // Вычисляем и кэшируем все пока ещё не сохранённые значения.
        while(last < x) {
            table[last + 1] = table[last] * (last + 1);
            last++;
        }
        // Возвращаем кэшированный вариант х.
        return table[x];
    }
}



