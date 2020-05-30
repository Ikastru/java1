package ru.progwards.java1.lessons.maps;

/**
 * Кеш для чисел Фибоначчи на Map. Кеш имитирует таковой на сервере, когда идут
 * запросы со случайными параметрами от разных пользователей. Сам алгоритм
 * чисел Фибоначчи - это просто пример некоего алгоритма, который долго работает,
 * в сравнении с вытаскиванием значения из кэша. Считается, что кеш ничего не знает
 * об алгоритме, и умеет только сохранять и доставать значения по ключу.
 *
 *
 * 1.1 Определить приватную локальную переменную fiboCache как
 * Map<Integer, BigDecimal>
 *
 * 1.2 Определить конструктор public FiboMapCache(boolean cacheOn) - включен ли кэш.
 * При cacheOn = true кэш работает, при cacheOn = false - выключен
 *
 * 1.3 Реализовать public BigDecimal fiboNumber(int n). Алгоритм работы следующий:
 *
 * в функции проверить, находится ли вычисленное значение для n в кэше, и если да -
 * вернуть его из кэша, если нет - рассчитать и добавить в кэш.
 * Учитывать значение переменной cacheOn
 *
 * 1.4 Реализовать метод public void clearCahe() который устанавливает переменную
 * fiboCache в null
 *
 * 1.5 Для проверки работы реализовать public static void test() - тест для
 * расчета чисел Фибоначчи от n = 1 до 1000 включительно и замерить разницу
 * во времени с on = true и on = false, результат вывести на экран в формате
 * "fiboNumber cacheOn=??? время выполнения ???" для cacheOn=true и cacheOn=false,
 * вместо ??? вывести реальные значения в мсек.
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {

    private static Map<Integer, BigDecimal> fiboCache = new HashMap<>();
    private boolean cacheOn;

    public static int fibonacci(int n)  {
        if(n == 1)
            return 1;
        else if(n == 2)
            return 1;
        else {
            int n0=1, n1=1, n2=1;
        for (int i=3; i<=n; i++){
            n2=n1+n0;
//            System.out.print(n2+" ");
            n0=n1;
            n1=n2;
        }
        return n2;
        }
    }

    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }

    public BigDecimal fiboNumber(int n){
        BigDecimal bd = null;
        if (cacheOn){
            BigDecimal oldVal = fiboCache.getOrDefault(n, null);
            if (oldVal != null){
                bd = oldVal;
            } else {
                bd = BigDecimal.valueOf(fibonacci(n));
                fiboCache.put(n, bd);
            }
        } else {
            bd = BigDecimal.valueOf(fibonacci(n));
        }
        return bd;
    }

    public void clearCahe(){
        fiboCache.clear();
    }

    public static void test(){
        boolean cachSet = false;
        FiboMapCache fiboMapCacheOn = new FiboMapCache(cachSet);
        long startTime1 = new Date().getTime();
        for (int i=1; i<=1000; i++){
            System.out.println(fiboMapCacheOn.fiboNumber(i));
        }
        long resTime1 = new Date().getTime() - startTime1;
        System.out.println("fiboNumber cacheOn=" + cachSet + " время выполнения " + resTime1);
        cachSet = true;
        FiboMapCache fiboMapCacheOff = new FiboMapCache(cachSet);
        long startTime2 = new Date().getTime();
        for (int i=1; i<=1000; i++){
            System.out.println(fiboMapCacheOff.fiboNumber(i));
        }
        long resTime2 = new Date().getTime() - startTime2;
        System.out.println("fiboNumber cacheOff=" + cachSet + " время выполнения " + resTime2);
    }

    public static void main(String[] args) {
        for (int i = 1; i<=10; i++) {
            System.out.println(fibonacci(i));
        }
        FiboMapCache fiboMapCache = new FiboMapCache(false);
        for (int i = 1; i<=10; i++) {
            System.out.println(fiboMapCache.fiboNumber(i));
        }
        test();
        boolean cachSet = true;
        FiboMapCache fiboMapCache3 = new FiboMapCache(cachSet);
        for (int i=1; i<=10; i++){
            System.out.println(fiboMapCache3.fiboNumber(i));
        }
        fiboMapCache3.clearCahe();
    }

}
