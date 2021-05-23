package io.javaclasses.fileHub;

import com.google.common.base.Preconditions;

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
