package ru.otus.framework.querybuilder;

public class QueryBuilderException extends RuntimeException {
    public QueryBuilderException(String message) {
        super(message);
    }

    public QueryBuilderException(Exception ex) {
        super(ex);
    }
}
