package ru.otus;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiyJsonSerializerTest {
    private Gson gson = new Gson();
    private DiyJsonSerializer serializer = new DiyJsonSerializer();

    @org.junit.jupiter.api.Test
    void toJson() {
        assertEquals(gson.toJson(null), serializer.toJson(null));
        assertEquals(gson.toJson((byte) 1), serializer.toJson((byte) 1));
        assertEquals(gson.toJson((short) 1f), serializer.toJson((short) 1f));
        assertEquals(gson.toJson(1), serializer.toJson(1));
        assertEquals(gson.toJson(1L), serializer.toJson(1L));
        assertEquals(gson.toJson(1f), serializer.toJson(1f));
        assertEquals(gson.toJson(1d), serializer.toJson(1d));
        assertEquals(gson.toJson("aaa"), serializer.toJson("aaa"));
        assertEquals(gson.toJson('a'), serializer.toJson('a'));
        assertEquals(gson.toJson(new int[]{1, 2, 3}), serializer.toJson(new int[]{1, 2, 3}));
        assertEquals(gson.toJson(List.of(1, 2, 3)), serializer.toJson(List.of(1, 2, 3)));
        assertEquals(gson.toJson(Collections.singletonList(1)), serializer.toJson(Collections.singletonList(1)));
    }

    @org.junit.jupiter.api.Test
    void testToJson() {
        Item item1 = new Item();
        String json = serializer.toJson(item1, Item.class);

        Item item2 = gson.fromJson(json, Item.class);

        assertEquals(item1, item2);

        Item item3 = new Item();
        json = serializer.toJson(item3, Item.class);

        Item item4 = gson.fromJson(json, Item.class);

        assertEquals(item3, item4);
    }
}