package ru.otus.dbapi.service;

import ru.otus.dbapi.model.Account;

public interface AccountService {
    Account getAccount(long id);
    void saveAccount(Account account);
}
