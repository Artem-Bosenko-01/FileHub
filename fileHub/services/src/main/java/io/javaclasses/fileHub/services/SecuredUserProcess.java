package io.javaclasses.fileHub.services;

/**
 * This is abstract base, that makes it possible to handle command by authenticated user.
 *
 * @param <E> result entity.
 * @param <C> one of {@link AuthenticatedUserCommand command} from client.
 * */
public interface SecuredUserProcess<C extends  AuthenticatedUserCommand, E> extends UserProcess<C,E> {}
