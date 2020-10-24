package Vasilyev.Ch10;

// Класс с полем и методами:
class MyClass1{
    private int number;
    MyClass1(int n){
        number=n;
    }
    void set(int n){
        number=n;
    }
    int get(){
        return number;
    }
}
// Первый функциональный интерфейс:
interface MyGetter1{
    int myget(MyClass1 obj);
}
// Второй функциональный интерфейс:
interface MySetter1{
    void myset(MyClass1 obj,int n);
}
// Главный класс:
class MethReferenceDemo{
    public static void main(String[] args){
        // Создается объект:
        MyClass1 obj=new MyClass1(100);
        System.out.println("Создан объект с полем 100");
        // Используем ссылки на методы:
        MyGetter1 A=MyClass1::get;
        MySetter1 B=MyClass1::set;
        System.out.println("Переменная A: "+A.myget(obj));
        System.out.println("Переменная obj: "+obj.get());
        obj.set(200);
        System.out.println("Полю присвоено значение 200");
        System.out.println("Переменная A: "+A.myget(obj));
        B.myset(obj,300);
        System.out.println("Выполнена команда B.myset(obj,300)");
        System.out.println("Переменная A: "+A.myget(obj));
        System.out.println("Переменная obj: "+obj.get());
        // Создается новый объект:
        obj=new MyClass1(400);
        System.out.println("Создан объект с полем 400");
        System.out.println("Переменная A: "+A.myget(obj));
        System.out.println("Переменная obj: "+obj.get());
        B.myset(obj,500);
        System.out.println("Выполнена команда B.myset(obj,500)");
        System.out.println("Переменная A: "+A.myget(obj));
        System.out.println("Переменная obj: "+obj.get());
    }
}
