package ru.otus.framework.querybuilder.impl;

import ru.otus.framework.querybuilder.PatternQueryBuilder;

import java.util.List;

public class InsertPatternQuery implements PatternQueryBuilder {
    @Override
    public String build(String tableName, List<String> columns) {
        String joinedColumns = String.join(", ", columns);
        String pattern = "insert into #table(#columns) values (#values);";
        return pattern.replace("#table", tableName)
                      .replace("#columns", joinedColumns)
                      .replace("#values", symbols(columns.size()));
    }

    private String symbols(int size) {
        StringBuilder sb = new StringBuilder();
        while (size-- > 0) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append("?");
        }
        return sb.toString();
    }
}
