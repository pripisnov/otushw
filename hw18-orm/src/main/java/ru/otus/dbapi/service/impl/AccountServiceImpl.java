package ru.otus.dbapi.service.impl;

import ru.otus.dbapi.dao.AccountDao;
import ru.otus.dbapi.model.Account;
import ru.otus.dbapi.service.AccountService;
import ru.otus.framework.sessionmanager.SessionManager;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getAccount(long id) {
        try (SessionManager sessionManager = accountDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return accountDao.load(id, Account.class);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        try (SessionManager sessionManager = accountDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                accountDao.createOrUpdate(account);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }
    }
}
