package io.javaclasses.fileHub.persistent;

/**
 * Throws if something went wrong while exectuing query to databese.
 * Ordinary it throws when method catches {@link java.sql.SQLException sql exception}.
 */
public class InvalidExecutingSqlStatement extends RuntimeException {

    public InvalidExecutingSqlStatement(String message) {

        super(message);
    }
}
