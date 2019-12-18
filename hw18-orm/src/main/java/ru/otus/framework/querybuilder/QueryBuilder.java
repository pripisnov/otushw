package ru.otus.framework.querybuilder;

public interface QueryBuilder<T> {
    String query(Class<T> clazz);
}
