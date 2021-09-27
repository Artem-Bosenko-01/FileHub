package io.javaclasses.fileHub.persistent;

/**
 * Throws if an error occurs while reading the resource file.
 */
public class InvalidReadingPropertyFileException extends Exception {

    private static final long serialVersionUID = -4592855279045482270L;

    public InvalidReadingPropertyFileException(String message) {

        super(message);
    }
}
