package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GettersAndSetters {
    public static void check(String myClass) throws Exception{
        Class clazz = Class.forName(myClass);
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        String[] getSet = new String[] {"get", "set"};
        List<MyFields> list = new ArrayList<>();
        for (int i = 0; i < getSet.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                String field = fields[j].getName();
                if (field.contains("this"))
                    continue;
                MyFields myField = new MyFields();
                myField.name=getSet[i] + field.substring(0,1).toUpperCase()+ field.substring(1);
                myField.type = fields[j].getType().getSimpleName();
                myField.simplename = field;
                list.add (myField);
            }

        }
        for (MyFields item : list) {
            if (Arrays.stream(methods).filter(m->m.getName().contains(item.name)).toArray().length==0) {
                if (item.name.contains("get")) {
                    System.out.println("public " + item.type + " " + item.name + " ();");
                } else  {
                    System.out.println("public void " + item.name + " (" + item.type + " " + item.simplename + ");");
                }
            }
        }

    }

    static class MyFields {
        public String name;
        public String type;
        public String simplename;
    }

    public static void main(String[] args) throws Exception {
        check("ru.progwards.java2.lessons.reflection.Person");
    }
}

