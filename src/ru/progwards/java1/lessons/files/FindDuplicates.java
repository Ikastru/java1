package ru.progwards.java1.lessons.files;

/**
 * В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению),
 * дате-времени последнего изменения, размеру и по содержимому.
 * Сигнатура метода public List<List<String>> findDuplicates(String startPath),
 * результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.
 */

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readString;

public class FindDuplicates {

    public List<List<String>> findDuplicates(String startPath) {
        //Хэш Мэп для хранения атрибутов файлов
        Map<String, Object> attrMap = new HashMap<>();
        Map<String, String> attrMapFileName = new HashMap<>();
        Map<String, String> attrMapExtension = new HashMap<>();
        Map<String, Long> attrMapLastChange = new HashMap<>();
        Map<String, Long> attrMapSize = new HashMap<>();
        Map<String, String> attrMapContent = new HashMap<>();
        //Список для сохранения всех путей по обходу для его дальнейшей проверки
        List <String> listAll = new ArrayList<>();
        //Конечный нужный список спаиска одинаковых строк
        List<List<String>> list = new ArrayList<>();
        final Path dir = Paths.get(startPath);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*");
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        //Строка для сохранения всех атрибутов файла для дальнейшей проверки
                        String attrNames =
                                "lastModifiedTime," +
                                        "lastAccessTime,"+
                                        "creationTime," +
                                        "size," +
                                        "isRegularFile," +
                                        "isDirectory," +
                                        "isSymbolicLink," +
                                        "isOther";
                        File file = new File(path.toString());
                        listAll.add(path.toString());
                        try {
                            attrMap.put(path.toString(), Files.readAttributes(path, attrNames));
                            //Имя файла
                            attrMapFileName.put(path.toString(), path.getFileName().toString());
                            //Расширение файла
                            attrMapExtension.put(path.toString(), FilenameUtils.getExtension(path.toString()));
                            //Последнее изменение файла
                            attrMapLastChange.put(path.toString(), file.lastModified());
                            //Размер файла
                            attrMapSize.put(path.toString(), file.length());
                            //Содержимое файла в одну строку
                            attrMapContent.put(path.toString(), readString(path));
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
        for (int i=0; i<listAll.size(); i++){
            String str = listAll.get(i);
            //Список для сохранения похожих строк из полного списка и добавления в конечный список
            List <String> listSameLocal = new ArrayList<>();
            for (int j = i+1; j<listAll.size(); j++){
                if (attrMapFileName.get(str).equals(attrMapFileName.get(listAll.get(j))) && attrMapExtension.get(str).equals(attrMapExtension.get(listAll.get(j)))
                && attrMapLastChange.get(str).equals(attrMapLastChange.get(listAll.get(j))) && attrMapSize.get(str).equals(attrMapSize.get(listAll.get(j)))
                && attrMapContent.get(str).equals(attrMapContent.get(listAll.get(j)))){
                    listSameLocal.add(listAll.get(j));
                }
                if (!listSameLocal.isEmpty()){
                    listSameLocal.add(str);
                }
            }
            list.add(listSameLocal);
            listSameLocal.clear();
        }
        return list;
    }

}
