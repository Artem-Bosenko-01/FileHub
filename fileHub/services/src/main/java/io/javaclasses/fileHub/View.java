package io.javaclasses.fileHub;

/**
 * This is abstract process, that return entity
 * or list of entities after query handle.
 *
 * @param <E> result entity.
 * @param <C> one of query from client.
 * */
public interface View<C extends Query, E> extends SecuredProcess<C,E>{}
