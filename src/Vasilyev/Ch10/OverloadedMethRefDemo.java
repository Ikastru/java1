package Vasilyev.Ch10;

// Класс:
class MyClass4{
    // Поле:
    int number;
    // Перегруженный метод:
    void set(){
        number=0;
    }
    void set(int n){
        number=n;
    }
}
// Первый интерфейс:
interface Alpha4{
    void none();
}
// Второй интерфейс:
interface Bravo4{
    void one(int n);
}
// Главный класс:
class OverloadedMethRefDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание объекта:
        MyClass4 obj=new MyClass4();
        // Использование ссылки на перегруженный метод:
        Alpha4 A=obj::set;
        Bravo4 B=obj::set;
        // Вызов метода через интерфейсную переменную:
        B.one(100);
        // Проверка значения поля объекта:
        System.out.println("Значение поля: "+obj.number);
        // Вызов метода через интерфейсную переменную:
        A.none();
        // Проверка значения поля объекта:
        System.out.println("Значение поля: "+obj.number);
    }
}
