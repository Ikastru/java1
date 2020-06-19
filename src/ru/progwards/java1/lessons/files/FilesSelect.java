package ru.progwards.java1.lessons.files;

/**
 * Реализовать метод с сигнатурой public void selectFiles(String inFolder, String outFolder, List<String> keys),
 * который сортирует файлы по их содержимому. Нужно просмотреть содержимое всех файлов, с расширением txt,
 * содержащихся в каталоге inFolder с подкаталогами, и если файл содержит ключевое слово из коллекции keys,
 * то скопировать его в подпапку с соответствующим именем, этого элемента keys, все подпапки должны находиться в outFolder.
 *
 *
 * Например, вызвана функция с параметрами (“aaa”, “bbb”, {“check”, “files”} )
 *
 * нужно проверить каталог aaa с подкаталогами, найти там все файлы txt, и если файл содержит “check”,
 * скопировать его в папку bbb/check, если файл содержит “files”, скопировать его в папку bbb/files. Важно!
 * Если, например, слово “files” ни разу не встретилось, пустую папку создавать не нужно
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilesSelect {
    public static void selectFiles(String inFolder, String outFolder, List<String> keys) {
        List<Path> fList;
        Path dir2 = Paths.get(outFolder);   //  имя каталога назначения
        try {
            fList = createList(inFolder);    //  получить список всех файлов с атрибутами
            Iterator<Path> it1 = fList.iterator();
            while (it1.hasNext()) {
                Path itObj = it1.next();                    //  файл для копирования
                String text = contentFile(itObj);
                Iterator<String> it2 = keys.iterator();
                while (it2.hasNext()) {
                    String itStr = it2.next();              //  имя подкаталога назначения
                    if (text.contains(itStr)) {
                        //  найти или создать папку в outFolder
                        Path dstDir = dir2.resolve(itStr);
                        if (!Files.exists(dstDir)) {
                            // действия, если папка не существует
                            Files.createDirectory(dstDir);  //  создать каталог
                        }
                        Path dstFile = dstDir.resolve(itObj.getFileName().toString());
                        Files.copy(itObj, dstFile, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //  перебор всех файлов и формирование списка
    public static List<Path> createList(String startPath) {
        final String pattern = "glob:**/*.txt";
        List<Path> fList = new ArrayList<>();
        if (startPath == null)
            return fList;
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        fList.add(path);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e);
        }

        return fList;
    }

//  извлечь содержимое файла и проверить
    static String contentFile(Path path) {
        String fileAsString = "";
        try {
            fileAsString = Files.readString(path);
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileAsString;
    }
}

