package ru.otus.framework.sessionmanager;

import java.sql.Connection;

public interface DatabaseSession {
    Connection getConnection();
}
