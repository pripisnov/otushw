package ru.otus.framework.querybuilder.impl;

import ru.otus.framework.annotation.Column;
import ru.otus.framework.annotation.Id;
import ru.otus.framework.querybuilder.PatternQueryBuilder;
import ru.otus.framework.querybuilder.QueryBuilder;
import ru.otus.framework.querybuilder.QueryBuilderException;

import java.lang.reflect.Field;
import java.util.*;

public class QueryBuilderImpl<T> implements QueryBuilder<T> {
    private final List<String> fieldNames = new ArrayList<>();
    private final PatternQueryBuilder patternBuilder;

    public QueryBuilderImpl(PatternQueryBuilder patternBuilder) {
        this.patternBuilder = patternBuilder;
    }

    @Override
    public String query(Class<T> clazz) {
        boolean haveId = sortFields(clazz);
        if (haveId && !fieldNames.isEmpty()) {
            return build(clazz);
        }
        else
            throw new QueryBuilderException("invalid field annotation");
    }

    private boolean sortFields(Class<T> clazz) {
        boolean haveId = false;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Id id = field.getDeclaredAnnotation(Id.class);
            Column column = field.getDeclaredAnnotation(Column.class);
            if (id != null) {
                haveId = true;
            } else if (column != null) {
                fieldNames.add(column.name());
            }
        }
        return haveId;
    }

    private String build(Class<T> clazz) {
        return patternBuilder.build(clazz.getSimpleName().toLowerCase(), fieldNames);
    }
}
