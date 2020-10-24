package Vasilyev.Ch10;

// Класс:
class MyClass3{
    // Закрытое поле:
    private int number;
    // Конструктор:
    MyClass3(int n){
        number=n;
    }
    // Открытые методы:
    void show(){
        System.out.println("Значение поля: "+number);
    }
    void set(int n){
        number=n;
    }
}
// Интерфейс:
interface MyInterface{
    MyClass3 create(int n);
}
// Главный класс:
class ConstructorReferenceDemo{
    // Главный метод:
    public static void main(String[] args){
        // Использование ссылки на конструктор:
        MyInterface ref=MyClass3::new;
        // Создание объекта вызовом метода
        // из интерфейсной переменной:
        MyClass3 obj=ref.create(100);
        // Вызов методов объекта:
        obj.show();
        obj.set(200);
        obj.show();
    }
}
