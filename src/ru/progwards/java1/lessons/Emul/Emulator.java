package ru.progwards.java1.lessons.Emul;

/**
 * Эмулятор RAM машины
 */

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Emulator {
    //Имя входного файла
    private static String FILE_NAME;
    //Список, содержащий считанный текст программы
    List<String> lines;
    //Список, содержащий полный текст программы по лексемам
    List<String> allProgram = new ArrayList<>();
    Map<Integer, String> allMap = new HashMap<>();
    //Строка для сбора комментариев
    StringBuilder stringBuilder = new StringBuilder();
    //Счётчик цикла ассемблера
    private int counter;
    //Данные из входной ленты (первые два элемента всегда равны 0 для совпадения индексов со списком команд)
    private int[] arrIn = new int[100];
    //С этой позиции считывается число из входной ленты в операнд
    public static int countLoad = 0;
    //Рагистр выполнения, register.get(0) это и есть r0 - сумматор
    private int[] register = new int[100];
    //Выходная лента
    List<Integer> listOut = new ArrayList<>();


    public Emulator(String filename) {
        this.FILE_NAME = filename;
    }

    public void readIn(int x) {
        register[x] = (arrIn[countLoad]);
        countLoad++;
    }

    public void writeOut(int x) {
        listOut.add(register[x]);
    }

    public void load(int x) {
        register[0] = register[x];
    }

    public void loadNumber(int x) {
        register[0] = x;
    }

    public void store(int x) {
        register[x] = register[0];
    }

    public void add(int x) {
        register[0] += register[x];
    }

    public void addNumber(int x) {
        register[0] += x;
    }

    public void sub(int x) {
        register[0] = register[0] - register[x];
    }

    public void subNumber(int x) {
        register[0] = register[0] - x;
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

    public void halt() {
        try (FileWriter writer = new FileWriter("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\Result.txt", false)) {
            writer.write(String.valueOf(listOut));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Данные записаны в файл: C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\Result.txt");
        return;
    }

    //Проверить, содержит ли строка косвенный указатель
    public boolean isPoint(String strCheck) {
        boolean bool = false;
        char[] arrChar = strCheck.toCharArray();
        for (int k = 0; k < arrChar.length; k++) {
            if (arrChar[k] == '*') {
                bool = true;
            }
        }
        return bool;
    }

    //Проверить, содержит ли строка знак равно =
    public boolean isNumberMy(String strCheck) {
        boolean bool = false;
        char[] arrChar = strCheck.toCharArray();
        for (int k = 0; k < arrChar.length; k++) {
            if (arrChar[k] == '=') {
                bool = true;
            }
        }
        return bool;
    }

    //Проверить, является ли значение allMap числом
    public boolean isNumberMap(String strCheck) {
        if (strCheck == null || strCheck.isEmpty()) return false;
        for (int i = 0; i < strCheck.length(); i++) {
            if (!Character.isDigit(strCheck.charAt(i))) return false;
        }
        return true;
    }

    public void jgtz(String metka) {
        if (register[0] > 0) {
//            loop((Integer) getKeyFromValue(allMap,metka+":"), (Integer) getKeyFromValue(allMap,metka));
            for (int i = (Integer) getKeyFromValue(allMap, metka + ":"); i < (Integer) getKeyFromValue(allMap, metka); i++) {
                switch (allProgram.get(i).trim()) {
                    case ("load"):
                        if (isPoint(allProgram.get(i + 1))) {
                            load(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else if (isNumberMy(allProgram.get(i + 1))) {
                            loadNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else {
                            load(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("read"):
                        if (isPoint(allProgram.get(i + 1))) {
                            readIn(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else {
                            readIn(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("store"):
                        if (isPoint(allProgram.get(i + 1))) {
                            store(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else {
                            store(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("write"):
                        if (isPoint(allProgram.get(i + 1))) {
                            writeOut(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                        } else {
                            writeOut(register[parseInt(allProgram.get(i + 1).trim())]);
                        }
                        break;
                    case ("add"):
                        if (isNumberMy(allProgram.get(i + 1))) {
                            addNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else {
                            add(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("sub"):
                        if (isNumberMy(allProgram.get(i + 1))) {
                            subNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else if (isPoint(allProgram.get(i + 1))) {
                            sub(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                        } else {
                            sub(register[parseInt(allProgram.get(i + 1).trim())]);
                        }
                        break;
                    case ("jgtz"):
                        jgtz(allProgram.get(i + 1));
                        break;
                    default:
                        break;
                }
            }
        }
//        else {
//            loop((Integer) getKeyFromValue(allMap,metka)+1, allProgram.size());
//        }
    }

    public void jz(String metka) {
        if (register[0] == 0) {
            for (int i = (Integer) getKeyFromValue(allMap, metka + ":"); i < (Integer) getKeyFromValue(allMap, metka); i++) {
                switch (allProgram.get(i).trim()) {
                    case ("load"):
                        if (isPoint(allProgram.get(i + 1))) {
                            load(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else if (isNumberMy(allProgram.get(i + 1))) {
                            loadNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else {
                            load(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("read"):
                        if (isPoint(allProgram.get(i + 1))) {
                            readIn(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else {
                            readIn(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("store"):
                        if (isPoint(allProgram.get(i + 1))) {
                            store(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                        } else {
                            store(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("write"):
                        if (isPoint(allProgram.get(i + 1))) {
                            writeOut(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                        } else {
                            writeOut(register[parseInt(allProgram.get(i + 1).trim())]);
                        }
                        break;
                    case ("add"):
                        if (isNumberMy(allProgram.get(i + 1))) {
                            addNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else {
                            add(parseInt(allProgram.get(i + 1).trim()));
                        }
                        break;
                    case ("sub"):
                        if (isNumberMy(allProgram.get(i + 1))) {
                            subNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                        } else if (isPoint(allProgram.get(i + 1))) {
                            sub(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                        } else {
                            sub(register[parseInt(allProgram.get(i + 1).trim())]);
                        }
                        break;
                    case ("jz"):
                        jz(allProgram.get(i + 1));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void jump(String metka) {
        for (int i = (Integer) getKeyFromValue(allMap, metka + ":"); i < (Integer) getKeyFromValue(allMap, metka); i++) {
            switch (allProgram.get(i).trim()) {
                case ("load"):
                    if (isPoint(allProgram.get(i + 1))) {
                        load(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else if (isNumberMy(allProgram.get(i + 1))) {
                        loadNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else {
                        load(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("read"):
                    if (isPoint(allProgram.get(i + 1))) {
                        readIn(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else {
                        readIn(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("store"):
                    if (isPoint(allProgram.get(i + 1))) {
                        store(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else {
                        store(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("write"):
                    if (isPoint(allProgram.get(i + 1))) {
                        writeOut(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                    } else {
                        writeOut(register[parseInt(allProgram.get(i + 1).trim())]);
                    }
                    break;
                case ("add"):
                    if (isNumberMy(allProgram.get(i + 1))) {
                        addNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else {
                        add(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("sub"):
                    if (isNumberMy(allProgram.get(i + 1))) {
                        subNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else if (isPoint(allProgram.get(i + 1))) {
                        sub(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                    } else {
                        sub(register[parseInt(allProgram.get(i + 1).trim())]);
                    }
                    break;
                case ("jgtz"):
                    jgtz(allProgram.get(i + 1));
                    break;
                case ("jz"):
                    jz(allProgram.get(i + 1));
                    break;
                default:
                    break;
            }
        }
    }

    //Непосредственное выполнение программы
    public void loop(int m, int end) {
        for (int i = m; i < end; i++) {
            switch (allProgram.get(i).trim()) {
                case ("load"):
                    if (isPoint(allProgram.get(i + 1))) {
                        load(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else if (isNumberMy(allProgram.get(i + 1))) {
                        loadNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else {
                        load(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("read"):
                    if (isPoint(allProgram.get(i + 1))) {
                        readIn(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else {
                        readIn(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("store"):
                    if (isPoint(allProgram.get(i + 1))) {
                        store(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else {
                        store(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("write"):
                    if (isPoint(allProgram.get(i + 1))) {
                        writeOut(parseInt(allProgram.get(i + 1).replace('*', ' ').trim()));
                    } else {
                        writeOut(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("add"):
                    if (isNumberMy(allProgram.get(i + 1))) {
                        addNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else {
                        add(parseInt(allProgram.get(i + 1).trim()));
                    }
                    break;
                case ("sub"):
                    if (isNumberMy(allProgram.get(i + 1))) {
                        subNumber(parseInt(allProgram.get(i + 1).replace('=', ' ').trim()));
                    } else if (isPoint(allProgram.get(i + 1))) {
                        sub(register[parseInt(allProgram.get(i + 1).replace('*', ' ').trim())]);
                    } else {
                        sub(register[parseInt(allProgram.get(i + 1).trim())]);
                    }
                    break;
                case ("jgtz"):
                    jgtz(allProgram.get(i + 1));
                    break;
                case ("jz"):
                    jz(allProgram.get(i + 1));
                    break;
                case ("jump"):
                    jump(allProgram.get(i + 1));
                    break;
                case ("halt"):
                    halt();
                default:
                    break;
            }
        }

    }

    public void calculate() {
        try {
            lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
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
//            System.out.println(allMap);
        if (allProgram.get(0).equals("\uFEFF<input>") || allProgram.get(0).equals("\uFEFF;") || allProgram.get(0).equals("<input>") || allProgram.get(0).equals(";")) {
            int begin = (Integer) getKeyFromValue(allMap, "\uFEFF<input>");
            counter = parseInt(allProgram.get(begin+1).trim());
            int j = 1;
            while (isNumberMap(allMap.get(j))) {
                arrIn[j - 1] = parseInt(allProgram.get(j).trim());
                j++;
            }
            loop(j, allProgram.size());
        } else {
            System.out.println("Неправильный ввод программы");
            return;
        }
    }


    public static void main(String[] args) {
        Emulator emulator = new Emulator("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\Ex2Mult.txt");
        emulator.calculate();
    }
}


