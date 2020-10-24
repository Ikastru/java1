package Vasilyev.Ch10;

// Функциональный интерфейс:
@FunctionalInterface
interface MyNums{
    // Метод с кодом по умолчанию:
    default void show(int n){
        System.out.println("Аргумент: "+n);
        System.out.println("Результат: "+get(n));
    }
    // Абстрактный метод:
    int get(int n);
}
// Главный класс:
class UsingLambdaDemo{
    // Главный метод:
    public static void main(String[] args){
        // Присваивание лямбда-выражения значением
        // переменной интерфейсного типа:
        MyNums A=(int n)->{
            // Локальные переменные:
            int k,s=0;
            // Вычисление суммы натуральных чисел:
            for(k=1;k<=n;k++){
                s+=k;
            }
            // Результат:
            return s;
        };
        System.out.println("Используем лямбда-выражение:");
        // Вызов методов из интерфейсной переменной:
        A.show(10);
        System.out.println("Проверка: "+A.get(10));
        // Альтернативная ссылка на объект:
        MyNums B=A;
        System.out.println("Новое лямбда-выражение:");
        // Интерфейсной переменой значением присваивается
        // новое лямбда-выражение:
        A=n->n*n;
        System.out.println("Вызов метода show() из A:");
        A.show(10);
        System.out.println("Вызов метода show() из B:");
        B.show(10);
    }
}