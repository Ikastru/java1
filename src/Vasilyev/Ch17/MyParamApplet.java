package Vasilyev.Ch17;

// Импорт классов:
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс апплета:
public class MyParamApplet extends JApplet{
    // Поле - ссылка на метку:
    private JLabel L;
    // Поля для определения размера шрифта (определяются
    // на основе параметра, переданного апплету):
    private int small,big;
    // Название для шрифта:
    private String name="Arial";
    // Шрифты для метки:
    private Font onFnt,offFnt;
    // Цвет для текста метки:
    private Color onFgr=Color.RED;
    // Значение поля определяется на основе параметра,
    // переданного апплету:
    private Color offFgr;
    // Цвет для фона метки:
    private Color onBgr=Color.WHITE;
    private Color offBgr=Color.YELLOW;
    // Текст для метки:
    private String onTxt="Красный текст на белом фоне";
    // Значения полей определяются на основе параметра,
    // переданного апплету:
    private String offTxt,clr;
    // Метод для определения параметров метки:
    private void setAll(Color fgr,Color bgr,Font fnt,String txt){
        // Текст для метки:
        L.setText(txt);
        // Цвет текста для метки:
        L.setForeground(fgr);
        // Цвет фона для метки:
        L.setBackground(bgr);
        // Применение шрифта для метки:
        L.setFont(fnt);
    }
    // Метод инициализации апплета:
    public void init(){
        // Контролируемый код. Считывание параметра апплета:
        try{
            // Значение параметра:
            clr=getParameter("color");
            // Проверка значения:
            if(clr.equalsIgnoreCase("розовый")){
                offFgr=Color.PINK;
            }
            else{
                if(clr.equalsIgnoreCase("зеленый")){
                    offFgr=Color.GREEN;
                }
                else{
                    if(clr.equalsIgnoreCase("черный")){
                        offFgr=Color.BLACK;
                    }
                    else{
                        clr="Синий";
                        offFgr=Color.BLUE;
                    }
                }
            }
        }
        // Обработка исключения:
        catch(Exception e){
            clr="Синий";
            offFgr=Color.BLUE;
        }
        // Контролируемый код. Считывание параметра апплета:
        try{
            // Размер шрифта:
            small=Integer.parseInt(getParameter("fontsize"));
        }
        // Обработка исключения:
        catch(Exception e){
            small=20;
        }
        // Увеличенный размер шрифта:
        big=small+10;
        // Определение шрифтов:
        onFnt=new Font(name,Font.ITALIC|Font.BOLD,small);
        offFnt=new Font(name,Font.BOLD,small);
        // Определение текста для метки:
        offTxt=clr+" текст на желтом фоне";
        // Отключение менеджера компоновки:
        setLayout(null);
        // Создание метки:
        L=new JLabel();
        // Выравнивание текста по центру:
        L.setHorizontalAlignment(JLabel.CENTER);
        // Положение и размеры метки:
        L.setBounds(30,30,getWidth()-60,getHeight()-60);
        // Применение рамки вокруг метки:
        L.setBorder(BorderFactory.createEtchedBorder());
        // Переход в режим непрозрачности для метки:
        L.setOpaque(true);
        // Определение параметров метки:
        setAll(offFgr,offBgr,offFnt,offTxt);
        // Регистрация обработчика событий
        // класса ComponentEvent в апплете:
        addComponentListener(new ComponentAdapter(){
            // Метод вызывается при изменении
            // размеров апплета:
            public void componentResized(ComponentEvent e){
                // Размеры метки:
                L.setSize(getWidth()-60,getHeight()-60);
            }
        });
        // Регистрация обработчика событий
        // класса MouseEvent в апплете:
        addMouseListener(new MouseAdapter(){
            // Метод вызывается при нажатии кнопки мыши:
            public void mousePressed(MouseEvent e){
                // Применение шрифта к метке:
                L.setFont(new Font(name,L.getFont().getStyle(),big));
            }
            // Метод вызывается при отпускании кнопки мыши:
            public void mouseReleased(MouseEvent e){
                // Применение шрифта к метке:
                L.setFont(new Font(name,L.getFont().getStyle(),small));
            }
        });
        // Регистрация обработчика событий
        // класса MouseEvent в метке:
        L.addMouseListener(new MouseAdapter(){
            // Метод вызывается при наведении курсора
            // мыши на метку:
            public void mouseEntered(MouseEvent e){
                // Параметры метки:
                setAll(onFgr,onBgr,onFnt,onTxt);
            }
            // Метод вызывается при "уходе" курсора
            // мыши из области метки:
            public void mouseExited(MouseEvent e){
                // Параметры метки:
                setAll(offFgr,offBgr,offFnt,offTxt);
            }
        });
        // Регистрация еще одного обработчика событий
        // класса MouseEvent в метке:
        L.addMouseListener(getMouseListeners()[0]);
        // Добавление метки в апплет:
        add(L);
    }
}
