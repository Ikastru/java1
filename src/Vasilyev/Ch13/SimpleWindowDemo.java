package Vasilyev.Ch13;

// Импорт класса JFrame:
import javax.swing.JFrame;

// Подкласс создан наследованием суперкласса JFrame:
class MyFrame extends JFrame{
    // Конструктор:
    MyFrame(String name){
        // Вызов конструктора суперкласса:
        super(name);
        // Положение и размеры окна:
        setBounds(250,250,300,200);
        // Окно постоянных размеров:
        setResizable(false);
        // Реакция на щелчок системной пиктограммы:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Отображение окна на экране:
        setVisible(true);
    }
}
// Главный класс:
class SimpleWindowDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание анонимного объекта подкласса:
        new MyFrame("Обычное окно");
    }
}