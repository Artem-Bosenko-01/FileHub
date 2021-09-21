package io.javaclasses.fileHub.persistent;

import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Abstract base for managing user and file systems stored in a database.
 * <p>
 * The connection to the required database will be obtained from the configuration.
 *
 * @param <I> - {@link RecordId entity identifier} type.
 * @param <E> - implementation of {@link DataRecord abstract entity}.
 */
public abstract class AbstractStorageInDatabase<I extends RecordId, E extends DataRecord<I>> implements Storage<I, E> {

    private final ConfigurationJDBC configuration;

    protected AbstractStorageInDatabase(ConfigurationJDBC configuration) {

        this.configuration = Preconditions.checkNotNull(configuration);
    }

    protected Connection connection() throws SQLException {

        return configuration.getConnection();
    }

    @Override
    public void create(E inputDataObject) throws DuplicatedIdException {

        if (findByID(inputDataObject.id()).isPresent()) {
            throw new DuplicatedIdException("");
        }

        try (Connection connection = configuration.getConnection()) {

            PreparedStatement statement = statementForCreatingObject(connection, inputDataObject);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatement(sqlException.getMessage());
        }
    }

    protected abstract PreparedStatement statementForCreatingObject(Connection connection, E dataObject) throws SQLException;

    @Override
    public void update(E inputDataObject) throws NotExistedItemException {

        if (findByID(inputDataObject.id()).isEmpty()) {
            throw new NotExistedItemException("");
        }

        try (Connection connection = configuration.getConnection()) {

            PreparedStatement statement = statementForUpdatingObject(connection, inputDataObject);

            statement.executeUpdate();

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatement(sqlException.getMessage());
        }

    }

    protected abstract PreparedStatement statementForUpdatingObject(Connection connection, E dataObject) throws SQLException;

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {

        try (Connection connection = configuration.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName() + "  WHERE " + idName() + "=?");

            statement.setString(1, dataRecordID);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatement(sqlException.getMessage());
        }
    }


    @Override
    public Optional<E> findByID(I dataRecordID) {

        try (Connection connection = configuration.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName() + "  WHERE " + idName() + "=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                E foundObject = getObjectFromResultSet(resultSet);

                return Optional.of(foundObject);
            }

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatement(sqlException.getMessage());
        }

        return Optional.empty();
    }

    protected abstract E getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract String tableName();

    protected abstract String idName();
}
