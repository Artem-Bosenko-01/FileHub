package io.javaclasses.fileHub.persistent;

/**
 * Throws when someone want to add record to {@link Storage storage} with
 * already used {@link RecordId id}.
 */
public class DuplicatedIdException extends Exception {

    private static final long serialVersionUID = 1001153994850736816L;

    public DuplicatedIdException(String id) {

        super("Duplicate id " + id);
    }

}
