package io.javaclasses.fileHub.webservices;

/**
 * Throws if an error occurs while running parsing to the JSON object.
 */
public final class InvalidParsingToJsonObjectException extends Exception {

    InvalidParsingToJsonObjectException(String message) {

        super(message);
    }
}
