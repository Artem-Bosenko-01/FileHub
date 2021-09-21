package io.javaclasses.fileHub.persistent.files;

import io.javaclasses.fileHub.persistent.AbstractJDBCStorage;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.JDBCConfiguration;
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

@Component
public class FolderJDBCStorage extends AbstractJDBCStorage<FolderId, Folder> implements FolderStorage {

    @Autowired
    protected FolderJDBCStorage(JDBCConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void create(Folder inputDataObject) throws DuplicatedIdException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO folder VALUES (?,?,?,?,?)");

            statement.setString(1, inputDataObject.id().toString());
            statement.setString(2, inputDataObject.name());
            statement.setString(3, inputDataObject.owner().toString());
            statement.setLong(4, inputDataObject.itemsAmount());
            statement.setString(5, inputDataObject.parentFolder());

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public void update(Folder inputDataObject) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE folder " +
                    "SET name=?,owner=?,items_amount=?,parent_folder=?" +
                    "WHERE id=?");

            statement.setString(1, inputDataObject.name());
            statement.setString(2, inputDataObject.owner().toString());
            statement.setLong(3, inputDataObject.itemsAmount());
            statement.setString(4, inputDataObject.parentFolder());
            statement.setString(5, inputDataObject.id().toString());

            statement.executeUpdate();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM folder WHERE id=?");

            statement.setString(1, dataRecordID);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<Folder> findByID(FolderId dataRecordID) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder WHERE id=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Folder folder = parseFolderFromResultSet(resultSet);

                return Optional.of(folder);
            }

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Folder> findAllFoldersByParentFolderId(String parentId, String owner) throws NotExistedItemException {

        if (owner == null) {

            throw new NotExistedItemException("User with doesn't be null ");
        }

        try (Connection connection = this.connection()) {

            List<Folder> folders = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE parent_folder=? AND owner=?");

            statement.setString(1, parentId);
            statement.setString(2, owner);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Folder folder = parseFolderFromResultSet(resultSet);

                folders.add(folder);
            }

            return folders;

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<Folder> findRootFolderByUserId(UserId id) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM folder " +
                    "WHERE owner=? AND parent_folder IS NULL");

            statement.setString(1, id.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Folder folder = parseFolderFromResultSet(resultSet);

                return Optional.of(folder);
            }

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean isFolderNameAlreadyExist(String name) {
        return false;
    }

    @Override
    public void decreaseItemsAmount(String id) {

    }

    @Override
    public void increaseItemsAmount(String id) {
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

                Folder folder = parseFolderFromResultSet(resultSet);

                folders.add(folder);
            }

            return folders;

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    private static Folder parseFolderFromResultSet(ResultSet resultSet) throws SQLException {

        Folder folder = new Folder(resultSet.getString("id"));

        folder.setName(resultSet.getString("name"));

        folder.setOwner(new UserId(resultSet.getString("owner")));

        folder.setItemsAmount(resultSet.getLong("items_amount"));

        folder.setParentFolder(resultSet.getString("parent_folder"));

        return folder;
    }
}
