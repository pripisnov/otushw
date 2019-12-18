package ru.otus.jdbc;

import ru.otus.dbapi.dao.AccountDao;
import ru.otus.dbapi.dao.UserDao;
import ru.otus.dbapi.model.Account;
import ru.otus.dbapi.model.User;
import ru.otus.dbapi.service.AccountService;
import ru.otus.dbapi.service.UserService;
import ru.otus.dbapi.service.impl.AccountServiceImpl;
import ru.otus.dbapi.service.impl.UserServiceImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.dao.AccountDaoJdbc;
import ru.otus.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbServiceDemo {
        private static final String ACCOUNT_TABLE = "create table account(id bigint(20) NOT NULL auto_increment, type varchar(255), rest int(3))";
        private static final String USER_TABLE = "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))";

    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSourceH2();
        DbServiceDemo demo = new DbServiceDemo();

        demo.createTable(dataSource, ACCOUNT_TABLE);
        demo.createTable(dataSource, USER_TABLE);

        SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutor<Account> accountDbExecutor = new DbExecutor<>();
        DbExecutor<User> userDbExecutor = new DbExecutor<>();

        AccountDao accountDao = new AccountDaoJdbc(sessionManager, accountDbExecutor);
        AccountService accountService = new AccountServiceImpl(accountDao);
        accountService.saveAccount(new Account("test_type", 10));

        UserDao userDao = new UserDaoJdbc(sessionManager, userDbExecutor);
        UserService userService = new UserServiceImpl(userDao);
        userService.saveUser(new User("artur", 45));

        Account account = accountService.getAccount(1);
        User user = userService.getUser(1);

        System.out.println(account);
        System.out.println(user);

        account.setRest(20);
        user.setName("king_artur");

        accountService.saveAccount(account);
        userService.saveUser(user);

        account = accountService.getAccount(1);
        user = userService.getUser(1);

        System.out.println(account);
        System.out.println(user);
    }

    private void createTable(DataSource dataSource, String sql) throws SQLException {
        String tableName = getTableName(sql);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
             pst.executeUpdate();
             System.out.println(tableName + " created");
        } catch (SQLException e) {
            System.out.println(tableName + " do not created");
        }
    }

    private String getTableName(String str) {
        Pattern pattern = Pattern.compile("table [a-z]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return str.substring(matcher.start(), matcher.end());
        return "#table";
    }
}
