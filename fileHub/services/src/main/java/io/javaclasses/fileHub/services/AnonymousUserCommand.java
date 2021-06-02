package io.javaclasses.fileHub.services;

/**
 * This is base, that carries information from client, that didn't authenticate in Filegub application.
 **/
public class AnonymousUserCommand implements Command{

    private static final AuthToken NULLABLE_USER_TOKEN = new AuthToken("this is anonymous user");

    @Override
    public AuthToken token() {
        return NULLABLE_USER_TOKEN;
    }
}
