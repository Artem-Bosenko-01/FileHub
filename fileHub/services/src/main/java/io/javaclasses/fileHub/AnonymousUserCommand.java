package io.javaclasses.fileHub;

/**
 *
 * */
public class AnonymousUserCommand implements Command{

    private final AuthToken NULLABLE_USER_TOKEN = new AuthToken("this is anonymous user");

    @Override
    public AuthToken token() {
        return NULLABLE_USER_TOKEN;
    }
}
