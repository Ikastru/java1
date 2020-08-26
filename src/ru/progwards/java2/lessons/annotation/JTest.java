package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class JTest {

    public static void run(String name) throws Exception {
        ArrayList<Method> al = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        Class c = Class.forName(name);
        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                al.add(m);
            }
            if (m.isAnnotationPresent(Before.class)) {
                if (beforeMethod == null) beforeMethod = m;
                else throw new RuntimeException("Больше одного метода с аннотацией BeforeSuite");
            }
            if (m.isAnnotationPresent(After.class)) {
                if (afterMethod == null) afterMethod = m;
                else throw new RuntimeException("Больше одного метода с аннотацией AfterSuite");
            }
            al.sort((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());
        }
        System.out.println(al.toString());
    }

    public static void main(String[] args) throws Exception {
        run("ru.progwards.java2.lessons.annotation.Calculator");
    }
}

