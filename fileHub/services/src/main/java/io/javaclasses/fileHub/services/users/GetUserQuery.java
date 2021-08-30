package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to get full information about user by his {@link UserId id}.
 */
public final class GetUserQuery extends Query {

    private final UserId id;

    public GetUserQuery(AuthToken token, UserId id) {

        super(checkNotNull(token));
        this.id = checkNotNull(id);

    }


    public UserId id() {

        return checkNotNull(id);
    }

    @Override
    public boolean equals(Object o) {

        checkNotNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        GetUserQuery that = (GetUserQuery) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}
