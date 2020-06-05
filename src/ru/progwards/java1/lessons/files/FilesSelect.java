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

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readString;
import static org.apache.commons.io.FilenameUtils.getExtension;

public class FilesSelect {

    private boolean isDirEmpty(final Path directory) throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }

    public void selectFiles(String inFolder, String outFolder, List<String> keys){
        //Хэш Мэп для хранения атрибутов файлов
        Map<String, String> attrMapExtension = new HashMap<>();
        Map<String, String> attrMapContent = new HashMap<>();
        Map<String, String> attrMapFileName = new HashMap<>();
        final Path dir = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        File file = new File(path.toString());
                        try {
                            //Расширение файла
                            attrMapExtension.put(path.toString(), getExtension(path.toString()));
                            //Содержимое файла в одну строку
                            attrMapContent.put(path.toString(), readString(path));
                            //Имя файла
                            attrMapFileName.put(path.toString(), path.getFileName().toString());
                            for (int j = 0; j<keys.size(); j++){
                                Path pathEnd = Paths.get(outFolder+"/" + keys.get(j));
                                if (readString(path).equals(keys.get(j)) && getExtension(path.toString()).equals("txt")){
                                    //if directory exists?
                                    if (!Files.exists(path)) {
                                        try {
                                            Files.createDirectory(pathEnd);
                                        } catch (IOException e) {
                                            //fail to create directory
                                            e.printStackTrace();
                                        }
                                    }
                                    Path srcFile = Paths.get(path.toString());
                                    Path dstFile = Paths.get(outFolder+"/" + keys.get(j)+"/"+path.getFileName());
                                    Files.copy(srcFile, dstFile);
                                }
                                if (isDirEmpty(pathEnd)){
                                    Files.deleteIfExists(pathEnd);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
