package ru.progwards.java1.lessons.test;

import java.io.*;

/**
 * Этот класс является самостоятельной программой для копирования файлов
 * и определяет также статический метод copy(), который могут использовать
 * для копирования файлов другие программы
 */

public class FileCopy {

    public static void main(String[] args) {
        if (args.length !=2) {
            System.err.println("Формат java FileCopy <исходный файл> <конечный файл>");
        } else {
            try {
                copy (args[0], args[1]);
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Статический метод, фактически производящий копирование файла.
     * До копирования файла он, однако, произваодит множество проверок.
     */
    public static void copy(String from_name, String to_name) throws IOException {
        // Получаем объекты Fail из объектов String
        File from_file = new File(from_name);
        File to_file = new File(to_name);
        //Сначала убеждаемся, что исходный файл существует, яляется файлом и доступен для чтения
        if (!from_file.exists())
            abort("нет такого исходного файла: " + from_name);
        if (!from_file.isFile())
            abort("невозможно копирование каталога(папки): " + from_name);
        if (!from_file.canRead())
            abort("исходный файл недоступен для чтения: " + from_name);
        //Если заданный конечный файл - это каталог, то в качестве имени
        //конечного файла используем имя исходного файла
        if (to_file.isDirectory())
            to_file = new File(to_file, from_file.getName());
        //Если файл с заданным именем конечного файла существует, то
        //убеждаемся, что он доступен для записи, и, прежде, чем его перезаписывать,
        //запрашиваем подтверждение пользователя. Если конечный файл не существует,
        //убеждаемся, что такой каталог существует и доступен для записи
        if (to_file.exists()) {
            if (!to_file.canWrite())
                abort("конечный файл недоступен для записи: " + to_name);
            //Спрашиваем, следует ли перезаписывать существующий файл
            System.out.print("Перезаписать существующий файл: " + to_file.getName() + " ? Y/N: ");
            System.out.flush();
            //Получаем ответ от пользователя
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String response = in.readLine();
            //Проверяем ответ пользователя. Если ответ не Y, копирование отменяется
            if (!response.equals("Y") && !response.equals("y"))
                abort("существующий файл не был перезаписан.");
        } else {
            //Если файла с заданным именем нет, то проверяем, существует ли каталог
            //с таким именем и доступен ли он для записи. Если getParent() возвращает
            //null, каталогом будет текущий dir., для определения того, что это за
            //каталог, применяется системное свойство user.dir
            String parent = to_file.getParent();
            if (parent == null)     // If none, use the current directory
                parent = System.getProperty("user.dir");
            File dir = new File(parent);          // Convert it to a file.
            if (!dir.exists())
                abort("каталог назначения не существует: " + parent);
            if (dir.isFile())
                abort("каталог назначения не является каталогом: " + parent);
            if (!dir.canWrite())
                abort("катаолог назначения недоступен для записи: " + parent);
        }
        //Если добрались до этого места, то всё в порядке. Так что начинаем копировать файл,
        //за один приём перемещая буфер байтов
        FileInputStream from = null;
        FileOutputStream to = null;
        try {
            byte[] buffer = new byte[4096]; //Для хранения содержимого файла
            int bytes_read;                  //Число байтов в буфере
            //В буфер считывается порция байтов, затем они выводятся, цикл исполняется,
            //пока не достигнут конец файла(пока read() не возвратит -1). Обратить внимание
            // на сочетание в цикле while присваивания и сравнения.
            while ((bytes_read = from.read(buffer)) != -1) // Читаем до достижения EOF
                to.write(buffer, 0, bytes_read);            // записываем
        }
        /**
         * Всегда закрываем потоки, даже если были выданы исключения
         */
        finally {
            if (from != null) try {
                from.close();
            } catch (IOException e) {
                ;
            }
            if (to != null) try {
                to.close();
            } catch (IOException e) {
                ;
            }
        }
    }
    /**
     * Вспомогательный метод, генерирующий исключения
     */
    private static void abort(String msg) throws IOException {
        throw new IOException("FileCopy: " + msg);
    }
}
