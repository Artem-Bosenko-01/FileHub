package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needed to get information about user by his {@link UserId id}.
 */
public final class GetUserQuery extends Query {

    public GetUserQuery(AuthToken token) {
    public GetUserQuery(AuthToken token) {

        super(checkNotNull(token));

    }
}
