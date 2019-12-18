package ru.otus.framework.querybuilder;

import java.util.List;

public interface PatternQueryBuilder {
    String build(String tableName, List<String> columns);
}
