package io.javaclasses.fileHub.persistent;

/**
 * This is exception throws when someone want to create record in {@link Storage storage} but {@link RecordId id} for
 * this record already exists.
 */
public class DuplicatedIdException extends Exception {

    private static final long serialVersionUID = 1001153994850736816L;

    DuplicatedIdException(String message) {
        super(message);
    }

}
