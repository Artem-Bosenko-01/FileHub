package io.javaclasses.fileHub;

/**
 *
 * */
public interface SecuredProcess<C extends  AuthenticatedUserCommand, E> extends Process<C,E>{}
