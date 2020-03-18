package ru.otus;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DiyJsonSerializer {
    private Object obj;
    private JsonObjectBuilder jsonObjectBuilder;

    public String toJson(Object obj) {
        if (obj == null) {
            return getIfNull();
        }
        return createJson(obj);
    }

    public String toJson(Object obj, Class<?> clazz) {
        if (obj == null) {
            return getIfNull();
        }
        this.obj = obj;
        this.jsonObjectBuilder = Json.createObjectBuilder();
        return createJson(clazz);
    }

    private String getIfNull() {
        return "null";
    }

    private String createJson(Object obj) {
        return new JsonItem(obj).getString();
    }

    private String createJson(Class<?> clazz) {
        var fields = List.of(clazz.getDeclaredFields());
        fields.forEach(this::addField);
        return jsonObjectBuilder.build().toString();
    }

    private JsonArrayBuilder getIfArray(Object obj) {
        if (isArray(obj)) {
            return getJsonArrayBuilder(obj);
        }
        if (isCollection(obj)) {
            return getJsonArrayBuilder((Collection<?>) obj);
        }
        return null;
    }

    private JsonObjectBuilder getIfMap(Object obj) {
        if (isMap(obj)) {
            return getJsonObjectBuilder((Map<?, ?>) obj);
        }
        return null;
    }

    private JsonValue getIfSingleValue(Object obj) {
        return getJsonValue(obj);
    }

    private boolean isArray(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.isArray();
    }

    private boolean isCollection(Object obj) {
        return obj instanceof Collection<?>;
    }

    private boolean isMap(Object obj) {
        return obj instanceof Map<?, ?>;
    }

    private void addField(Field field) {
        try {
            field.setAccessible(true);
            this.add(field.getName(), field.get(obj));
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void add(String name, Object obj) {
        new JsonItem(obj).addToBuilder(jsonObjectBuilder, name);
    }

    private JsonObjectBuilder getJsonObjectBuilder(Map<?, ?> map) {
        var jb = Json.createObjectBuilder();
        map.forEach((k, v) -> jb.add(k.toString(), getJsonValue(v)));
        return jb;
    }

    private JsonArrayBuilder getJsonArrayBuilder(Collection<?> cl) {
        var jab = Json.createArrayBuilder();
        cl.forEach(v -> jab.add(getJsonValue(v)));
        return jab;
    }

    private JsonArrayBuilder getJsonArrayBuilder(Object obj) {
        Class<?> type;
        if ((type = obj.getClass().componentType()) != null) {
            if (type == int.class) {
                return getJsonArrayBuilder((int[]) obj);
            }
            if (type == byte.class) {
                return getJsonArrayBuilder((byte[]) obj);
            }
            if (type == short.class) {
                return getJsonArrayBuilder((short[]) obj);
            }
            if (type == char.class) {
                return getJsonArrayBuilder((char[]) obj);
            }
            if (type == long.class) {
                return getJsonArrayBuilder((long[]) obj);
            }
            if (type == float.class) {
                return getJsonArrayBuilder((float[]) obj);
            }
            if (type == double.class) {
                return getJsonArrayBuilder((double[]) obj);
            }
            if (type == boolean.class) {
                return getJsonArrayBuilder((boolean[]) obj);
            }
            if (type == String.class) {
                return getJsonArrayBuilder((String[]) obj);
            }
        }
        return null;
    }

    private <T> JsonArrayBuilder getJsonArrayBuilder(T[] array) {
        var jab = Json.createArrayBuilder();
        Arrays.stream(array).forEach(i -> jab.add(getJsonValue(i)));
        return jab;
    }

    private JsonArrayBuilder getJsonArrayBuilder(boolean[] array) {
        String[] gArray = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = String.valueOf(array[i]);
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(byte[] array) {
        Integer[] gArray = new Integer[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = (int)array[i];
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(short[] array) {
        Integer[] gArray = new Integer[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = (int)array[i];
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(char[] array) {
        String[] gArray = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            gArray[i] = String.valueOf(array[i]);
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(int[] array) {
        Integer[] gArray = new Integer[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = array[i];
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(long[] array) {
        Long[] gArray = new Long[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = array[i];
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(float[] array) {
        Double[] gArray = new Double[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = (double)array[i];
        }
        return getJsonArrayBuilder(gArray);
    }

    private JsonArrayBuilder getJsonArrayBuilder(double[] array) {
        Double[] gArray = new Double[array.length];
        for (int i = 0; i < array.length; ++i) {
            gArray[i] = array[i];
        }
        return getJsonArrayBuilder(gArray);
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
        return null;
    }

    class JsonItem {
        private JsonArrayBuilder arrayBuilder;
        private JsonObjectBuilder objectBuilder;
        private JsonValue value;
        private Object obj;

        public JsonItem(Object obj) {
            if (obj == null) {
                throw new NullPointerException();
            }
            this.obj = obj;
            this.identity();
        }

        private void identity() {
            arrayBuilder = getIfArray(obj);
            objectBuilder = getIfMap(obj);
            value = getIfSingleValue(obj);
        }

        String getString() {
            if (arrayBuilder != null) {
               return arrayBuilder.build().toString();
            } else if (objectBuilder != null) {
                return objectBuilder.build().toString();
            } else if (value != null) {
                return value.toString();
            } else {
                throw new IllegalArgumentException("no type matches: " + obj.getClass().getName());
            }
        }

        void addToBuilder(JsonObjectBuilder builder, String fieldName) {
            if (arrayBuilder != null) {
                builder.add(fieldName, arrayBuilder);
            } else if (objectBuilder != null) {
                builder.add(fieldName, objectBuilder);
            } else if (value != null) {
                builder.add(fieldName, value);
            } else {
                throw new IllegalArgumentException("no type matches: " + obj.getClass().getName());
            }
        }
    }
}
