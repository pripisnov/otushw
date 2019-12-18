package ru.otus.jdbc.dao;

import ru.otus.dbapi.dao.AccountDao;
import ru.otus.dbapi.model.Account;
import ru.otus.framework.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoJdbc implements AccountDao {
    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<Account> dbExecutor;

    public AccountDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<Account> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void create(Account objectData) {
        try {
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(objectData.getType()));
            params.add(String.valueOf(objectData.getRest()));
            dbExecutor.insertRecord(getConnection(), Account.class, params);
        } catch (SQLException e) {

        }
    }

    @Override
    public void update(Account objectData) {
        try {
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(objectData.getType()));
            params.add(String.valueOf(objectData.getRest()));
            params.add(String.valueOf(objectData.getNo()));
            dbExecutor.updateRecord(getConnection(), Account.class, params);
        } catch (SQLException e) {

        }
    }

    @Override
    public void createOrUpdate(Account objectData) {
        Account account = load(objectData.getNo(), Account.class);
        if (account != null)
            update(objectData);
        else
            create(objectData);
    }

    @Override
    public Account load(long id, Class<Account> clazz) {
        try {
                Optional<Account> optionalAccount = dbExecutor.selectRecord(getConnection(), clazz, id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new Account(resultSet.getLong(1), resultSet.getString(2)
                                , resultSet.getInt(3));
                    }
                } catch (SQLException e) {

                }
                return null;
            });
                return optionalAccount.orElse(null);
        } catch (SQLException e) {

        }
        return null;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
