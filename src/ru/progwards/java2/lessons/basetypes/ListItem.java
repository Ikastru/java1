package ru.progwards.java2.lessons.basetypes;

public class ListItem<T> {

    public T data;
    private ListItem<T> next;
    private ListItem<T> prev;

    ListItem(T d) {
        data = d;
        next = null;
        prev = null;
    }

    ListItem(T d, ListItem<T> p) {
        data = d;
        prev = p;
    }

    public T getData() {
        return data;
    }

    public void setdata(T d) {
        data = d;
    }

    public ListItem<T> getPrev() {
        return prev;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setPrev(ListItem<T> li) {
        prev = li;
    }

    public void setNext(ListItem<T> li) {
        next = li;
    }

}
