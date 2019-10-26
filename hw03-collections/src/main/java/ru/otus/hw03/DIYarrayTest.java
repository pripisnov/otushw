package ru.otus.hw03;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DIYarrayTest {
    public static void main(String[] args) {

        List<Integer> src = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        List<Integer> dest = new DIYarrayList<>();
        for(int i = 0; i < src.size(); i++) {
            dest.add(i);
        }

        Collections.copy(dest, src);
        Collections.addAll(dest, 44, 11, 300);
        Collections.sort(dest,  Comparator.comparingInt(o -> o));

        System.out.println(dest);

        Integer j = 11;
        dest.remove(j);

        System.out.println(dest);

        List<Integer> newDest = new DIYarrayList<>();

        System.out.println(newDest);

    }
}
