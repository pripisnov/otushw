package ru.otus.dbapi.service;

import ru.otus.dbapi.model.User;

public interface UserService {
    User getUser(long id);
    void saveUser(User user);
}
