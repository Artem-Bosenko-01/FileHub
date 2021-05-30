package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.Query;

import java.util.Objects;

/**
 *  This is object, that contains data, that needs to get full information about user by his {@link UserID id}.
 *
 * */
public final class ProfileReadQuery extends Query {
    private final UserID id;

    public ProfileReadQuery(AuthToken token, UserID id) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
    }


    public UserID id() {
        return Preconditions.checkNotNull(id);
    }

    @Override
    public boolean equals(Object o) {

        Preconditions.checkNotNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        ProfileReadQuery that = (ProfileReadQuery) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
