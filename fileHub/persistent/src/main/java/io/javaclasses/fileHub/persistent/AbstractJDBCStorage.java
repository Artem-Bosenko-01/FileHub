package io.javaclasses.fileHub.persistent;

import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @param <I>
 * @param <E>
 */
public abstract class AbstractJDBCStorage<I extends RecordId, E extends DataRecord<I>> implements Storage<I, E> {

    private final JDBCConfiguration configuration;

    protected AbstractJDBCStorage(JDBCConfiguration configuration) {

        this.configuration = Preconditions.checkNotNull(configuration);
    }

    protected Connection connection() throws SQLException {

        return configuration.getConnection();
    }

}
