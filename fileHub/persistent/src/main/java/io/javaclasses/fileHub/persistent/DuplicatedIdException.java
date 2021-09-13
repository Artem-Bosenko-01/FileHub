package io.javaclasses.fileHub.persistent;

import java.io.Serial;

/**
 * Throws when someone want to add record to {@link Storage storage} with
 * already used {@link RecordId id}.
 */
public class DuplicatedIdException extends Exception {

    @Serial
    private static final long serialVersionUID = 1001153994850736816L;

    DuplicatedIdException(String message) {
        super(message);
    }

}
