package ru.progwards.java1.lessons.files;

/**
 * В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению),
 * дате-времени последнего изменения, размеру и по содержимому.
 * Сигнатура метода public List<List<String>> findDuplicates(String startPath),
 * результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readString;

public class FindDuplicates {

    static boolean compareFiles(Additional itObj, Additional groupObj) {
        String text1 = contentFile(itObj);
        String text2 = contentFile(groupObj);
        return text1.equals(text2);
    }

    //  прочитать содержимое файла
    static String contentFile(Additional xObj) {
        String fileAsString = "";
        try {
            Path path = Paths.get(xObj.filename.toString());
            fileAsString = Files.readString(path);
        } catch (IOException e){
            System.out.println(e);
        }
        return fileAsString;
    }

    public static List<List<String>> findDuplicates(String startPath) {
        List<Additional> fileList;     //  список всех файлов с атрибутами
        List<List<String>> myList = new ArrayList<>();
        fileList = createList(startPath);    //  получить список всех файлов

        Additional itObj;              //  текущий элемент
        Additional groupObj = null;    //  первый объект для группировки
        boolean first = true;       //  указатель первого элемента
        List<String> list1 = null;  //  список
        int index = 0;
        for (Additional oneFile : fileList) {
            index++;
            itObj = oneFile;
//            System.out.println(index + " - значение = " + itObj);
            if (first) {
                groupObj = itObj; //  запомнить первый элемент
                first = false;  //  больше этого не повтрится
                list1 = new ArrayList<>();  //  пустой список
                list1.add(Paths.get(startPath).relativize(groupObj.filename).toString());
            } else {
                if (itObj.compareTo(groupObj) == 0) { //  если ключи равны - проверить содержимое
                    if (compareFiles(itObj, groupObj))
                        list1.add(Paths.get(startPath).relativize(itObj.filename).toString());
                } else {
                    if (list1.size() > 1)
                        myList.add(list1);
                    groupObj = itObj; //  запомнить новый элемент
                    list1 = new ArrayList<>();
                    list1.add(Paths.get(startPath).relativize(groupObj.filename).toString());
                }
            }
        }
        if (list1 == null)
            return myList;

        if (list1.size() > 1)
            myList.add(list1);

        return myList;
    }

    //  перебор всех файлов и формирование списка
    public static List<Additional> createList(String startPath) {
        final String pattern = "glob:**/*";
        List<Additional> fileList = new ArrayList<>();
        if (startPath == null)
            return fileList;
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        String last = Files.getAttribute(path, "basic:lastModifiedTime").toString();
                        String size = Files.getAttribute(path, "basic:size").toString();
                        fileList.add(new Additional(path, last, size, null));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            System.out.println(e);
        }

        fileList.sort(null);
//        System.out.println("fileList = " + fileList);
        return fileList;
    }

}
