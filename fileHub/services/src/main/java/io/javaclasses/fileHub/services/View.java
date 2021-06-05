package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;

/**
 * This is abstract process, that return entity
 * or list of entities after query handle.
 *
 * @param <E> result entity.
 * @param <C> one of query from client.
 * */
public abstract class View<C extends Query, E> extends SecuredUserProcess<C,E> {
    protected View(AuthorizationStorage storage) {
        super(storage);
    }
}
