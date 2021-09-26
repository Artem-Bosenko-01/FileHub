package io.javaclasses.fileHub.persistent;


/**
 * Throws when {@link DataRecord record} doesn't exist in {@link Storage storage}.
 */
public class NotExistedItemException extends Exception {

    private static final long serialVersionUID = 7972850341614916371L;

    public NotExistedItemException(String id) {

        super("Id doesn't exist " + id);
    }

}
