package io.javaclasses.fileHub.persistent.users.tokens;

import io.javaclasses.fileHub.persistent.AbstractJDBCStorage;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.JDBCConfiguration;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

@Component
public class AuthorizationJDBCStorage extends AbstractJDBCStorage<UserAuthToken, AuthorizationUsers>
        implements AuthorizationStorage {

    @Autowired
    protected AuthorizationJDBCStorage(JDBCConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void create(AuthorizationUsers inputDataObject) throws DuplicatedIdException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO authorization_user VALUES (?,?,?)");

            statement.setString(1, inputDataObject.id().toString());
            statement.setString(2, inputDataObject.userID().toString());
            statement.setObject(3, inputDataObject.expirationTime());

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public void update(AuthorizationUsers inputDataObject) throws NotExistedItemException {
    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM authorization_user WHERE token=?");

            statement.setString(1, dataRecordID);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<AuthorizationUsers> findByID(UserAuthToken dataRecordID) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authorization_user WHERE token=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                UserAuthToken token = new UserAuthToken(resultSet.getString("token"));

                UserId userId = new UserId(resultSet.getString("userId"));

                Timestamp expirationTime = resultSet.getTimestamp("expirationTime");

                AuthorizationUsers user = new AuthorizationUsers(token, userId, expirationTime.toLocalDateTime());

                return Optional.of(user);
            }


        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean isTokenExist(String token) {

        Optional<AuthorizationUsers> user = findByID(new UserAuthToken(token));

        return user.isPresent();
    }
}
