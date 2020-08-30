package ru.progwards.java2.lessons.graph;

/**
 * Задача поиска неиспользуемых объектов
 * <p>
 * Метод public static List<CObject> find(List<CObject> roots, List<CObject> objects)
 * - List<CObject> roots -  список корневых объектов
 * - List<CObject> objects - список всех объектов системы
 * - возвращает список неиспользуемых объектов
 */

import java.util.ArrayList;
import java.util.List;

public class FindUnused {
    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        List<CObject> result = new ArrayList<>();
        for (CObject obj : objects) {
            if (obj.mark != 1) {
                if (obj.mark == 0) {
                    result.add(obj);
                }
            }
            obj.mark = 1;
        }
        return result;
    }
}

