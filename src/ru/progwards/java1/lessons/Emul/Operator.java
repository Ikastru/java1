package ru.progwards.java1.lessons.Emul;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Operator {
    //Полный текст программы
    private Map<Integer, String> programText = new HashMap<>();
    public static int countCommand;
    //Рагистр выполнения, register.get(0) это и есть r0 - сумматор
    Map<Integer, Integer> registerWork = new HashMap<>();
    //С этой позиции считывается число из входной ленты в операнд
    public static int countLoad = 0;
    //Данные из входной ленты
    Map<Integer, Integer> lentaIn = new HashMap();
    //Выходная лента
    List<Integer> listOut = new ArrayList<>();

    public Operator(Map<Integer, String> programText, Map<Integer, Integer> lentaIn){
        this.programText.putAll(programText);
        this.lentaIn.putAll(lentaIn);
        registerWork.put(0,0);
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

    //Загрузить операнд в сумматор: load 3
    private void load(){
        if (isPoint(programText.get(countCommand+1).trim())) {
            registerWork.put(0, registerWork.get(registerWork.get(parseInt(programText.get(countCommand + 1).replace('*', ' ').trim()))));
        } else if (isNumberMy(programText.get(countCommand + 1))) {
            registerWork.put(0, parseInt(programText.get(countCommand + 1).replace('=', ' ').trim()));
        } else {
            registerWork.put(0, registerWork.get(parseInt(programText.get(countCommand + 1).trim())));
        }
        countCommand++;
    }

    //Загрузить данные из входной ленты в операнд: read 1
    private void read(){
        if (isPoint(programText.get(countCommand+1).trim())) {
            registerWork.put(parseInt(programText.get(countCommand + 1).replace('*', ' ').trim()), lentaIn.get(countLoad));
        } else {
            registerWork.put(parseInt(programText.get(countCommand+1).trim()), lentaIn.get(countLoad));
        }
        countLoad++;
        countCommand++;
    }

    //Сохранить сумматор (r0) в операнд: store 3
    private void store(){
        if (isPoint(programText.get(countCommand+1).trim())) {
            registerWork.put(registerWork.get(parseInt(programText.get(countCommand + 1).replace('*', ' ').trim())), registerWork.get(0));
        } else {
            registerWork.put(parseInt(programText.get(countCommand + 1).trim()), registerWork.get(0));
        }
        countCommand++;
    }

    //Вывести операнд в выходную ленту: write 3
    private void write(){
        if (isPoint(programText.get(countCommand+1).trim())) {
            listOut.add(registerWork.get(registerWork.get(parseInt(programText.get(countCommand + 1).replace('*', ' ').trim()))));
        } else {
            listOut.add(registerWork.get(parseInt(programText.get(countCommand + 1).trim())));
        }
        countCommand++;
    }

    //Прибавить операнд к сумматору: add 1
    private void add(){
        if (isNumberMy(programText.get(countCommand + 1))) {
            registerWork.put(0, registerWork.get(0)+parseInt(programText.get(countCommand + 1).replace('=', ' ').trim()));
        } else {
            registerWork.put(0, registerWork.get(0) + registerWork.get(parseInt(programText.get(countCommand + 1).trim())));
        }
        countCommand++;
    }

    //Вычесть операнд из сумматора: sub =1
    private void sub(){
        if (isPoint(programText.get(countCommand+1).trim())) {
            registerWork.put(0, registerWork.get(0) - registerWork.get(registerWork.get(parseInt(programText.get(countCommand + 1).replace('*', ' ').trim()))));
        } else if (isNumberMy(programText.get(countCommand + 1))) {
            registerWork.put(0, registerWork.get(0) - parseInt(programText.get(countCommand + 1).replace('=', ' ').trim()));
        } else {
            registerWork.put(0, registerWork.get(0) - registerWork.get(parseInt(programText.get(countCommand + 1).trim())));
        }
        countCommand++;
    }

    public void jgtz(String metka) {
        if (registerWork.get(0) > 0) {
            countCommand = (Integer) EmulatorOOP.getKeyFromValue(programText, metka + ":");
        }
        else {
            countCommand++;
        }
    }

    public void jz(String metka) {
        if (registerWork.get(0) == 0) {
            countCommand = (Integer) EmulatorOOP.getKeyFromValue(programText, metka + ":");
        }
        else {
            countCommand++;
        }
    }

    public void jump(String metka) {
        countCommand = (Integer) EmulatorOOP.getKeyFromValue(programText, metka + ":");
    }

    public void halt() {
        char[] arrFileName = new char[EmulatorOOP.FILE_NAME.length()];
        arrFileName = EmulatorOOP.FILE_NAME.toCharArray();
        arrFileName[EmulatorOOP.FILE_NAME.length()-3] = 'r';
        arrFileName[EmulatorOOP.FILE_NAME.length()-2] = 'a';
        arrFileName[EmulatorOOP.FILE_NAME.length()-1] = 'm';
        EmulatorOOP.FILE_OUT = Arrays.toString(arrFileName).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
        try (FileWriter writer = new FileWriter(EmulatorOOP.FILE_OUT, false)) {
            writer.write(String.valueOf(listOut));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Данные записаны в файл: " + EmulatorOOP.FILE_OUT);
        System.exit(0);
    }

    public void run() {
        for(;;) {
            switch (programText.get(countCommand).trim()) {
                case ("load"):
                    load();
                    break;
                case ("read"):
                    read();
                    break;
                case ("store"):
                    store();
                    break;
                case ("write"):
                    write();
                    break;
                case ("add"):
                    add();
                    break;
                case ("sub"):
                    sub();
                    break;
                case ("jgtz"):
                    jgtz(programText.get(countCommand + 1));
                    break;
                case ("jz"):
                    jz(programText.get(countCommand + 1));
                    break;
                case ("jump"):
                    jump(programText.get(countCommand + 1));
                    break;
                case ("halt"):
                    halt();
                default:
                    countCommand++;
                    break;
            }
        }
    }
}
