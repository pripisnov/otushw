package ru.otus.dbapi.service.impl;

import ru.otus.dbapi.dao.UserDao;
import ru.otus.dbapi.model.User;
import ru.otus.dbapi.service.UserService;
import ru.otus.framework.sessionmanager.SessionManager;


public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.load(id, User.class);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()){
            sessionManager.beginSession();
            try {
                userDao.createOrUpdate(user);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }
    }
}
