package io.javaclasses.fileHub;

/**
 * This is abstract base, that makes it possible to handle command by authenticated user.
 *
 * @param <E> result entity.
 * @param <C> one of {@link AuthenticatedUserCommand command} from client.
 * */
public interface SecuredProcess<C extends  AuthenticatedUserCommand, E> extends Process<C,E>{}
