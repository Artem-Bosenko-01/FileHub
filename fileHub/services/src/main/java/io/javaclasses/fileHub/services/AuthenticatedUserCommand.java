package io.javaclasses.fileHub.services;

import com.google.common.base.Preconditions;

/**
 * This is base, that carries information from client, that authenticated in Filehub application.
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
