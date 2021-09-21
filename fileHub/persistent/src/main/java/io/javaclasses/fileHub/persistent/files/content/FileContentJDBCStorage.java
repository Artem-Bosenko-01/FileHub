package io.javaclasses.fileHub.persistent.files.content;

import io.javaclasses.fileHub.persistent.AbstractJDBCStorage;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.JDBCConfiguration;
import io.javaclasses.fileHub.persistent.NotExistedItemException;
import io.javaclasses.fileHub.persistent.files.FileId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class FileContentJDBCStorage extends AbstractJDBCStorage<FileId, FileContent> implements FIleContentStorage {

    @Autowired
    protected FileContentJDBCStorage(JDBCConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void create(FileContent inputDataObject) throws DuplicatedIdException {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO file_content VALUES (?,?)");


            statement.setString(1, inputDataObject.id().toString());
            statement.setBytes(2, inputDataObject.content());

            statement.execute();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }
    }

    @Override
    public void update(FileContent inputDataObject) throws NotExistedItemException {
    }

    @Override
    public void delete(String dataRecordID) throws NotExistedItemException {
    }

    @Override
    public Optional<FileContent> findByID(FileId dataRecordID) {

        try (Connection connection = this.connection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM file_content WHERE fileId=?");

            statement.setString(1, dataRecordID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                FileContent fileContent = new FileContent(resultSet.getString("id"));

                fileContent.setContent(resultSet.getBytes("content"));

                return Optional.of(fileContent);
            }

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

        return Optional.empty();
    }
}
