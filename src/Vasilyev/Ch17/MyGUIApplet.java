package Vasilyev.Ch17;

// Импорт классов:
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс апплета:
public class MyGUIApplet extends JApplet{
    // Панель меню:
    private JMenuBar mb;
    // Пункты меню:
    private JMenu font,color;
    // Опционные команды меню:
    private JCheckBoxMenuItem mItalic,mBold;
    // Команды меню - переключатели:
    private JRadioButtonMenuItem mRed,mBlack,mBlue;
    // Команда меню:
    private JMenuItem mReset;
    // Метка:
    private JLabel text;
    // Опции:
    private JCheckBox italic,bold;
    // Кнопка:
    private JButton reset;
    // Переключатели:
    private JRadioButton red,black,blue;
    // Метод для определения текста и шрифта для метки:
    private void setAll(){
        // Цвет:
        Color clr;
        // Текст для метки:
        String txt=" текст на сером фоне";
        // Стиль (начальное значение):
        int style=Font.PLAIN;
        // Определение стиля:
        if(italic.isSelected()){
            style|=Font.ITALIC; // Курсив
        }
        if(bold.isSelected()){
            style|=Font.BOLD; // Жирный шрифт
        }
        // Уточнение текста и определение цвета:
        if(red.isSelected()){
            txt="Красный"+txt;
            clr=Color.RED;
        }
        else{
            if(black.isSelected()){
                txt="Черный"+txt;
                clr=Color.BLACK;
            }
            else{
                txt="Синий"+txt;
                clr=Color.BLUE;
            }
        }
        // Текст для метки:
        text.setText(txt);
        // Шрифт для метки:
        text.setFont(new Font("Arial",style,20));
        // Цвет текста метки:
        text.setForeground(clr);
    }
    // Метод инициализации апплета:
    public void init(){
        // Переменные:
        int w,h;
        // Ширина и высота апплета:
        w=getWidth();
        h=getHeight();
        // Отключение менеджера компоновки:
        setLayout(null);
        // Создание панели:
        JPanel pnl=new JPanel();
        // Положение и размеры панели:
        pnl.setBounds(5,5,w-10,h-35);
        // Рамка для панели:
        pnl.setBorder(BorderFactory.createEtchedBorder());
        // Отключение менеджера компоновки панели:
        pnl.setLayout(null);
        // Создание метки:
        text=new JLabel();
        // Режим выравнивания текста по центру:
        text.setHorizontalAlignment(JLabel.CENTER);
        // Положение и размеры метки:
        text.setBounds(5,5,pnl.getWidth()-10,pnl.getHeight()/3);
        // Рамка вокруг метки:
        text.setBorder(BorderFactory.createEtchedBorder());
        // Добавление метки на панель:
        pnl.add(text);
        // Создание опций:
        italic=new JCheckBox("Курсивный шрифт");
        bold=new JCheckBox("Жирный шрифт");
        // Создание кнопки:
        reset=new JButton("Сброс");
        // Положение и размеры опций и кнопки:
        italic.setBounds(text.getX(),text.getY()+text.getHeight()+5,text.getWidth()/2-5,30);
        bold.setBounds(italic.getX(),italic.getY()+italic.getHeight()+5,italic.getWidth(),italic.getHeight());
        reset.setBounds(bold.getX(),bold.getY()+bold.getHeight()+5,bold.getWidth(),bold.getHeight());
        // Добавление опций и кнопки на панель:
        pnl.add(italic);
        pnl.add(bold);
        pnl.add(reset);
        // Создание группы переключателей:
        ButtonGroup bg=new ButtonGroup();
        // Создание переключателей:
        red=new JRadioButton("Красный",true);
        black=new JRadioButton("Черный",false);
        blue=new JRadioButton("Синий",false);
        // Добавление переключателей в группу:
        bg.add(red);
        bg.add(black);
        bg.add(blue);
        // Положение и размеры переключателей:
        red.setBounds(italic.getX()+italic.getWidth()+5,italic.getY(),italic.getWidth(),italic.getHeight());
        black.setBounds(red.getX(),bold.getY(),red.getWidth(),red.getHeight());
        blue.setBounds(black.getX(),reset.getY(),black.getWidth(),black.getHeight());
        // Добавление переключателей на панель:
        pnl.add(red);
        pnl.add(black);
        pnl.add(blue);
        // Создание панели меню:
        mb=new JMenuBar();
        // Создание пункта меню:
        font=new JMenu("Шрифт");
        // Создание опционных команд меню:
        mItalic=new JCheckBoxMenuItem("Курсив",false);
        mBold=new JCheckBoxMenuItem("Жирный",false);
        // Создание команды меню:
        mReset=new JMenuItem("Сброс");
        // Добавление опционных команд в пункт меню:
        font.add(mItalic);
        font.add(mBold);
        // Добавление разделителя:
        font.addSeparator();
        // Добавление команды в пункт меню:
        font.add(mReset);
        // Создание пункта меню:
        color=new JMenu("Цвет");
        // Создание группы переключателей:
        ButtonGroup mBG=new ButtonGroup();
        // Создание команд-переключателей:
        mRed=new JRadioButtonMenuItem("Красный",true);
        mBlack=new JRadioButtonMenuItem("Черный",false);
        mBlue=new JRadioButtonMenuItem("Синий",false);
        // Добавление команд-переключателей в группу:
        mBG.add(mRed);
        mBG.add(mBlack);
        mBG.add(mBlue);
        // Добавление команд-переключателей в пункт меню:
        color.add(mRed);
        color.add(mBlack);
        color.add(mBlue);
        // Добавление пунктов меню на панель меню:
        mb.add(font);
        mb.add(color);
        // Добавление панели меню в апплет:
        setJMenuBar(mb);
        // Применение текста и шрифта к метке:
        setAll();
        // Добавление панели в апплет:
        add(pnl);
        // Регистрация обработчиков
        // для событий класса ActionEvent:
        mItalic.addActionListener(e->italic.setSelected(mItalic.isSelected()));
        mBold.addActionListener(e->bold.setSelected(mBold.isSelected()));
        italic.addActionListener(e->mItalic.setSelected(italic.isSelected()));
        bold.addActionListener(e->mBold.setSelected(bold.isSelected()));
        mRed.addActionListener(e->red.setSelected(mRed.isSelected()));
        mBlack.addActionListener(e->black.setSelected(mBlack.isSelected()));
        mBlue.addActionListener(e->blue.setSelected(mBlue.isSelected()));
        red.addActionListener(e->mRed.setSelected(red.isSelected()));
        black.addActionListener(e->mBlack.setSelected(black.isSelected()));
        blue.addActionListener(e->mBlue.setSelected(blue.isSelected()));
        // Регистрация обработчиков
        // для событий класса ItemEvent:
        italic.addItemListener(e->setAll());
        bold.addItemListener(italic.getItemListeners()[0]);
        red.addItemListener(italic.getItemListeners()[0]);
        black.addItemListener(italic.getItemListeners()[0]);
        blue.addItemListener(italic.getItemListeners()[0]);
        // Обработчик для кнопки:
        reset.addActionListener(e->{
            italic.setSelected(true);
            // Программный "щелчок" на опции:
            italic.doClick();
            bold.setSelected(true);
            // Программный "щелчок" на опции:
            bold.doClick();
            // Программный "щелчок" на переключателе:
            red.doClick();
        });
        // Обработчик для команды меню:
        mReset.addActionListener(e->reset.doClick());
    }
}