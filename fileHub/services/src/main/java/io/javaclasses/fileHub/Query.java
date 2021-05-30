package io.javaclasses.fileHub;

import io.javaclasses.fileHub.users.tokens.AuthToken;

public class Query extends AuthenticatedUserCommand{
    public Query(AuthToken token) {
        super(token);
    }
}
