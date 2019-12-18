package ru.otus.dbapi.dao;

import ru.otus.dbapi.model.Account;
import ru.otus.framework.sessionmanager.SessionManager;
import ru.otus.framework.template.JdbcTemplate;


public interface AccountDao extends JdbcTemplate<Account> {
    SessionManager getSessionManager();
}
