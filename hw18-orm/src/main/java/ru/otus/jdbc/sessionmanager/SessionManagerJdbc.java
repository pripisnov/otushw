package ru.otus.jdbc.sessionmanager;

import ru.otus.framework.sessionmanager.DatabaseSession;
import ru.otus.framework.sessionmanager.SessionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionManagerJdbc implements SessionManager {
    private final DataSource dataSource;
    private Connection connection;
    private DatabaseSessionJdbc databaseSession;

    public SessionManagerJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void beginSession() {
        try {
            connection = dataSource.getConnection();
            databaseSession = new DatabaseSessionJdbc(connection);
        } catch (SQLException e) {
            close();
        }

    }

    @Override
    public void commitSession() {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollbackSession();
        }
    }

    @Override
    public void rollbackSession() {
        try {
            connection.rollback();
        } catch (SQLException e) {

        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }

    @Override
    public DatabaseSession getCurrentSession() {
        return databaseSession;
    }
}
