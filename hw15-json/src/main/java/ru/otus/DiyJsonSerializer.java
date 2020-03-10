package ru.otus;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class DiyJsonSerializer {
    private Object obj;
    private JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

    public String toJson(Object obj) {
        this.obj = obj;
        return createJson();
    }

    private String createJson() {
        Class<?> clazz = this.obj.getClass();
        var fields = List.of(clazz.getDeclaredFields());
        fields.forEach(this::addField);
        return jsonObjectBuilder.build().toString();
    }

    private void addField(Field field) {
        try {
            field.setAccessible(true);
            this.add(field.getName(), field.get(obj), field.getType());
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void add(String name, Object obj, Class<?> cl) {
        if (cl == List.class || cl == Set.class) {
            var collection = (Collection<?>) obj;
            this.addCollection(name, collection);
            return;
        } else if (cl == Map.class) {
            var map = (Map<?, ?>) obj;
            var jb = Json.createObjectBuilder();
            map.forEach((k, v) -> jb.add(k.toString(), getJsonValue(v)));
            jsonObjectBuilder.add(name, jb);
            return;
        } else {
            var collection = getListIfArray(obj, cl);
            if (collection != null) {
                this.addCollection(name, collection);
                return;
            }
        }
        jsonObjectBuilder.add(name, getJsonValue(obj));
    }

    private void addCollection(String name, Collection<?> collection) {
        var jab = Json.createArrayBuilder();
        collection.forEach(v -> jab.add(getJsonValue(v)));
        jsonObjectBuilder.add(name, jab);
    }

    private List<?> getListIfArray(Object obj, Class<?> cl) {
        if (cl == int[].class) {
            return Arrays.stream((int[]) obj).boxed().collect(Collectors.toList());
        } else if (cl == byte[].class) {
            byte[] array = (byte[]) obj;
            int[] iArray = new int[array.length];
            for (int i = 0; i < array.length; ++i) iArray[i] = array[i];
            return Arrays.stream(iArray).boxed().collect(Collectors.toList());
        } else if (cl == short[].class) {
            short[] array = (short[]) obj;
            int[] iArray = new int[array.length];
            for (int i = 0; i < array.length; ++i) iArray[i] = array[i];
            return Arrays.stream(iArray).boxed().collect(Collectors.toList());
        } else if (cl == long[].class) {
            return Arrays.stream((long[]) obj).boxed().collect(Collectors.toList());
        } else if (cl == float[].class) {
            float[] array = (float[]) obj;
            double[] dArray = new double[array.length];
            for (int i = 0; i < array.length; ++i) dArray[i] = array[i];
            return Arrays.stream(dArray).boxed().collect(Collectors.toList());
        } else if (cl == double[].class) {
            return Arrays.stream((double[]) obj).boxed().collect(Collectors.toList());
        } else if (cl == boolean[].class) {
            boolean[] array = (boolean[]) obj;
            String[] strArray = new String[array.length];
            for (int i = 0; i < array.length; ++i) strArray[i] = String.valueOf(array[i]);
            return Arrays.stream(strArray).collect(Collectors.toList());
        } else if (cl == char[].class) {
            char[] array = (char[]) obj;
            String[] strArray = new String[array.length];
            for (int i = 0; i < array.length; ++i) strArray[i] = String.valueOf(array[i]);
            return Arrays.stream(strArray).collect(Collectors.toList());
        } else if (cl == String[].class) {
            var strArray = (String[]) obj;
            return Arrays.stream(strArray).collect(Collectors.toList());
        }
        return null;
    }

    private JsonValue getJsonValue(Object val) {
        var clazz = val.getClass();
        if (clazz == String.class) {
            return Json.createValue((String) val);
        } else if (clazz == Integer.class) {
            return Json.createValue((int) val);
        } else if (clazz == Short.class) {
            var v = (short) val;
            return Json.createValue(v);
        } else if (clazz == Byte.class) {
            var v = (byte) val;
            return Json.createValue(v);
        } else if (clazz == Long.class) {
            return Json.createValue((long) val);
        } else if (clazz == Double.class) {
            return Json.createValue((double) val);
        } else if (clazz == Float.class) {
            return Json.createValue((float) val);
        } else if (clazz == Boolean.class) {
            return Json.createValue(val.toString());
        } else if (clazz == Character.class) {
            return Json.createValue(val.toString());
        }
        throw new IllegalArgumentException("no type matches: " + clazz.getTypeName());
    }


}
