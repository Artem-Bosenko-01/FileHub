package io.javaclasses.fileHub.persistent;

/**
 * Throws if something went wrong while executing query to database.
 * Ordinary it throws when method catches {@link java.sql.SQLException sql exception}.
 */
public class InvalidExecutingSqlStatementException extends RuntimeException {

    private static final long serialVersionUID = -4176125399576943685L;

    public InvalidExecutingSqlStatementException(String message) {

        super(message);
    }
}
