package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;

import java.util.Objects;

/**
 * This is object, that contains data after successful
 * execution {@link UserAuthenticatedProcess authanticate process}.
 */
public final class UserAuthenticatedDTO {

    private final AuthToken token;
    private final UserID id;

    public UserAuthenticatedDTO(AuthToken token, UserID id) {
        this.token = Preconditions.checkNotNull(token);
        this.id = Preconditions.checkNotNull(id);
    }

    public AuthToken token() {
        return token;
    }

    public UserID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthenticatedDTO that = (UserAuthenticatedDTO) o;
        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
