package ru.progwards.java2.lessons.classloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathLoader extends ClassLoader {
    final static String PATH_OF_TASKS = "c:/root/";
    final static String DOT_CLASS = ".class";
    private static PathLoader loader = new PathLoader(PATH_OF_TASKS);
    private final String basePath;
    private static String date;
    private static byte[] data;

    public PathLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public PathLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String classPath = className.replace(".", "\\");
            Path classPathName = Paths.get(basePath + date + "\\" + classPath + DOT_CLASS);
            System.out.println(classPathName.toString());
            if (Files.exists(classPathName)) {
                byte[] b = Files.readAllBytes(classPathName);
                data = Files.readAllBytes(classPathName);
                System.out.println(defineClass(className, b, 0, b.length).getSimpleName());
                System.setErr(new PrintStream(new FileOutputStream(System.getProperty(PATH_OF_TASKS)+"/patchloader.log")));
                return defineClass(className, b, 0, b.length);
            } else
                return findSystemClass(className);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ClassNotFoundException(className);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findClass(name);
        if (resolve)
            resolveClass(c);
        return c;
    }

    private static void updateTaskList(Map<String, Task> tasks) throws IOException {
        Files.walkFileTree(Paths.get(PATH_OF_TASKS), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) {
                    String classNameDate = makeClassNameDate(path);
                    date = makeDateName(classNameDate);
                    String className = classNameDate.substring(date.length()+1);
                    Task task = tasks.get(className);
                    if (task == null || task.getModifiedTime() != attrs.lastModifiedTime().toMillis()) {
                        try {
                            if (task != null)
                                loader = new PathLoader(PATH_OF_TASKS);
                            Class<?> taskClass = loader.loadClass(className, true);
                            Task newTask = (Task) taskClass.getConstructor().newInstance();
                            newTask.setModifiedTime(attrs.lastModifiedTime().toMillis());
                            System.setErr(new PrintStream(new FileOutputStream(System.getProperty(PATH_OF_TASKS)+"/patchloader.log")));
                            tasks.put(className, newTask);
                        } catch (ClassNotFoundException | NoSuchMethodException |
                                InstantiationException | IllegalAccessException |
                                InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static String makeDateName(String className) {
        return className.split("[.]")[0];
    }

    private static Path makeRelPath(Path path) {
        path = path.toAbsolutePath();
        return Paths.get(PATH_OF_TASKS).relativize(path);
    }

    private static String makeClassNameDate(Path path) {
        Path relPath = makeRelPath(path);
        String className = relPath.toString().replace("\\", ".");
        return className.substring(0, className.length() - DOT_CLASS.length());
    }

    public static void main(String[] args) throws Exception {
        Map<String, Task> tasks = new LinkedHashMap<>();
        while(true) {
            System. out.println("Проверка классов и запуск задач: " + String. format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
            updateTaskList(tasks);
            for (var task : tasks.entrySet()) {
                System. out.println(" " + task.getValue().process(data));
                Thread.sleep(5_000);
            }
        }
    }
}