package Vasilyev.Ch12;

// Класс с целочисленным полем:
class MyNumber {
    int number;
}

// Класс для создания потоков:
class MyThread3 extends Thread {
    // Ссылка на объект с целочисленным полем:
    private MyNumber obj;
    // Интервал приостановки в выполнении потока:
    private int time;
    // Количество циклов:
    private int count;
    // Поле логического типа:
    private boolean state;

    // Конструктор:
    MyThread3(String name, MyNumber obj, int time, int count, boolean state) {
        // Вызов конструктора суперкласса:
        super(name);
        // Ссылка на объект:
        this.obj = obj;
        // Интервал задержки:
        this.time = time;
        // Количество циклов:
        this.count = count;
        // Значение поля логического типа:
        this.state = state;
        // Запуск потока:
        start();
    }

    // Метод для отображения "линии" из символов:
    private void line() {
        // Локальная символьная переменная:
        char s;
        // Значение переменной:
        if (state) s = '-';
        else s = '*';
        // Отображение последовательности символов:
        for (int k = 1; k <= 35; k++) {
            System.out.print(s);
        }
        System.out.println("");
    }

    // Переопределение метода run():
    public void run() {
        // Оператор цикла:
        for (int k = 1; k <= count; k++) {
            // Блок синхронизации:
            synchronized (obj) {
                // Отображается "линия":
                line();
                // Отображение сообщения:
                System.out.println("Поток " + getName() + ": исходное значение " + obj.number);
                // Контролируемый код:
                try {
                    // Задержка в выполнении потока:
                    Thread.sleep(time);
                } // Обработка исключения:
                catch (InterruptedException e) {
                    System.out.println(e);
                }
                // Изменение значения поля объекта:
                if (state) obj.number++;
                else obj.number--;
                // Отображение сообщения:
                System.out.println("Поток " + getName() + ": новое значение " + obj.number);
                // Отображение "линии":
                line();
            } // Завершение блока синхронизации
        }
    }
}

// Главный класс:
class SynchronizedThreadsDemo {
    // Главный метод:
    public static void main(String[] args) {
        // Целочисленные переменные:
        int n = 100, count = 5, time = 1000, dt = 200;
        // Создание объекта с целочисленным полем:
        MyNumber obj = new MyNumber();
        // Присваивание полю объекта значения:
        obj.number = n;
        // Создание первого потока:
        MyThread3 Alpha = new MyThread3("Alpha", obj, time + dt, count, true);
        // Создание второго потока:
        MyThread3 Bravo = new MyThread3("Bravo", obj, time - dt, count, false);
        // Контролируемый код:
        try {
            // Ожидание завершения потоков:
            if (Alpha.isAlive()) Alpha.join();
            if (Bravo.isAlive()) Bravo.join();
        } // Обработка исключения:
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}