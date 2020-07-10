package ru.progwards.java1.lessons.Emul;

/**
 * Эмулятор RAM машины с ООП
 *
 * Формат начального ввода в файле: <input> { целые числа}
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class EmulatorOOP {
    //Имя входного файла
    public static String FILE_NAME;
    //Имя выходного файла
    public static String FILE_OUT;
    //Список, содержащий считанный текст программы
    List<String> lines;
    //Список и хэшМэп, содержащий полный текст программы по лексемам
    List<String> allProgram = new ArrayList<>();
    Map<Integer, String> allMap = new HashMap<>();
    //Счётчик цикла ассемблера (чтобы заполнить входную ленту чилами)
    private int counter;
    //Данные из входной ленты
    Map<Integer, Integer> arrInList = new HashMap();

    public EmulatorOOP(String filename) {
        this.FILE_NAME = filename;
    }

    //Найти ключ по значению в Map
    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    //Проверить, является ли значение allMap числом
    public boolean isNumberMap(String strCheck) {
        if (strCheck == null || strCheck.isEmpty()) return false;
        for (int i = 0; i < strCheck.length(); i++) {
            if (!Character.isDigit(strCheck.charAt(i))) return false;
        }
        return true;
    }

    public void calculate() {
        try {
            lines = Files.readAllLines(Paths.get(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pat = Pattern.compile(" ");
        //Список всех команд по порядку
        for (String line : lines) {
            String strs[] = pat.split(line);
            for (int i = 0; i < strs.length; i++) {
                allProgram.add(strs[i].toLowerCase());
            }
        }
        for (int i = 0; i < allProgram.size(); i++) {
            allMap.put(i, allProgram.get(i));
        }
        if (allProgram.get(0).equals("\uFEFF<input>") || allProgram.get(0).equals("\uFEFF;") || allProgram.get(0).equals("<input>") || allProgram.get(0).equals(";")) {
            int begin = (Integer) getKeyFromValue(allMap, "\uFEFF<input>");
            counter = parseInt(allProgram.get(begin + 1).trim());
            int j = 1;
            Operator.countCommand = 2;
            while (isNumberMap(allMap.get(j))) {
                arrInList.put(j - 1, parseInt(allProgram.get(j).trim()));
                j++;
                Operator.countCommand++;
            }
            Operator operator = new Operator(allMap, arrInList);
            operator.run();
        } else {
            System.out.println("Неправильный ввод программы");
            return;
        }
    }

    public static void main(String[] args) {
        String inFileName = "C:\\Users\\user\\IdeaProjects\\MyProject\\Ex2Mult.txt";
        EmulatorOOP emulatorOOP = new EmulatorOOP(inFileName);
        emulatorOOP.calculate();
    }
}

