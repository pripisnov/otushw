package ru.otus.framework.template;

public interface JdbcTemplate<T> {
    void create(T objectData);
    void update(T objectData);
    void createOrUpdate(T objectData);
    T load(long id, Class<T> clazz);
}
