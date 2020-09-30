package Vasilyev.Ch12;

// Импорт класса Random:
import java.util.Random;
// Подкласс MyThread2 создается наследованием
// суперкласса Thread:

class MyThread2 extends Thread{
    // Количество сообщений:
    private int count;
    // Конструктор:
    MyThread2(String name,int count){
        // Вызов конструктора суперкласса:
        super(name);
        // Значение целочисленного поля:
        this.count=count;
        // Запуск потока на выполнение:
        start();
    }
    // Переопределение метода run():
    public void run(){
        // Отображение сообщения о запуске потока:
        System.out.println("Выполняется поток "+getName());
        // Создание объекта класса Random
        // для генерирования случайных чисел:
        Random rnd=new Random();
        // Оператор цикла, в котором выводятся сообщения:
        for(int k=1;k<=count;k++){
            System.out.println("Сообщение от потока "+getName()+":\t"+getName().charAt(0)+k);
            try{
                // Задержка в выполнении потока:
                Thread.sleep(1000+rnd.nextInt(2001));
            }
            catch(InterruptedException e){
                System.out.println("Прерывание потока "+getName());
            }
        }
        // Сообщение о завершении выполнения потока:
        System.out.println("Поток "+getName()+" завершен");
    }
}

// Главный класс:
class MultiThreadDemo{
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Начинает выполняться главный поток");
        // Создание объектов - запуск потоков:
        MyThread2 A=new MyThread2("ALPHA",5);
        MyThread2 B=new MyThread2("BRAVO",3);
        MyThread2 C=new MyThread2("CHARLIE",7);
        // Сообщения главного потока:
        for(int k=1;k<=4;k++){
            System.out.println("Сообщение от главного потока:\t"+k);
            // Задержка в выполнении потока:
            Thread.sleep(2000);
        }
        // Ожидание завершения дочерних потоков:
        if(A.isAlive()){
            A.join();
        }
        if(B.isAlive()){
            B.join();
        }
        if(C.isAlive()){
            C.join();
        }
        // Сообщение о завершении главного потока:
        System.out.println("Главный поток завершен");
    }
}
