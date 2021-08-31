package io.javaclasses.fileHub.services;

import com.google.common.base.Preconditions;

/**
 * The base that contains data from authenticated clients in the FileHub application.
 **/
public class AuthenticatedUserCommand implements Command{

    private final AuthToken token;

    public AuthenticatedUserCommand(AuthToken token) {
        this.token = Preconditions.checkNotNull(token);
    }

    @Override
    public AuthToken token() {
        return token;
    }
}
