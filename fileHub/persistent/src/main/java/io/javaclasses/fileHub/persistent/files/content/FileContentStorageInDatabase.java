package io.javaclasses.fileHub.persistent.files.content;

import io.javaclasses.fileHub.persistent.AbstractStorageInDatabase;
import io.javaclasses.fileHub.persistent.ConfigurationJDBC;
import io.javaclasses.fileHub.persistent.files.FileId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manages {@link FileContent files contents} that saved at database.
 */
@Component
public class FileContentStorageInDatabase extends AbstractStorageInDatabase<FileId, FileContent> implements FIleContentStorage {

    @Autowired
    protected FileContentStorageInDatabase(ConfigurationJDBC configuration) {
        super(configuration);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, FileContent dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO file_content VALUES (?,?)");


        statement.setString(1, dataObject.id().toString());
        statement.setBytes(2, dataObject.content());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, FileContent dataObject) throws SQLException {
        return null;
    }

    @Override
    protected FileContent getObjectFromResultSet(ResultSet resultSet) throws SQLException {

        FileContent fileContent = new FileContent(resultSet.getString("file_id"));

        fileContent.setContent(resultSet.getBytes("content"));

        return fileContent;
    }

    @Override
    protected String tableName() {
        return "file_content";
    }

    @Override
    protected String idName() {
        return "file_id";
    }

}
