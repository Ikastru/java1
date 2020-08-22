package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.*;
import java.util.Arrays;

/*
Выводит содержимое класса (конструкторы, поля и методы)
 */

public class ClassInspector {
    public static void inspect(String clazz) throws Exception{
        Class myClass = Class.forName(clazz);
        String modifiers = Modifier.toString(myClass.getModifiers());
        System.out.println(modifiers + " class " + myClass.getSimpleName() + " {");
        getClassConstructors(myClass);
        getClassFields(myClass);
        getClassMethods(myClass);
        System.out.println("}");
    }

    private static String getFullParam(Parameter[] parameters) {
        if (parameters.length==0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length-1; i++) {
            sb.append(getParam(parameters[i]) + ", ");
        }
        sb.append(getParam(parameters[parameters.length-1]));
        return sb.toString();
    }

    private static String getParam(Parameter parameter) {
        return parameter.getType().getSimpleName() + " "
                + parameter.getName();
    }

    public static void getClassConstructors(Class myClass){
        Constructor[] constructors = myClass.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(m ->
                System.out.println("\t" + Modifier.toString(m.getModifiers()) + " "
                        + " " + m.getName() + " ("
                        + getFullParam(m.getParameters()) + ") {}" ) );
    }

    private static void getClassFields(Class myClass) {
        System.out.println();
        Field[] fields =myClass.getDeclaredFields();
        Arrays.stream(fields).forEach(m ->
                System.out.println("\t" + Modifier.toString(m.getModifiers()) + " "
                        + m.getType().getSimpleName()
                        + " " + m.getName() + ";" ) );
    }

    private static void getClassMethods(Class myClass) {
        System.out.println();
        Method[] methods = myClass.getDeclaredMethods();
        Arrays.stream(methods).forEach(m ->
                System.out.println("\t" + Modifier.toString(m.getModifiers()) + " "
                        + m.getReturnType().getSimpleName()
                        + " " + m.getName() + " ("
                        + getFullParam(m.getParameters()) + ") {}" ) );
    }

    public static void main(String[] args) throws Exception {
        inspect("ru.progwards.java2.lessons.reflection.Employee");
    }
}
