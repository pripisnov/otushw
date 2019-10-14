package ru.otus.hw01;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> myList = Lists.newArrayList("Hello,", " ", "Otus!");
        String result = Joiner.on("").join(myList);
        System.out.println(result);
    }
}