package io.javaclasses.fileHub;

/**
 * This is exception throws when someone want to find entity but {@link RecordID id} doesn't exist in {@link Storage storage}.
 *
 * */
public class NotExistIDException extends Exception {
    public NotExistIDException(String message) {
        super(message);
    }
}
