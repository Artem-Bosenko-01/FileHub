package io.javaclasses.fileHub.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gets configuration parameters from resource file and configures the JDBC and {@link BasicDataSource basic connection pool source}
 * for the FileHub application.
 */
@Component
public class JdbcConfiguration {

    private static final BasicDataSource connectionsPool = new BasicDataSource();

    public JdbcConfiguration() throws InvalidReadingPropertyFileException {

        Properties properties = getPropertyFile();

        configureDataSource(properties);

    }

    public Connection getConnection() throws SQLException {

        return connectionsPool.getConnection();
    }

    private static Properties getPropertyFile() throws InvalidReadingPropertyFileException {

        Properties props = new Properties();

        try (InputStream iStream = JdbcConfiguration.class.getClassLoader().getResourceAsStream("JDBCSettings.properties")) {

            props.load(iStream);

        } catch (IOException e) {

            throw new InvalidReadingPropertyFileException(e.getMessage());
        }

        return props;
    }

    private static void configureDataSource(Properties propertyFile) {

        connectionsPool.setDriverClassName(propertyFile.getProperty("db.driver.class"));
        connectionsPool.setUrl(propertyFile.getProperty("db.conn.url"));
        connectionsPool.setUsername(propertyFile.getProperty("db.username"));
        connectionsPool.setPassword(propertyFile.getProperty("db.password"));
        connectionsPool.setInitialSize(Integer.parseInt(propertyFile.getProperty("db.pool.minSize")));
        connectionsPool.setMaxTotal(Integer.parseInt(propertyFile.getProperty("db.pool.maxSize")));
        connectionsPool.setMaxOpenPreparedStatements(100);
        connectionsPool.setPoolPreparedStatements(true);
    }

}
