package Vasilyev.Ch13;

// Импорт классов:
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс для создания окна
// реализует интерфейс ActionListener:
class MyFrame5 extends JFrame implements ActionListener{
    // Описание метода из интерфейса ActionListener:
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
    // Конструктор:
    MyFrame5(String name){
        // Вызов конструктора суперкласса:
        super(name);
        // Положение и размеры окна:
        setBounds(250,250,300,200);
        // Окно постоянных размеров:
        setResizable(false);
        // Реакция на щелчок системной пиктограммы:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Отключение менеджера компоновки для окна:
        setLayout(null);
        // Создание объекта панели:
        JPanel pnl=new JPanel();
        // Положение и размеры панели:
        pnl.setBounds(5,5,285,110);
        // Рамка вокруг панели:
        pnl.setBorder(BorderFactory.createEtchedBorder());
        // Отключение менеджера компоновки для панели:
        pnl.setLayout(null);
        // Объект изображения (для отображения в метке):
        ImageIcon img=new ImageIcon("C:\\Apps\\giraffe.png");
        // Текстовое значение для отображения в метке:
        String txt="<html>Это жираф.<br>Он большой.<br>Он все видит.</html>";
        // Создание объекта метки с изображением:
        JLabel imgLbl=new JLabel(img);
        // Положение и размеры метки:
        imgLbl.setBounds(10,10,90,90);
        // Рамка вокруг метки:
        imgLbl.setBorder(BorderFactory.createEtchedBorder());
        // Создание объекта для метки с текстом:
        JLabel txtLbl=new JLabel(txt,JLabel.CENTER);
        // Положение и размеры метки:
        txtLbl.setBounds(110,10,165,90);
        // Рамка вокруг метки:
        txtLbl.setBorder(BorderFactory.createEtchedBorder());
        // Создание объекта кнопки:
        JButton btn=new JButton("Закрыть окно");
        // Положение и размеры кнопки:
        btn.setBounds(50,120,200,30);
        // Отменяется режим отображения рамки фокуса:
        btn.setFocusPainted(false);
        // Регистрация в кнопке обработчиком
        // объекта окна:
        btn.addActionListener(this);
        // Создание объекта шрифта:
        Font F=new Font(
                // Название шрифта как у кнопки:
                btn.getFont().getName(),
                // Стиль - жирный курсив:
                Font.BOLD|Font.ITALIC,
                // Размер шрифта на 2 больше чем у кнопки:
                btn.getFont().getSize()+2);
        // Применение шрифта к метке с текстом:
        txtLbl.setFont(F);
        // Добавление меток на панель:
        pnl.add(imgLbl);
        pnl.add(txtLbl);
        // Добавление панели в окно:
        add(pnl);
        // Добавление кнопки в окно:
        add(btn);
        // Отображение окна на экране:
        setVisible(true);
    }
}
// Главный класс:
class WindowAsHandlerDemo{
    // Главный метод:
    public static void main(String[] args){
        // Создание объекта окна:
        new MyFrame5("Обработчик - объект окна");
    }
}