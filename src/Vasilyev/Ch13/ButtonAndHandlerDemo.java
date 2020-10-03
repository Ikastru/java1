package Vasilyev.Ch13;

// Импорт классов:
import javax.swing.*;
import java.awt.event.*;

// Класс кнопки:
class MyButton extends JButton implements ActionListener{
    // Описание метода из интерфейса ActionPerformed:
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
    // Конструктор:
    MyButton(int x,int y,int w,int h){
        // Вызов конструктора суперкласса:
        super("Закрыть окно");
        // Положение и размеры кнопки:
        setBounds(x,y,w,h);
        // Отмена отображения рамки фокуса:
        setFocusPainted(false);
        // Регистрация обработчика в кнопке:
        addActionListener(this);
    }
}

// Класс панели:
class MyPanel extends JPanel{
    // Конструктор:
    MyPanel(String txt,ImageIcon img){
        // Вызов конструктора суперкласса:
        super();
        // Положение и размеры панели:
        setBounds(5,5,285,110);
        // Рамка вокруг панели:
        setBorder(BorderFactory.createEtchedBorder());
        // Отключение менеджера компоновки:
        setLayout(null);
        // Создание объекта для метки с изображением:
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
        // Добавление меток на панель:
        add(txtLbl);
        add(imgLbl);
    }
}

// Класс для окна:
class MyFrame6 extends JFrame{
    // Конструктор:
    MyFrame6(String name,String txt,ImageIcon img){
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
        MyPanel pnl=new MyPanel(txt,img);
        // Создание объекта кнопки:
        MyButton btn=new MyButton(50,120,200,30);
        // Добавление панели в окно:
        add(pnl);
        // Добавление кнопки в окно:
        add(btn);
        // Отображение окна на экране:
        setVisible(true);
    }
}
// Главный класс:
class ButtonAndHandlerDemo{
    // Главный метод:
    public static void main(String[] args){
        // Объект для изображения:
        ImageIcon img=new ImageIcon("C:\\Apps\\giraffe.png");
        // Текстовое значение:
        String txt="<html>Это жираф.<br>Он большой.<br>Он все видит.</html>";
        // Создание объекта окна:
        new MyFrame6("Обработчик - объект кнопки",txt,img);
    }
}