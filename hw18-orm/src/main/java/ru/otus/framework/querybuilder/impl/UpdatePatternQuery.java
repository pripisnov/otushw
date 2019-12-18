package ru.otus.framework.querybuilder.impl;

import ru.otus.framework.querybuilder.PatternQueryBuilder;

import java.util.List;

public class UpdatePatternQuery implements PatternQueryBuilder {
    @Override
    public String build(String tableName, List<String> columns) {
        String joinedColumns = getJoinedColumns(columns);
        String pattern = "update #table set #columns where id=?;";
        return pattern.replace("#table", tableName)
                      .replace("#columns", joinedColumns);
    }

    private String getJoinedColumns(List<String> columns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String column : columns) {
            if (stringBuilder.length() > 0)
                stringBuilder.append(", ");
            stringBuilder.append(column).append("=?");
        }
        return stringBuilder.toString();
    }
}
