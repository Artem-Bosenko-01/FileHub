package io.javaclasses.fileHub.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;

import java.util.Objects;

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
