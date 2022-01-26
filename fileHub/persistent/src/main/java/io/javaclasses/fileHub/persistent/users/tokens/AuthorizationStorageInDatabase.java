package io.javaclasses.fileHub.persistent.users.tokens;

import io.javaclasses.fileHub.persistent.AbstractStorageInDatabase;
import io.javaclasses.fileHub.persistent.JdbcConfiguration;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

/**
 * Manages information about {@link AuthorizationUsers user session} that saved at database.
 */
@Component
public class AuthorizationStorageInDatabase extends AbstractStorageInDatabase<UserAuthToken, AuthorizationUsers>
        implements AuthorizationStorage {

    @Autowired
    protected AuthorizationStorageInDatabase(JdbcConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, AuthorizationUsers dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO authorization_user VALUES (?,?,?)");

        statement.setString(1, dataObject.id().value());
        statement.setString(2, dataObject.userID().value());
        statement.setObject(3, dataObject.expirationTime());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, AuthorizationUsers dataObject)
            throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "UPDATE authorization_user " +
                        "SET user_id=?,expiration_time=?" +
                        "WHERE token=?");

        statement.setString(1, dataObject.userID().value());
        statement.setObject(2, dataObject.expirationTime());
        statement.setString(3, dataObject.id().value());

        return statement;
    }

    @Override
    protected AuthorizationUsers getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        UserAuthToken token = new UserAuthToken(resultSet.getString("token"));

        UserId userId = new UserId(resultSet.getString("user_id"));

        Timestamp expirationTime = resultSet.getTimestamp("expiration_time");

        AuthorizationUsers user = new AuthorizationUsers(token, userId, expirationTime.toLocalDateTime());

        return user;
    }

    @Override
    protected String tableName() {

        return "authorization_user";
    }

    @Override
    protected String primaryKeyName() {

        return "token";
    }

    @Override
    public boolean isTokenExist(String token) {

        Optional<AuthorizationUsers> user = findByID(new UserAuthToken(token));

        return user.isPresent();
    }
}
