package io.javaclasses.fileHub.persistent;

/**
 * This is exception throws when someone want to create record in {@link Storage storage} but {@link RecordId id} for
 * this record already exists.
 */
public class DuplicatedUserIdException extends Exception {

    public DuplicatedUserIdException(String message) {
        super(message);
    }

}
