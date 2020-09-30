package Vasilyev.Ch12;

class MainThreadDemo{
    public static void main(String[] args){
        // Объектная переменная для записи ссылки на поток:
        Thread t;
        // Получение ссылки на объект главного потока:
        t=Thread.currentThread();
        // Отображение информации о потоке:
        System.out.println(t);
        // Изменение имени потока:
        t.setName("Главный поток");
        // Задается приоритетность потока:
        t.setPriority(7);
        // Отображение информации о потоке:
        System.out.println(t);
    }
}
