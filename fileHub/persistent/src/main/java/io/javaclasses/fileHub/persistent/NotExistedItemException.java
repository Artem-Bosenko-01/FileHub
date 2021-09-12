package io.javaclasses.fileHub.persistent;

/**
 * This is exception throws when someone want to find entity but {@link RecordId id} doesn't exist in {@link Storage storage}.
 */
public class NotExistedItemException extends Exception {

    private static final long serialVersionUID = 7972850341614916371L;

    public NotExistedItemException(String message) {
        super(message);
    }

}
