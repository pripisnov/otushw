package ru.otus;

import java.util.*;

public class Item {
    private String strValue = "strValue";
    private char aChar = 100;
    private short shortValue = 1;
    private byte byteValue = 1;
    private int intValue = 1;
    private long longValue = 10L;
    private float floatValue = 11.0F;
    private double doubleValue = 10.0;
    private boolean boolValue = true;
    private String[] strings = {"a", "b", "c"};
    private byte[] bytes = {1, 2, 3};
    private short[] shorts = {1, 2, 3};
    private int[] array = {1, 3, 4};
    private long[] array1 = {1, 3, 4};
    private float[] array3 = {1, 3, 4};
    private double[] array4 = {1.0, 3.0, 4.0};
    private boolean[] array5 = {true, false, true};
    private char[] chars = {100, 100, 100};
    private List<String> list = new ArrayList<>() {{
        add("one");
        add("two");
    }};

    private Set<String> set = new HashSet<>() {{
        add("apple");
        add("fruit");
    }};

    private Map<Integer, String> map = new HashMap<>() {{
       put(1, "apple");
       put(2, "caw");
    }};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return aChar == item.aChar &&
                shortValue == item.shortValue &&
                byteValue == item.byteValue &&
                intValue == item.intValue &&
                longValue == item.longValue &&
                Float.compare(item.floatValue, floatValue) == 0 &&
                Double.compare(item.doubleValue, doubleValue) == 0 &&
                boolValue == item.boolValue &&
                Objects.equals(strValue, item.strValue) &&
                Arrays.equals(strings, item.strings) &&
                Arrays.equals(bytes, item.bytes) &&
                Arrays.equals(shorts, item.shorts) &&
                Arrays.equals(array, item.array) &&
                Arrays.equals(array1, item.array1) &&
                Arrays.equals(array3, item.array3) &&
                Arrays.equals(array4, item.array4) &&
                Arrays.equals(array5, item.array5) &&
                Arrays.equals(chars, item.chars) &&
                Objects.equals(list, item.list) &&
                Objects.equals(set, item.set) &&
                Objects.equals(map, item.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(strValue, aChar, shortValue, byteValue, intValue,
                longValue, floatValue, doubleValue, boolValue, list, set, map);
        result = 31 * result + Arrays.hashCode(strings);
        result = 31 * result + Arrays.hashCode(bytes);
        result = 31 * result + Arrays.hashCode(shorts);
        result = 31 * result + Arrays.hashCode(array);
        result = 31 * result + Arrays.hashCode(array1);
        result = 31 * result + Arrays.hashCode(array3);
        result = 31 * result + Arrays.hashCode(array4);
        result = 31 * result + Arrays.hashCode(array5);
        result = 31 * result + Arrays.hashCode(chars);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "strValue='" + strValue + '\'' +
                ", aChar=" + aChar +
                ", shortValue=" + shortValue +
                ", byteValue=" + byteValue +
                ", intValue=" + intValue +
                ", longValue=" + longValue +
                ", floatValue=" + floatValue +
                ", doubleValue=" + doubleValue +
                ", boolValue=" + boolValue +
                ", strings=" + Arrays.toString(strings) +
                ", bytes=" + Arrays.toString(bytes) +
                ", shorts=" + Arrays.toString(shorts) +
                ", array=" + Arrays.toString(array) +
                ", array1=" + Arrays.toString(array1) +
                ", array3=" + Arrays.toString(array3) +
                ", array4=" + Arrays.toString(array4) +
                ", array5=" + Arrays.toString(array5) +
                ", chars=" + Arrays.toString(chars) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                '}';
    }
}
