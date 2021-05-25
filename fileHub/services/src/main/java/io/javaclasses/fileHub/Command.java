package io.javaclasses.fileHub;

/**
 * This is abstract base, for communication some of services with user information.
 * */
public interface Command {
    AuthToken token();
}
