package ru.otus.jdbc;

import ru.otus.framework.querybuilder.QueryBuilder;
import ru.otus.framework.querybuilder.impl.InsertPatternQuery;
import ru.otus.framework.querybuilder.impl.QueryBuilderImpl;
import ru.otus.framework.querybuilder.impl.SelectPatternQuery;
import ru.otus.framework.querybuilder.impl.UpdatePatternQuery;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.sql.Statement.*;

public class DbExecutor<T> {
    public long insertRecord(Connection connection, Class<T> clazz, List<String> params) throws SQLException {
        Savepoint savepoint = connection.setSavepoint("savePoint");
        QueryBuilder<T> queryBuilder = new QueryBuilderImpl<>(new InsertPatternQuery());
        String sql = queryBuilder.query(clazz);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS)) {
            for (int idx = 0; idx < params.size(); ++idx) {
                preparedStatement.setString(idx + 1, params.get(idx));
            }
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            connection.rollback(savepoint);
            throw e;
        }
    }

    public void updateRecord(Connection connection, Class<T> clazz, List<String> params) throws SQLException {
        Savepoint savepoint = connection.setSavepoint("savePoint");
        QueryBuilder<T> queryBuilder = new QueryBuilderImpl<>(new UpdatePatternQuery());
        String sql = queryBuilder.query(clazz);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int idx = 0; idx < params.size(); ++idx) {
                preparedStatement.setString(idx + 1, params.get(idx));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback(savepoint);
            throw e;
        }
    }

    public Optional<T> selectRecord(Connection connection, Class<T> clazz, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        QueryBuilder<T> queryBuilder = new QueryBuilderImpl<>(new SelectPatternQuery());
        String sql = queryBuilder.query(clazz);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(resultSet));
            }
        }
    }
}
