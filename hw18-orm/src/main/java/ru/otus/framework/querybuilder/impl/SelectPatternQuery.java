package ru.otus.framework.querybuilder.impl;

import ru.otus.framework.querybuilder.PatternQueryBuilder;

import java.util.List;

public class SelectPatternQuery implements PatternQueryBuilder {
    @Override
    public String build(String tableName, List<String> columns) {
        columns.add(0, "id");
        String joinedColumns = String.join(", ", columns);
        String pattern = "select #columns from #table where id=?;";
        return pattern.replace("#table", tableName)
                      .replace("#columns", joinedColumns);
    }
}
