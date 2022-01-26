package io.javaclasses.fileHub.persistent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AbstractStorageInDatabaseTest {

    private final DataRecordStub record = new DataRecordStub(new RecordIdStub("id"), "field");

    @Test
    void shouldSuccessfullyGenerateSqlScriptForCreatingObject()
            throws InvalidReadingPropertyFileException, DuplicatedIdException {

        ResultSetBaseStub resultSet = new ResultSetBaseStub();

        JdbcConfigurationStub configurationStub = new JdbcConfigurationStub(resultSet);

        Storage<RecordIdStub, DataRecordStub> databaseStub = new StorageInDatabaseStub(configurationStub);

        databaseStub.create(record);

        Assertions.assertEquals("INSERT INTO tableName VALUES (?,?)", configurationStub.getLastConnection().getLastStatement().getSqlQuery());

        Map<Integer, String> map = new HashMap<>();

        map.put(1, record.id().value());
        map.put(2, record.field());

        Assertions.assertEquals(map, configurationStub.getLastConnection().getLastStatement().getParams());

    }

    @Test
    void shouldSuccessfullyGenerateSqlScriptForUpdatingObject()
            throws InvalidReadingPropertyFileException, NotExistedItemException {

        ResultSetBaseStub resultSet = new ResultSetUpdatingObjectStub();

        JdbcConfigurationStub configurationStub = new JdbcConfigurationStub(resultSet);

        Storage<RecordIdStub, DataRecordStub> databaseStub = new StorageInDatabaseStub(configurationStub);

        databaseStub.update(record);

        Assertions.assertEquals("UPDATE tableName SET field=? WHERE id=?", configurationStub.getLastConnection().getLastStatement().getSqlQuery());

        Map<Integer, String> map = new HashMap<>();

        map.put(1, record.field());
        map.put(2, record.id().value());

        Assertions.assertEquals(map, configurationStub.getLastConnection().getLastStatement().getParams());

    }

    @Test
    void shouldSuccessfullyGenerateSqlScriptForFindingObjectById()
            throws InvalidReadingPropertyFileException {

        ResultSetBaseStub resultSet = new ResultSetBaseStub();

        JdbcConfigurationStub configurationStub = new JdbcConfigurationStub(resultSet);

        Storage<RecordIdStub, DataRecordStub> databaseStub = new StorageInDatabaseStub(configurationStub);

        databaseStub.findByID(record.id());

        Assertions.assertEquals("SELECT * FROM tableName  WHERE id=?", configurationStub.getLastConnection().getLastStatement().getSqlQuery());

        Map<Integer, String> map = new HashMap<>();

        map.put(1, record.id().value());

        Assertions.assertEquals(map, configurationStub.getLastConnection().getLastStatement().getParams());

    }

    @Test
    void shouldSuccessfullyGenerateSqlScriptForDeletingObject()
            throws InvalidReadingPropertyFileException, NotExistedItemException {

        ResultSetBaseStub resultSet = new ResultSetBaseStub();

        JdbcConfigurationStub configurationStub = new JdbcConfigurationStub(resultSet);

        Storage<RecordIdStub, DataRecordStub> databaseStub = new StorageInDatabaseStub(configurationStub);

        databaseStub.delete(record.id().value());

        Assertions.assertEquals("DELETE FROM tableName  WHERE id=?", configurationStub.getLastConnection().getLastStatement().getSqlQuery());

        Map<Integer, String> map = new HashMap<>();

        map.put(1, record.id().value());

        Assertions.assertEquals(map, configurationStub.getLastConnection().getLastStatement().getParams());

    }

}