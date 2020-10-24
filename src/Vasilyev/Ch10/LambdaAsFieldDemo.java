package Vasilyev.Ch10;

// Функциональный интерфейс:
@FunctionalInterface
interface MyInterface5{
    // Метод с целочисленным аргументом возвращает
    // целочисленный результат:
    int getNumber(int n);
}
// Класс с полем интерфейсного типа:
class MyClass5{
    // Закрытое поле интерфейсного типа:
    private MyInterface5 ref;
    // Конструктор:
    MyClass5(MyInterface5 mi){
        set(mi);
    }
    // Метод для присваивания значения полю:
    void set(MyInterface5 mi){
        ref=mi;
    }
    // Метод с целочисленным аргументом возвращает
    // результатом целое число:
    int get(int n){
        // Вызов метода из объекта, на которое ссылается
        // поле интерфейсного типа:
        return ref.getNumber(n);
    }
}
// Главный класс:
class LambdaAsFieldDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание объекта класса с передачей аргументом
        // конструктору лямбда-выражения:
        MyClass5 obj=new MyClass5((int n)-> n*n);
        // Проверка результата:
        System.out.println("Аргумент:");
        for(int k=0;k<=5;k++){
            System.out.print(k+"\t");
        }
        System.out.println("\nАргумент в квадрате:");
        for(int k=0;k<=5;k++){
            System.out.print(obj.get(k)+"\t");
        }
        // Полю объекта присваивается новое значение:
        obj.set((int n)-> n*n*n);
        // Проверка результата:
        System.out.println("\nАргумент в кубе:");
        for(int k=0;k<=5;k++){
            System.out.print(obj.get(k)+"\t");
        }
        // Полю объекта присваивается новое значение:
        obj.set((int n)-> n*n*n*n);
        // Проверка результата:
        System.out.println("\nАргумент в четвёртой степени:");
        for(int k=0;k<=5;k++){
            System.out.print(obj.get(k)+"\t");
        }
        System.out.println();
    }
}
