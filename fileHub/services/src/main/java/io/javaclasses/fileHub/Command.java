package io.javaclasses.fileHub;

import io.javaclasses.fileHub.users.tokens.AuthToken;

/**
 * This is abstract base, for communication some of services with user information.
 * */
public interface Command {
    AuthToken token();
}
