package io.javaclasses.fileHub.persistent;

/**
 * This is exception throws when someone want to find entity but {@link RecordId id} doesn't exist in {@link Storage storage}.
 */
public class NotExistUserIdException extends Exception {

    public NotExistUserIdException(String message) {
        super(message);
    }

}
