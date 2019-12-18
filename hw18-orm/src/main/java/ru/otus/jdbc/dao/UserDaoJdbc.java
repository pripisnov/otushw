package ru.otus.jdbc.dao;

import ru.otus.dbapi.dao.UserDao;
import ru.otus.dbapi.model.User;
import ru.otus.framework.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {
    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<User> dbExecutor;

    public UserDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void create(User objectData) {
        try {
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(objectData.getName()));
            params.add(String.valueOf(objectData.getAge()));
            dbExecutor.insertRecord(getConnection(), User.class, params);
        } catch (SQLException e) {

        }
    }

    @Override
    public void update(User objectData) {
        try {
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(objectData.getName()));
            params.add(String.valueOf(objectData.getAge()));
            params.add(String.valueOf(objectData.getId()));
            dbExecutor.updateRecord(getConnection(), User.class, params);
        } catch (SQLException e) {

        }
    }

    @Override
    public void createOrUpdate(User objectData) {
        User user = load(objectData.getId(), User.class);
        if (user != null)
            update(objectData);
        else
            create(objectData);
    }

    @Override
    public User load(long id, Class<User> clazz) {
        try {
            Optional<User> optionalUser = dbExecutor.selectRecord(getConnection(), clazz, id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong(1), resultSet.getString(2)
                                , resultSet.getInt(3));
                    }
                } catch (SQLException e) {

                }
                return null;
            });
            return optionalUser.orElse(null);
        } catch (SQLException e) {

        }
        return null;
    }

    public Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
