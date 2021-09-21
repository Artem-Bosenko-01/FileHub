package io.javaclasses.fileHub.persistent.files;

import com.google.common.net.MediaType;
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
public class FileJDBCStorage extends AbstractJDBCStorage<FileId, File> implements FileStorage {

    @Autowired
    protected FileJDBCStorage(JDBCConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void create(File inputDataObject) throws DuplicatedIdException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO file VALUES (?,?,?,?,?,?)");

            statement.setString(1, inputDataObject.id().toString());
            statement.setString(2, inputDataObject.name());
            statement.setString(3, inputDataObject.owner().toString());
            statement.setLong(4, inputDataObject.size());
            statement.setString(5, inputDataObject.mimeType().toString());
            statement.setString(6, inputDataObject.folder());

            boolean createdStatus = statement.execute();

            if (createdStatus) {

                throw new DuplicatedIdException("");
            }

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }
    }

    @Override
    public void update(File inputDataObject) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE file " +
                    "SET name=?,owner=?,size=?,mimeType=?,folder=?" +
                    "WHERE id=?");

            statement.setString(1, inputDataObject.name());
            statement.setString(2, inputDataObject.owner().toString());
            statement.setLong(3, inputDataObject.size());
            statement.setString(4, inputDataObject.mimeType().toString());
            statement.setString(5, inputDataObject.folder());
            statement.setString(6, inputDataObject.id().toString());

            statement.executeUpdate();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM file WHERE id=?");

            statement.setString(1, dataRecordID);

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<File> findByID(FileId dataRecordID) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM file WHERE id=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                File file = parseFileFromResultSet(resultSet);

                return Optional.of(file);
            }

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<File> findAllFilesByFolderIdAndUserId(String folderID, String userID) throws
            NotExistedItemException {

        if (userID == null) {

            throw new NotExistedItemException("User with doesn't be null ");
        }


        try (Connection connection = this.connection()) {

            List<File> files = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM file WHERE folder=? AND owner=?");

            statement.setString(1, folderID);
            statement.setString(2, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                File file = parseFileFromResultSet(resultSet);

                files.add(file);
            }

            return files;

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
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

            throw new RuntimeException(sqlException.getMessage());
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

            throw new RuntimeException(sqlException.getMessage());
        }

    }

    private static File parseFileFromResultSet(ResultSet resultSet) throws SQLException {

        File file = new File(resultSet.getString("id"));

        file.setName(resultSet.getString("name"));

        file.setUserID(new UserId(resultSet.getString("owner")));

        file.setSize(resultSet.getLong("size"));

        file.setMimeType(MediaType.parse(resultSet.getString("mimeType")));

        file.setFolder(resultSet.getString("folder"));

        return file;
    }
}
