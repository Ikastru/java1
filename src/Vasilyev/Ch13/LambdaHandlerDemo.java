package Vasilyev.Ch13;

// Импорт классов:
import javax.swing.*;
import java.awt.*;

// Класс для создания окна:
class MyFrame4 extends JFrame{
    // Конструктор:
    MyFrame4(String name){
        // Вызов конструктора суперкласса:
        super(name);
        // Положение и размеры окна:
        setBounds(250,250,300,200);
        // Окно постоянных размеров:
        setResizable(false);
        // Реакция на щелчок системной пиктограммы:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Отключение менеджера компоновки:
        setLayout(null);
        // Объект изображения (для отображения в метке):
        ImageIcon img=new ImageIcon("C:\\Apps\\giraffe.png");
        // Текстовое значение для отображения в метке:
        String txt="<html>Это жираф.<br>Он большой.<br>Он все видит.</html>";
        // Создание объекта метки:
        JLabel lbl=new JLabel(txt,img,JLabel.LEFT);
        // Создание объекта шрифта:
        Font F=new Font(Font.MONOSPACED,Font.BOLD,16);
        // Применение шрифта к метке:
        lbl.setFont(F);
        // Положение и размеры метки:
        lbl.setBounds(10,30,280,80);
        // Переход в режим непрозрачности метки:
        lbl.setOpaque(true);
        // Светло-серый цвет для фона метки:
        lbl.setBackground(Color.LIGHT_GRAY);
        // Рамка вокруг метки:
        lbl.setBorder(BorderFactory.createEtchedBorder());
        // Добавление метки в окно:
        add(lbl);
        // Создание объекта кнопки:
        JButton btn=new JButton("Закрыть окно");
        // Положение и размеры кнопки:
        btn.setBounds(50,120,200,30);
        // Отменяется режим отображения рамки фокуса:
        btn.setFocusPainted(false);
        // Регистрация в кнопке обработчика
        // на основе лямбда-выражения:
        btn.addActionListener(e->System.exit(0));
        // Добавление кнопки в окно:
        add(btn);
        // Отображение окна на экране:
        setVisible(true);
    }
}
// Главный класс:
class LambdaHandlerDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание объекта окна:
        new MyFrame4("Лямбда-выражение");
    }
}
