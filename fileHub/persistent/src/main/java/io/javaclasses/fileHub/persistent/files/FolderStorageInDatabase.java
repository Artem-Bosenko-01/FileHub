package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractStorageInDatabase;
import io.javaclasses.fileHub.persistent.InvalidExecutingSqlStatementException;
import io.javaclasses.fileHub.persistent.JdbcConfiguration;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages information about {@link Folder folders} that saved at database.
 */
@Component
public class FolderStorageInDatabase extends AbstractStorageInDatabase<FolderId, Folder> implements FolderStorage {

    @Autowired
    protected FolderStorageInDatabase(JdbcConfiguration configuration) {

        super(configuration);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, Folder dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO folder VALUES (?,?,?,?,?)");

        statement.setString(1, dataObject.id().value());
        statement.setString(2, dataObject.name());
        statement.setString(3, dataObject.owner().value());
        statement.setLong(4, dataObject.itemsAmount());
        statement.setString(5, dataObject.parentFolder());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, Folder dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE folder " +
                "SET name=?,owner=?,items_amount=?,parent_folder=?" +
                "WHERE id=?");

        statement.setString(1, dataObject.name());
        statement.setString(2, dataObject.owner().value());
        statement.setLong(3, dataObject.itemsAmount());
        statement.setString(4, dataObject.parentFolder());
        statement.setString(5, dataObject.id().value());

        return statement;
    }

    @Override
    protected Folder getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        Folder folder = new Folder(resultSet.getString("id"));

        folder.setName(resultSet.getString("name"));

        folder.setOwner(new UserId(resultSet.getString("owner")));

        folder.setItemsAmount(resultSet.getLong("items_amount"));

        folder.setParentFolder(resultSet.getString("parent_folder"));

        return folder;
    }

    @Override
    protected String tableName() {

        return "folder";
    }

    @Override
    protected String primaryKeyName() {

        return "id";
    }


    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItemException {

        if (owner == null) {

            throw new NotExistedItemException("null");
        }

        try (Connection connection = this.connection()) {

            List<Folder> folders = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE parent_folder=? AND owner=?");

            statement.setString(1, parentId);
            statement.setString(2, owner);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Folder folder = getObjectFromResultSet(resultSet);

                folders.add(folder);
            }

            return folders;

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<Folder> findRootFolderByUserId(UserId id) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE owner=? AND parent_folder IS NULL");

            statement.setString(1, id.value());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Folder folder = getObjectFromResultSet(resultSet);

                return Optional.of(folder);
            }

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean isFolderNameAlreadyExist(String name, String parentFolder) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE name=? AND parent_folder=?");

            statement.setString(1, name);
            statement.setString(2, parentFolder);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return true;
            }

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

        return false;
    }

    @Override
    public void decreaseItemsAmount(String id) throws NotExistedItemException {

        Optional<Folder> optionalFolder = findByID(new FolderId(id));

        if (optionalFolder.isPresent()) {

            Folder folder = optionalFolder.get();
            folder.setItemsAmount(folder.itemsAmount() - 1);

            update(folder);
        }
    }

    @Override
    public void increaseItemsAmount(String id) throws NotExistedItemException {

        Optional<Folder> optionalFolder = findByID(new FolderId(id));

        if (optionalFolder.isPresent()) {

            Folder folder = optionalFolder.get();
            folder.setItemsAmount(folder.itemsAmount() + 1);

            update(folder);
        }
    }

    @Override
    public List<Folder> getNestedFolders(String parentFolderId) {


        try (Connection connection = this.connection()) {

            List<Folder> folders = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE parent_folder=?");

            statement.setString(1, parentFolderId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Folder folder = getObjectFromResultSet(resultSet);

                folders.add(folder);
            }

            return folders;

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

    }

}
