package io.javaclasses.fileHub.persistent.users;

import io.javaclasses.fileHub.persistent.AbstractStorageInDatabase;
import io.javaclasses.fileHub.persistent.ConfigurationJDBC;
import io.javaclasses.fileHub.persistent.InvalidExecutingSqlStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Manages {@link User user's data} that saved at database.
 */
@Component
public class UserStorageInDatabase extends AbstractStorageInDatabase<UserId, User> implements UserStorage {

    @Autowired
    public UserStorageInDatabase(ConfigurationJDBC configurationJDBC) {

        super(configurationJDBC);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, User dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?)");

        statement.setString(1, dataObject.id().toString());
        statement.setString(2, dataObject.login());
        statement.setString(3, dataObject.password());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, User dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE users SET login=?, password=? WHERE id=?");

        statement.setString(1, dataObject.login());
        statement.setString(2, dataObject.password());
        statement.setString(3, dataObject.id().toString());

        return statement;
    }

    @Override
    protected User getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        User user = new User(new UserId(resultSet.getString("id")));

        user.setLogin(resultSet.getString("login"));

        user.setPassword(resultSet.getString("password"));

        return user;
    }

    @Override
    protected String tableName() {
        return "users";
    }

    @Override
    protected String idName() {
        return "id";
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");

            statement.setString(1, login);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = getObjectFromResultSet(resultSet);

                return Optional.of(user);
            }


        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatement(sqlException.getMessage());
        }

        return Optional.empty();
    }
}
