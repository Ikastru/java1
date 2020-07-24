package ru.progwards.java2.lessons.generics;

/**
 * 1. Реализовать классы Fruit и потомки Apple, Orange;
 *
 * 2. Реализовать класс FruitBox, в который можно складывать фрукты как потомок ArrayList.
 *
 * 3. Обратить внимание на метод добавления фрукта в коробку - add.
 * Каждая коробка может содержать фрукты только одного типа, либо я блоки, либо апельсины.
 *
 * 4.Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта:
 * вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны); Количество считаем как количество элементов массива.
 *
 * 5. Реализовать метод moveTo, который позволяет пересыпать фрукты из текущей коробки в другую,
 * переданную в качестве параметра. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
 * Если у нас фрукты одного типа, то в текущей коробке фруктов не остается, а в другую перекидываются объекты,
 * которые были в первой; В противном случае выдать исключение UnsupportedOperationException.
 *
 *
 * 6. Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной в качестве параметра.
 * Вернуть: 1 - если масса текущей меньше массы переданной параметром, 0 - если их массы равны и
 * 1 в противоположном случае. Коробки с яблоками и апельсинами тоже можно сравнивать.
 */

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> extends ArrayList {
    public List<T> box = new ArrayList<>();

    public void add(T o) throws UnsupportedOperationException {
        if (box.isEmpty()) {
            box.add(o);
        } else {
            if (box.get(0).getClass() == o.getClass()) {
                box.add(o);
            } else
                throw new UnsupportedOperationException();
        }
    }

    public void moveTo(FruitBox o) throws UnsupportedOperationException {
        List<T> temp = new ArrayList<>();
        for (T fruit : box) {
            temp.add(fruit);
        }
        if (o.box.isEmpty()){
            o.box.addAll(temp);
        } else {
            if (temp.get(0).getClass() == o.box.get(0).getClass()) {
                o.box.addAll(temp);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }
//        o.box.addAll(this.box);
//        if (this.box.get(0).getClass() == o.box.get(0).getClass()) {
//            return;
//        } else{
//            throw new UnsupportedOperationException();
//    }


    public float getWeight() {
        if (box.isEmpty())
            return 0;
        else {
            int count = box.size();
            return box.get(0).getWeight() * count;
        }
    }

    public int сompareTo(FruitBox o) {
        return Float.compare(this.getWeight(), o.getWeight());
    }

    public static void main(String[] args) {
        FruitBox fruitBox1 = new FruitBox();
        FruitBox fruitBox2 = new FruitBox();
        FruitBox fruitBox3 = new FruitBox();
        fruitBox1.add(new Apple());
        System.out.println(fruitBox1.getWeight());
        fruitBox1.add(new Apple());
        System.out.println(fruitBox1.getWeight());
        fruitBox2.add(new Orange());
        fruitBox2.add(new Orange());
        fruitBox2.add(new Orange());
        System.out.println(fruitBox2.getWeight());
        fruitBox1.moveTo(fruitBox3);
        System.out.println(fruitBox3.getWeight());
    }
}

