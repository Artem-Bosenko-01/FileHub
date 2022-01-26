package io.javaclasses.fileHub.services;

/**
 * This is abstract base for communication some of services with user information.
 * */
interface Command {

    AuthToken token();
}
