package Vasilyev.Ch10;

// Класс:
class MyClass{
    // Закрытое целочисленное поле:
    private int number;
    // Конструктор:
    MyClass(int n){
        number=n;
    }
    // Метод для присваивания значения полю:
    void set(int n){
        number=n;
    }
    // Метод для считывания значения поля:
    int get(){
        return number;
    }
}
// Первый функциональный интерфейс:
interface MyGetter{
    int myget();
}
// Второй функциональный интерфейс:
interface MySetter{
    void myset(int n);
}
// Главный класс:
class ObjMethReferenceDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание объекта:
        MyClass obj=new MyClass(100);
        System.out.println("Создан объект с полем 100");
        // Используем ссылки на методы:
        MyGetter A=obj::get;
        MySetter B=obj::set;
        // Проверяем "значение поля" вызовом метода myget()
        // из интерфейсной переменной A:
        System.out.println("Переменная A: "+A.myget());
        // Проверяем значение поля объекта:
        System.out.println("Переменная obj: "+obj.get());
        // Полю объекта присваивается значение:
        obj.set(200);
        System.out.println("Полю присвоено значение 200");
        // Проверяем "значение поля" вызовом метода myget()
        // из интерфейсной переменной A:
        System.out.println("Переменная A: "+A.myget());
        // Присваиваем "значение полю" вызовом метода myset()
        // из интерфейсной переменной B:
        B.myset(300);
        System.out.println("Выполнена команда B.myset(300)");
        // Проверяем "значение поля" вызовом метода myget()
        // из интерфейсной переменной A:
        System.out.println("Переменная A: "+A.myget());
        // Проверяем значение поля объекта:
        System.out.println("Переменная obj: "+obj.get());
        // Создается новый объект:
        obj=new MyClass(400);
        System.out.println("Создан объект с полем 400");
        // Проверяем "значение поля" вызовом метода myget()
        // из интерфейсной переменной A:
        System.out.println("Переменная A: "+A.myget());
        // Проверяем значение поля объекта:
        System.out.println("Переменная obj: "+obj.get());
        // Присваиваем "значение полю" вызовом метода myset()
        // из интерфейсной переменной B:
        B.myset(500);
        System.out.println("Выполнена команда B.myset(500)");
        // Проверяем "значение поля" вызовом метода myget()
        // из интерфейсной переменной A:
        System.out.println("Переменная A: "+A.myget());
        // Проверяем значение поля объекта:
        System.out.println("Переменная obj: "+obj.get());
    }
}
