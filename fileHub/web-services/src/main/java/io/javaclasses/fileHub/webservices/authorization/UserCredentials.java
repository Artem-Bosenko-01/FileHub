package io.javaclasses.fileHub.webservices.authorization;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;

/**
 * User personal data that deserialized from json object.
 */
public class UserCredentials {

    private static final Gson converter = new Gson();

    private final String credentials;


    UserCredentials(String lineForParsing) {
        this.credentials = Preconditions.checkNotNull(lineForParsing);
    }

    public AuthenticationUserCommand deserialize() {
        return converter.fromJson(credentials, AuthenticationUserCommand.class);
    }
}
