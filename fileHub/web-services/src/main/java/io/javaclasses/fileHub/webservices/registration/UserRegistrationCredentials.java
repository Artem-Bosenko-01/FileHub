package io.javaclasses.fileHub.webservices.registration;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;

/**
 * User personal data that deserialized from json object for registration user process.
 */
public class UserRegistrationCredentials {

    private static final Gson converter = new Gson();

    private final String credentials;


    public UserRegistrationCredentials(String lineForParsing) {
        this.credentials = Preconditions.checkNotNull(lineForParsing);
    }

    public RegistrationUserCommand deserialize() {
        return converter.fromJson(credentials, RegistrationUserCommand.class);
    }
}
