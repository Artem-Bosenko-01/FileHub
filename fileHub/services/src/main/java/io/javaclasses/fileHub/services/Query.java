package io.javaclasses.fileHub.services;

/**
 * The base command that contains data for readonly {@link View processes}.
 **/
public class Query extends AuthenticatedUserCommand {

    public Query(AuthToken token) {

        super(token);
    }
}
