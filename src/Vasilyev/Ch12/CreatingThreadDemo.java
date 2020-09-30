package Vasilyev.Ch12;

// Класс реализует интерфейс Runnable:
class MyThread implements Runnable{
    // Описание метода run() (программный код для потока):
    public void run(){
        for(int i=1;i<=5;i++){
            // Отображение сообщения:
            System.out.println("Дочерний поток:\t"+i);
            try{
                // Задержка в выполнении потока:
                Thread.sleep(1200);
            } // Обработка исключения:
            catch(InterruptedException e){
                System.out.println("Прерывание дочернего потока");
            }
        }
    }
}
// Главный класс:
class CreatingThreadDemo{
    // Главный поток:
    public static void main(String[] args){
        System.out.println("Начинается выполнение программы");
        // Создание объекта класса Thread для запуска
        // дочернего потока на выполнение:
        Thread t=new Thread(new MyThread());
        System.out.println("Запускается дочерний поток");
        // Запуск дочернего потока:
        t.start();
        for(int k=0;k<=5;k++){
            // Отображение сообщения:
            System.out.println("Главный поток:\t"+(char)('A'+k));
            try{
                // Задержка в выполнении главного потока:
                Thread.sleep(2000);
            } // Обработка исключения:
            catch(InterruptedException e){
                System.out.println("Прерывание главного потока");
            }
        }
        System.out.println("Выполнение программы завершено");
    }
}