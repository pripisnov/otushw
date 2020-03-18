package ru.otus;

import com.google.gson.Gson;

public class Demo {
    public static void main(String[] args) {
        DiyJsonSerializer serializer = new DiyJsonSerializer();
        Item item1 = new Item();

        String json = serializer.toJson(item1, Item.class);
        System.out.println(json);

        Item item2 = new Gson().fromJson(json, Item.class);
        System.out.println(item2);
        System.out.println(item1.equals(item2));
    }
}
