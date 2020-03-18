package ru.otus;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        DiyJsonSerializer serializer = new DiyJsonSerializer();
        Item item1 = new Item();

        String json = serializer.toJson(item1, Item.class);
        System.out.println(json);

        Item item2 = new Gson().fromJson(json, Item.class);
        System.out.println(item2);
        System.out.println(item1.equals(item2));

        Gson gson = new Gson();

        List<Integer> list = new ArrayList<>() { {add(1); add(2); add(3); } };

        System.out.println(gson.toJson(null).getClass());
        System.out.println(gson.toJson(List.of(1, 2 ,3)));
        System.out.println(serializer.toJson(List.of(1, 2 ,3)));

        System.out.println(gson.toJson(null).equals(serializer.toJson(null)));
        System.out.println(gson.toJson((byte)1).equals( serializer.toJson((byte)1)));
        System.out.println(gson.toJson((short)1f).equals( serializer.toJson((short)1f)));
        System.out.println(gson.toJson(1).equals( serializer.toJson(1)));
        System.out.println(gson.toJson(1L).equals( serializer.toJson(1L)));
        System.out.println(gson.toJson(1f).equals( serializer.toJson(1f)));
        System.out.println(gson.toJson(1d).equals( serializer.toJson(1d)));
        System.out.println(gson.toJson("aaa").equals( serializer.toJson("aaa")));
        System.out.println(gson.toJson('a').equals( serializer.toJson('a')));
        System.out.println(gson.toJson(new int[] {1, 2, 3}).equals( serializer.toJson(new int[] {1, 2, 3})));
        System.out.println(gson.toJson(List.of(1, 2 ,3)).equals( serializer.toJson(List.of(1, 2 ,3))));
        System.out.println(gson.toJson(Collections.singletonList(1)).equals(serializer.toJson(Collections.singletonList(1))));
    }
}
