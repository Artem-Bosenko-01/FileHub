package io.javaclasses.fileHub.services;

/**
 * The base that contains data from not authenticated clients in the FileHub application yet.
 **/
public class AnonymousUserCommand implements Command {

    private static final AuthToken NULLABLE_USER_TOKEN = new AuthToken("this is anonymous user");

    @Override
    public AuthToken token() {

        return NULLABLE_USER_TOKEN;
    }
}
