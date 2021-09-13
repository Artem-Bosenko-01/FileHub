package io.javaclasses.fileHub.persistent;

import java.io.Serial;

/**
 * Throws when {@link DataRecord record} doesn't exist in {@link Storage storage}.
 */
public class NotExistedItemException extends Exception {

    @Serial
    private static final long serialVersionUID = 7972850341614916371L;

    public NotExistedItemException(String message) {
        super(message);
    }

}
