package io.javaclasses.fileHub.persistent.users;

import io.javaclasses.fileHub.persistent.AbstractJDBCStorage;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.JDBCConfiguration;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class UserJDBCStorage extends AbstractJDBCStorage<UserId, User> implements UserStorage {

    @Autowired
    public UserJDBCStorage(JDBCConfiguration jdbcConfiguration) {

        super(jdbcConfiguration);
    }

    @Override
    public void create(User inputDataObject) throws DuplicatedIdException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?)");

            statement.setString(1, inputDataObject.id().toString());
            statement.setString(2, inputDataObject.login());
            statement.setString(3, inputDataObject.password());

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }
    }

    @Override
    public void update(User inputDataObject) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET login=?, password=? WHERE id=?");

            statement.setString(1, inputDataObject.login());
            statement.setString(2, inputDataObject.password());
            statement.setString(3, inputDataObject.id().toString());

            statement.executeUpdate();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }
    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");

            statement.setString(1, dataRecordID);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<User> findByID(UserId dataRecordID) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User(new UserId(resultSet.getString("id")));

                user.setLogin(resultSet.getString("login"));

                user.setPassword(resultSet.getString("password"));

                return Optional.of(user);
            }


        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");

            statement.setString(1, login);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User(new UserId(resultSet.getString("id")));

                user.setLogin(resultSet.getString("login"));

                user.setPassword(resultSet.getString("password"));

                return Optional.of(user);
            }


        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }
}
