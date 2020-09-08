package ru.progwards.java2.lessons.classloader;

import javassist.*;
import jdk.jshell.execution.Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class SystemProfiler {

    public static void premain(String[] args, Instrumentation instr) throws Exception {
//        instr.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                ClassPool classPool = ClassPool.getDefault();
//                CtClass ct = null;
//                try {
//                    ct = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return ct.toBytecode();
//            }
//        });

    }

    public static void main(String[] args) throws CannotCompileException, NotFoundException {
        //Антон Архипов, лекция Javassist на службе у Java-разработчика
        ClassPool cp = ClassPool.getDefault();
        CtClass ct = cp.get("ru.progwards.java2.lessons.classloader.A");
        CtMethod foo = ct.getMethod("foo", "(Ljava/lang/String;)V");
        CtMethod foo1 =CtNewMethod.copy(foo, "__foo", ct, null);
        ct.addMethod(foo1);
        foo.setBody("try {__foo();} catch(Exception e){}");

//        CtClass ct = cp.get("ru.progwards.java2.lessons.classloader.A");
//        CtMethod foo = ct.getMethod("foo", "(Ljava/lang/String;)V");
//        foo.setBody("{System.out.println($1);}");
//        Util.invokeViaReflection(ct.toClass(), "foo", new Class[] {String.class}, new Object[] {"New str"});
//        CtClass ct = cp.makeClass("ru.progwards.java2.lessons.classloader.AdditionClass");
//        Method[] methods = ct.toClass().getMethods();
//        for (Method method : methods) {
//            System.out.println(method);
//        }
    }
}
