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

    private Map<Integer, BigDecimal> fiboCache = new HashMap<>();
    private boolean cacheOn;

    public static int fibonacci(int n)  {
//        int n0=0, n1=1, n2=1;
//        for (int i=0; i<=n; i++){
//            n2=n1+n0;
//            System.out.print(n2+" ");
//            n0=n1;
//            n1=n2;
//        }
//        return n2;
//        int cache1 = 1;
//        int cache2 = 1;
//        //Новое значение
//        int cache3 = 1;
//        if (n==0 || n==1){
//            cache3 = 1;
//        } else {
//            for (int i = 2; i <= n; i++) {
//                cache3 = cache1 + cache2;
//                cache1 = cache2;
//                cache2 = cache3;
//            }
//        }
//        return cache3;
        if(n == 0)
            return 1;
        else if(n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
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
//        System.out.println(fiboCache);
    }

    public static void test(){
        boolean cachSet = true;
        FiboMapCache fiboMapCacheOn = new FiboMapCache(cachSet);
        long startTime1 = new Date().getTime();
        for (int i=0; i<=10; i++){
            fiboMapCacheOn.fiboNumber(i);
        }
        long resTime1 = new Date().getTime() - startTime1;
        System.out.println("fiboNumber cacheOn=" + cachSet + " время выполнения " + resTime1);
        cachSet = false;
        FiboMapCache fiboMapCacheOff = new FiboMapCache(cachSet);
        long startTime2 = new Date().getTime();
        for (int i=0; i<=10; i++){
            fiboMapCacheOn.fiboNumber(i);
        }
        long resTime2 = new Date().getTime() - startTime1;
        System.out.println("fiboNumber cacheOn=" + cachSet + " время выполнения " + resTime2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        test();
        boolean cachSet = true;
        FiboMapCache fiboMapCache3 = new FiboMapCache(cachSet);
        for (int i=0; i<=10; i++){
            fiboMapCache3.fiboNumber(i);
        }
        fiboMapCache3.clearCahe();
    }

}
