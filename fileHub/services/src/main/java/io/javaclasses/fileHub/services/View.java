package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;

/**
 * This is abstract process, that handles {@link Query queries} for getting readonly data.
 *
 * @param <E> result entity.
 * @param <C> one of {@link Query query} from client.
 */
public abstract class View<C extends Query, E> extends SecuredUserProcess<C, E> {

    protected View(AuthorizationStorage storage) {

        super(storage);
    }
}
