package ru.otus.testclass;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

@NoArgsConstructor
public class TestClass {
    @Test
    void testPrintData() {

    }

    @SneakyThrows
    @Test
    void otherTestPrintData() {
        throw new Exception();
    }

    @Before
    void beforePrintData() {

    }

    @After
    void afterPrintData() {

    }

    @SneakyThrows
    @Before
    void otherBeforePrintData() {
        throw new Exception();
    }
}
