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
                int p = 0;
                int[] f = new int[1001];
                f[0] = 1;
                f[1] = 1;
                for(int i = 2; i <= n; i++) {
                    f[i] = f[i-1] + f[i-2];
                    p = f[i];
                }
                bd = BigDecimal.valueOf(p);
                fiboCache.put(n, bd);
            }
        } else {
            int p = 0;
            int[] f = new int[1001];
            f[0] = 0;
            f[1] = 1;
            for(int i = 2; i <= n; i++) {
                f[i] = f[i-1] + f[i-2];
                p = f[i];
            }
            bd = BigDecimal.valueOf(p);
        }
        return bd;
    }

    public void clearCahe(){
        fiboCache.clear();
        System.out.println(fiboCache);
    }

    public static void test(){
        boolean cachSet = true;
        FiboMapCache fiboMapCacheOn = new FiboMapCache(cachSet);
        long startTime1 = new Date().getTime();
        for (int i=0; i<=1000; i++){
            fiboMapCacheOn.fiboNumber(i);
        }
        long resTime1 = new Date().getTime() - startTime1;
        System.out.println("fiboNumber cacheOn=" + cachSet + " время выполнения " + resTime1);
        cachSet = false;
        FiboMapCache fiboMapCacheOff = new FiboMapCache(cachSet);
        long startTime2 = new Date().getTime();
        for (int i=0; i<=1000; i++){
            fiboMapCacheOn.fiboNumber(i);
        }
        long resTime2 = new Date().getTime() - startTime1;
        System.out.println("fiboNumber cacheOn=" + cachSet + " время выполнения " + resTime2);
    }

    public static void main(String[] args) {
        test();
        boolean cachSet = true;
        FiboMapCache fiboMapCache3 = new FiboMapCache(cachSet);
        for (int i=0; i<=1000; i++){
            fiboMapCache3.fiboNumber(i);
        }
        fiboMapCache3.clearCahe();
    }

}
