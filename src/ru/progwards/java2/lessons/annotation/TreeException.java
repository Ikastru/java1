package ru.progwards.java2.lessons.annotation;

/**
 * Класс обработки ошибок класса дерева, {@link AvlTree}
 * @author Progwards
 * @see AvlTree
 *
 */

public class TreeException extends Exception {
    public TreeException(String message) {
        super(message);
    }
}
