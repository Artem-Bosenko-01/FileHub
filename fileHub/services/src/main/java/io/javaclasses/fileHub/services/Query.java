package io.javaclasses.fileHub.services;

public class Query extends AuthenticatedUserCommand{
    public Query(AuthToken token) {
        super(token);
    }
}
