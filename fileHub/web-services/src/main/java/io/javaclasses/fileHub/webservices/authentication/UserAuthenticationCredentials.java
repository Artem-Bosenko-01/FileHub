package io.javaclasses.fileHub.webservices.authentication;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;

/**
 * User personal data that deserialized from json object.
 */
public class UserAuthenticationCredentials {

    private static final Gson converter = new Gson();

    private final String credentials;


    public UserAuthenticationCredentials(String lineForParsing) {
        this.credentials = Preconditions.checkNotNull(lineForParsing);
    }

    public AuthenticationUserCommand deserialize() {
        return converter.fromJson(credentials, AuthenticationUserCommand.class);
    }
}
