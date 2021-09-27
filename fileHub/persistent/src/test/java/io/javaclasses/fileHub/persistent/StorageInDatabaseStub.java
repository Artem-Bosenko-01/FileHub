package io.javaclasses.fileHub.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class StorageInDatabaseStub extends AbstractStorageInDatabase<RecordIdStub, DataRecordStub> {


    StorageInDatabaseStub(JdbcConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected PreparedStatement statementForCreatingObject(Connection connection, DataRecordStub dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO tableName VALUES (?,?)");

        statement.setString(1, dataObject.id().value());
        statement.setString(2, dataObject.field());

        return statement;
    }

    @Override
    protected PreparedStatement statementForUpdatingObject(Connection connection, DataRecordStub dataObject) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE tableName SET field=? WHERE id=?");

        statement.setString(1, dataObject.field());
        statement.setString(2, dataObject.id().value());

        return statement;
    }

    @Override
    protected DataRecordStub getObjectFromResultSet(ResultSet resultSet) {

        return new DataRecordStub(new RecordIdStub("id"), "fieldName");
    }

    @Override
    protected String tableName() {
        return "tableName";
    }

    @Override
    protected String primaryKeyName() {
        return "id";
    }

}