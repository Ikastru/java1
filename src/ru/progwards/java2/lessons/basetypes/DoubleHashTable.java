package ru.progwards.java2.lessons.basetypes;

/**
 * Реализовать класс DoubleHashTable - хэш таблица с двойным хэшированием
 * <p>
 * В качестве размера таблицы выбирать простое число, первоначальное значение 101
 * <p>
 * При количестве коллизий более 10% при одной серии пробирований - перестраивать таблицу, увеличивая размер
 * <p>
 * Стратегия роста - удвоение размера, но с учетом правила - размер таблицы простое число. Т.е. искать ближайшее простое
 * <p>
 * Ключи должны реализовывать интерфейс
 * interface HashValue {
 * int getHash();
 * <p>
 * }
 * <p>
 * Таким образом, мы унифицируем ключи любого типа, приведя их к целочисленному. Для строковых - выбрать что-то, из функций,
 * представленных в лекции, для целого числа можно вернуть просто само число.
 * <p>
 * Далее, когда реализуем 2 хэш функции, реализуем их для числовых значение, и тут взять - деление и умножение
 * <p>
 * Методы
 * <p>
 * 2.1 public void add(K key, V value) - добавить пару ключ-значение
 * <p>
 * 2.2 public V get(K key) - получить значение по ключу
 * <p>
 * 2.3 public void remove(K key) - удалить элемент по ключу
 * <p>
 * 2.4 public void change(K key1, Key key2) - изменить значение ключа у элемента с key1 на key2
 * <p>
 * 2.5 public int size() - получить количество элементов
 * <p>
 * 2.6 public Iterator<DoubleHashTable<K,V>> getIterator()
 */

import java.util.ArrayList;
import java.util.Iterator;

public class DoubleHashTable<K, V> {
    ArrayList<HashNode<K, V>> bucket = new ArrayList<>();
    int numBuckets = 101;
    int size;

    public DoubleHashTable() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getBucketIndex(K key) {
        int hashCod = key.hashCode();
        return hashCod % numBuckets;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            V val = head.value;
            head = head.next;
            bucket.set(index, head);
            size--;
            return val;
        } else {
            HashNode<K, V> prev = null;
            while (head != null) {

                if (head.key.equals(key)) {
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }
            size--;
            return null;
        }
    }

    public void add(K key, V value) {

        int index = getBucketIndex(key);
        System.out.println(index);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> toAdd = new HashNode<>();
        toAdd.key = key;
        toAdd.value = value;
        if (head == null) {
            bucket.set(index, toAdd);
            size++;
        } else {
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
            if (head == null) {
                head = bucket.get(index);
                toAdd.next = head;
                bucket.set(index, toAdd);
                size++;
            }
        }
        if ((1.0 * size) / numBuckets > 1.0) {
            //do something
            ArrayList<HashNode<K, V>> tmp = bucket;
            bucket = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            for (int i = 0; i < numBuckets; i++) {
                bucket.add(null);
            }
            for (HashNode<K, V> headNode : tmp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }

    }

    public void change(K key1, K key2){
        int index1 = getBucketIndex(key1);
        int index2 = getBucketIndex(key2);
        HashNode<K, V> head = bucket.get(index1);
        head.key = key2;
        bucket.set(index2, head);
    }

    class IteratorNew<K> implements Iterator<K>{
        HashNode<K,V> head;
        private int count;
        IteratorNew(HashNode<K,V> head){
            this.head = head;
            count = -1;
        }
        @Override
        public boolean hasNext() {
            if (count < size-1) {
                return true;
            }
            return false;
        }

        @Override
        public K next() {
            count++;
            try {
                return (K)bucket.get(count).key;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }
    }

    public Iterator<K> getIterator(){
        return new IteratorNew<>(bucket.get(0));
    }

    public static void main(String[] args) {
        DoubleHashTable<String, Integer> ht = new DoubleHashTable<>();
        ht.add("this", 1);
        System.out.println(ht.remove("this"));
        System.out.println(ht.remove("this"));

    }
}
