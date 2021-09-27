package io.javaclasses.fileHub.webservices;

/**
 * Throws if an error occurs while running parsing to the JSON object.
 */
public final class InvalidParsingToJsonObject extends Exception {

    InvalidParsingToJsonObject(String message) {

        super(message);
    }
}
