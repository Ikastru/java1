package Vasilyev.Ch17;

// Импорт классов:
import javax.swing.*;
import java.awt.*;

// Класс апплета:
public class MyApplet extends JApplet{
    // Метод инициализации апплета:
    public void init(){
        // Создание метки:
        JLabel lbl=new JLabel("Синий текст на желтом фоне");
        // Применение шрифта к метке:
        lbl.setFont(new Font("Arial",Font.BOLD,30));
        // Цвет текста:
        lbl.setForeground(Color.BLUE);
        // Переход в режим непрозрачности:
        lbl.setOpaque(true);
        // Цвет фона метки:
        lbl.setBackground(Color.YELLOW);
        // Добавление метки в апплет:
        add(lbl);
    }
}
