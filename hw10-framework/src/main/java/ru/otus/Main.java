package ru.otus;

import ru.otus.testloader.TestLoader;

public class Main {
    public static void main(String[] args) {
        TestLoader testLoader = new TestLoader();
        testLoader.testLoad("ru.otus.testclass.TestClass");
        testLoader.printResults();
    }
}
