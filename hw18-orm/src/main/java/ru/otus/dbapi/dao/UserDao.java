package ru.otus.dbapi.dao;

import ru.otus.dbapi.model.User;
import ru.otus.framework.sessionmanager.SessionManager;
import ru.otus.framework.template.JdbcTemplate;

public interface UserDao extends JdbcTemplate<User> {
    SessionManager getSessionManager();
}
