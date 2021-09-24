package io.javaclasses.fileHub.persistent;

import java.sql.Connection;
import java.sql.ResultSet;

public class JdbcConfigurationStub extends JdbcConfiguration {

    private ConnectionStub lastConnection;
    private final ResultSet resultSet;

    ConnectionStub getLastConnection() {
        return lastConnection;
    }

    JdbcConfigurationStub(ResultSet resultSet) throws InvalidReadingPropertyFileException {

        this.resultSet = resultSet;
    }

    @Override
    public Connection getConnection() {

        ConnectionStub connectionStub = new ConnectionStub(resultSet);

        lastConnection = connectionStub;

        return connectionStub;
    }
}
