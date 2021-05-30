package io.javaclasses.fileHub;

/**
 * This is exception throws when someone want to create record in {@link Storage storage} but {@link RecordID id} for
 * this record already exists.
 *
 * */
public class DuplicatedIDException extends Exception{
    public DuplicatedIDException(String message) {
        super(message);
    }
}
