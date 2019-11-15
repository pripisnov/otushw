package ru.otus.testloader;

import lombok.SneakyThrows;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestLoader {
    private  List<Method> beforeMethods;
    private  List<Method> testMethods;
    private  List<Method> afterMethods;
    private  int passedTestCount;
    private  int failedTestCount;
    private  Class klass;

    public TestLoader() {
        this.beforeMethods = new ArrayList<>();
        this.testMethods = new ArrayList<>();
        this.afterMethods = new ArrayList<>();
    }

    @SneakyThrows
    public void testLoad(String className) {
        klass = Class.forName(className);
        Method[] methods = klass.getDeclaredMethods();

        sortMethods(methods);

        load();
    }

    private void sortMethods(Method[] methods) {
        clear();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Class annotationType = annotation.annotationType();
                if (annotationType == Test.class)
                    testMethods.add(method);
                else if (annotationType == Before.class)
                    beforeMethods.add(method);
                else if (annotationType == After.class)
                    afterMethods.add(method);
            }
        }
    }

    private void clear() {
        testMethods.clear();
        beforeMethods.clear();
        afterMethods.clear();
        passedTestCount = 0;
        failedTestCount = 0;
    }

    private void load() {
        for (Method method : testMethods) {
            Object o = getClassInstance(klass);
            boolean pass = invokeBeforeMethods(o) && invokeTestMethod(method, o) && invokeAfterMethods(o);
            this.setResult(pass);
        }
    }

    @SneakyThrows
    private static Object getClassInstance(Class klass) {
        Constructor<?> constructor = klass.getConstructor();
        return constructor.newInstance();
    }

    private boolean invokeBeforeMethods(Object o) {
        return invokeMethods(beforeMethods, o);
    }

    private boolean invokeTestMethod(Method method, Object o) {
        return tryInvokeMethod(method, o);
    }

    private boolean invokeAfterMethods(Object o) {
        return invokeMethods(afterMethods, o);
    }

    private boolean invokeMethods(List<Method> methods, Object o) {
        boolean pass = true;
        for (Method method : methods) {
            if (!(pass = tryInvokeMethod(method, o)))
                break;
        }
        return pass;
    }

    private void setResult(boolean flag) {
        if (flag)
            passedTestCount++;
        else
            failedTestCount++;
    }

    @SneakyThrows
    private void invokeMethod(Method method, Object o) {
        method.setAccessible(true);
        method.invoke(o);
    }

    private boolean tryInvokeMethod(Method method, Object o) {
        try {
            this.invokeMethod(method, o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void printResults() {
        System.out.println("Tests started: " + testMethods.size() +
                "\nfailed: " + failedTestCount + ", passed: " + passedTestCount);
    }
}