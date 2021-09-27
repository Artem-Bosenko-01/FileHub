package io.javaclasses.fileHub.persistent.files;

import com.google.common.net.MediaType;
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

/**
 * Manages information about {@link File files} that saved at database.
 */
@Component
public class FileStorageInDatabase extends AbstractStorageInDatabase<FileId, File> implements FileStorage {

    @Autowired
    protected FileStorageInDatabase(JdbcConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, File dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO file VALUES (?,?,?,?,?,?)");

        statement.setString(1, dataObject.id().value());
        statement.setString(2, dataObject.name());
        statement.setString(3, dataObject.owner().value());
        statement.setLong(4, dataObject.size());
        statement.setString(5, dataObject.mimeType().toString());
        statement.setString(6, dataObject.folder());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, File dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE file " +
                "SET name=?,owner=?,size=?,mime_type=?,folder=?" +
                "WHERE id=?");

        statement.setString(1, dataObject.name());
        statement.setString(2, dataObject.owner().value());
        statement.setLong(3, dataObject.size());
        statement.setString(4, dataObject.mimeType().toString());
        statement.setString(5, dataObject.folder());
        statement.setString(6, dataObject.id().value());

        return statement;
    }

    @Override
    protected File getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        File file = new File(resultSet.getString("id"));

        file.setName(resultSet.getString("name"));

        file.setUserID(new UserId(resultSet.getString("owner")));

        file.setSize(resultSet.getLong("size"));

        file.setMimeType(MediaType.parse(resultSet.getString("mime_type")));

        file.setFolder(resultSet.getString("folder"));

        return file;
    }

    @Override
    protected String tableName() {
        return "file";
    }

    @Override
    protected String primaryKeyName() {
        return "id";
    }


    @Override
    public List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws
            NotExistedItemException {

        if (userID == null) {

            throw new NotExistedItemException("null");
        }


        try (Connection connection = this.connection()) {

            List<File> files = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM file WHERE folder=? AND owner=?");

            statement.setString(1, folderID);
            statement.setString(2, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                File file = getObjectFromResultSet(resultSet);

                files.add(file);
            }

            return files;

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

    }

    @Override
    public boolean isFIleNameAlreadyExist(String name) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM file WHERE name=?");

            statement.setString(1, name);

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
    public void deleteFilesByParentFolderId(String parentFolderId) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM file WHERE folder=?");

            statement.setString(1, parentFolderId);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new InvalidExecutingSqlStatementException(sqlException.getMessage());
        }

    }

}
