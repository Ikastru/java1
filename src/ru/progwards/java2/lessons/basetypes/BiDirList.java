package ru.progwards.java2.lessons.basetypes;

/**
 * Реализовать класс BiDirList - двунаправленный связный список
 *
 * Методы
 *
 * 1.1 public void addLast(T item) - добавить в конец списка
 *
 * 1.2 public void addFirst(T item)- добавить в начало списка
 *
 * 1.3 public void remove(T item) - удалить
 *
 * 1.4 public T at(int i) - получить элемент по индексу
 *
 * 1.5 public int size() - получить количество элементов
 *
 * 1.6 public static<T> BiDirList<T> from(T[] array) - конструктор из массива
 *
 * 1.7 public static<T> BiDirList<T> of(T...array) -  конструктор из массива
 *
 * 1.8 public static void toArray(T[] array) - скопировать в массив
 *
 * 1.9 public Iterator<BiDirList<T>> getIterator() - получить итератор
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BiDirList<T extends Comparable> implements Iterable<T> {
    private int quan;
    private ListItem<T> first;
    private ListItem<T> last;

    BiDirList() {
        quan = 0;
        first = null;
        last = null;
    }

    BiDirList(T data) {
        first = last = new ListItem<T>(data);
        quan = 1;
    }

    public static<T extends Comparable> BiDirList<T> from(T[] array){
        BiDirList<T> list = new BiDirList<>();
        for (T element : array)
            list.addLast(element);
        return list;
    }

    public static<T extends Comparable> BiDirList<T> of(T...array){
        BiDirList<T> list = new BiDirList<>();
        for (int i = 0; i < array.length ; i++)
            list.addLast(array[i]);
        return list;
    }

    public void toArray(T[] array){
        for (int i = 0; i < array.length ; i++) {
            array[i++] = this.at(i);
        }
    }

    public void addLast(T data) {
        ListItem<T> li = new ListItem<T>(data);
        if (first == null) {
            first = li;
            last = li;
        } else {
            last.setNext(li);
            li.setPrev(last);
            last = li;
        }
        quan++;
    }

    public void addFirst(T data) {
        ListItem<T> li = new ListItem<T>(data);
        if (first == null) {
            first = li;
            last = li;
        } else {
            first.setPrev(li);
            li.setNext(first);
            first = li;
        }
        quan++;
    }

    public ListItem<T> get(T item) {
        if (item.compareTo(quan)==1) {
            return null;
        }
        else {
            ListItem<T> li = first;
            for (int j=1; item.compareTo(j)==1; j++) {
                li = li.getNext();
            }
            return li;
        }
    }

    public T at(int i){
        if (i>quan) {
            return null;
        }
        else {
            ListItem<T> li = first;
            for (int j=1; j<i; j++) {
                li = li.getNext();
            }
            return (T)li;
        }
    }

    public void remove(T item) {
        if (item.compareTo(quan) ==-1 || item.compareTo(quan) ==0) {
            ListItem<T> li = get(item);
            ListItem<T> prev = li.getPrev();
            ListItem<T> next = li.getNext();
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
                if (item.compareTo(1) ==0) {
                    first = next;
                }
            }
            quan--;
        }
    }

    public int getSize() {
        return quan;
    }

    public ListItem<T> getFirst() {
        return first;
    }

    public ListItem<T> getLast() {
        return last;
    }

    public Iterator<BiDirList<T>> getIterator(){
        return new IteratorNew(first);
    }

    class IteratorNew<T> implements Iterator<T> {
        ListItem<T> elem;
        IteratorNew(ListItem<T> elem){
            this.elem = elem;
        }
        public boolean hasNext() {
            if (first == null)
                return false;
            if (elem == null)
                return false;
            return true;
        }
        public T next() {
            if (first == null)
                throw new NoSuchElementException();
            T rezult = elem.data;
            elem = elem.getNext();
            return rezult;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorNew<>(first);
    }

}

