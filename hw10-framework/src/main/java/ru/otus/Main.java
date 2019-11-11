package ru.otus;

import ru.otus.testloader.TestLoader;

public class Main {
    public static void main(String[] args) {
        TestLoader.testLoad("ru.otus.testclass.TestClass");
        TestLoader.printResults();
    }
}
