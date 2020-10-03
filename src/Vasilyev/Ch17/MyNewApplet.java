package Vasilyev.Ch17;

// Импорт классов:
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс апплета реализует интерфейсы
// MouseListener и ComponentListener:
public class MyNewApplet extends JApplet implements MouseListener,ComponentListener{
    // Поле - ссылка на метку:
    private JLabel L;
    // Поля для определения размера шрифта:
    private int small=25,big=35;
    // Название для шрифта:
    private String name="Arial";
    // Шрифт для метки, если над областью метки
    // находится курсор мыши:
    private Font onFnt=new Font(name,Font.ITALIC|Font.BOLD,small);
    // Шрифт для метки, если курсор мыши
    // не находится над областью метки:
    private Font offFnt=new Font(name,Font.BOLD,small);
    // Цвет для текста метки, если курсор мыши
    // находится над областью метки:
    private Color onFgr=Color.RED;
    // Цвет для текста метки, если курсор мыши
    // не находится над областью метки:
    private Color offFgr=Color.BLUE;
    // Цвет для фона метки, если курсор мыши
    // находится над областью метки:
    private Color onBgr=Color.WHITE;
    // Цвет для фона метки, если курсор мыши
    // не находится над областью метки:
    private Color offBgr=Color.YELLOW;
    // Текст для метки, если курсор мыши
    // находится над областью метки:
    private String onTxt="Красный текст на белом фоне";
    // Текст для метки, если курсор мыши
    // не находится над областью метки:
    private String offTxt="Синий текст на желтом фоне";
    // Метод для определения шрифта, текста,
    // цвета текста и цвета фона для метки:
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
    // Метод вызывается при изменении размеров компонента
    // (в данном случае апплета):
    public void componentResized(ComponentEvent e){
        // Определение размеров метки:
        L.setSize(getWidth()-60,getHeight()-60);
    }
    // Неиспользуемые методы из интерфейса
    // ComponentListener с пустой реализацией:
    public void componentHidden(ComponentEvent e){}
    public void componentShown(ComponentEvent e){}
    public void componentMoved(ComponentEvent e){}
    // Метод вызывается при наведении курсора
    // мыши на апплет или метку в апплете:
    public void mouseEntered(MouseEvent e){
        // Если курсор наведен на метку:
        if(e.getSource()==L){
            // Присваивание текста, шрифта и цвета для
            // текста и фона метки:
            setAll(onFgr,onBgr,onFnt,onTxt);
        }
    }
    // Метод вызывается при "уходе" курсора
    // мыши из области апплета или метки в апплете:
    public void mouseExited(MouseEvent e){
        // Если курсор мыши "ушел" из области метки:
        if(e.getSource()==L){
            // Присваивание текста, шрифта и цвета для
            // текста и фона метки:
            setAll(offFgr,offBgr,offFnt,offTxt);
        }
    }
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
    // Неиспользуемый метод из интерфейса MouseListener
    // с пустой реализацией:
    public void mouseClicked(MouseEvent e){}
    // Метод инициализации апплета:
    public void init(){
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
        // Присваивание текста, шрифта и цвета для
        // текста и фона метки:
        setAll(offFgr,offBgr,offFnt,offTxt);
        // Регистрация обработчика событий
        // класса ComponentEvent в апплете:
        addComponentListener(this);
        // Регистрация обработчика событий
        // класса MouseEvent в апплете:
        addMouseListener(this);
        // Регистрация обработчика событий
        // класса MouseEvent в метке:
        L.addMouseListener(this);
        // Добавление метки в апплет:
        add(L);
    }
}
