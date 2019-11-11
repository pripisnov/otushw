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
    private static List<Method> beforeMethods;
    private static List<Method> testMethods;
    private static List<Method> afterMethods;
    private static int passedTestCount;
    private static int failedTestCount;
    private static Class klass;

    static {
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
    }

    @SneakyThrows
    public static void testLoad(String className) {
        klass = Class.forName(className);
        Method[] methods = klass.getDeclaredMethods();

        sortMethods(methods);

        load();
    }

    private static void sortMethods(Method[] methods) {
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

    private static void clear() {
        testMethods.clear();
        beforeMethods.clear();
        afterMethods.clear();
        passedTestCount = 0;
        failedTestCount = 0;
    }

    private static void load() {
        for (Method method : testMethods) {
            Object o = getClassInstance(klass);
            invoke(method, o);
        }
    }

    @SneakyThrows
    private static Object getClassInstance(Class klass) {
        Constructor<?> constructor = klass.getConstructor();
        return constructor.newInstance();
    }

    private static void invoke(Method testMethod, Object o) {
        for (Method method : beforeMethods) {
            invokeMethod(method, o);
        }
        invokeTestMethod(testMethod, o);
        for (Method method : afterMethods) {
            invokeMethod(method, o);
        }
    }

    @SneakyThrows
    private static void invokeMethod(Method method, Object o) {
        method.setAccessible(true);
        method.invoke(o);
    }

    private static void invokeTestMethod(Method method, Object o) {
        try {
            method.setAccessible(true);
            method.invoke(o);
            passedTestCount++;
        } catch (Exception e) {
            e.printStackTrace();
            failedTestCount++;
        }
    }

    public static void printResults() {
        System.out.println("Tests started: " + testMethods.size() +
                "\nfailed: " + failedTestCount + ", passed: " + passedTestCount);
    }
}
